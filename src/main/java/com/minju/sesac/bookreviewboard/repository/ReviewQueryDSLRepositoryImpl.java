package com.minju.sesac.bookreviewboard.repository;

import com.minju.sesac.bookreviewboard.domain.QReview;
import com.minju.sesac.bookreviewboard.domain.Review;
import com.minju.sesac.bookreviewboard.dto.ReviewSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDSLRepositoryImpl implements ReviewQueryDSLRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> searchByFilters(ReviewSearchRequest request) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 1~8번 중 OR 조건(6번)을 제외한 모든 필터 조건 처리
        if (StringUtils.hasText(request.getBookTitleContains())) {
            builder.and(review.bookTitle.containsIgnoreCase(request.getBookTitleContains()));
        }
        if (request.getMinRating() != null && request.getMaxRating() != null) {
            builder.and(review.rating.between(request.getMinRating(), request.getMaxRating()));
        }
        if (StringUtils.hasText(request.getTitleContains())) {
            builder.and(review.title.containsIgnoreCase(request.getTitleContains()));
        }
        if (StringUtils.hasText(request.getAuthor())) {
            builder.and(review.author.eq(request.getAuthor()));
        }
        if (StringUtils.hasText(request.getBookAuthor())) {
            builder.and(review.bookAuthor.eq(request.getBookAuthor()));
        }
        if (StringUtils.hasText(request.getContentContains())) {
            builder.and(review.content.containsIgnoreCase(request.getContentContains()));
        }
        if (request.getRating() != null) {
            builder.and(review.rating.eq(request.getRating()));
        }
        if (StringUtils.hasText(request.getBookTitle())) {
            builder.and(review.bookTitle.eq(request.getBookTitle()));
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(request.getSort(), review);

        List<Review> reviews = jpaQueryFactory.selectFrom(review)
                                              .where(builder)
                                              .orderBy(orderSpecifier)
                                              .offset(pageable.getOffset())
                                              .limit(pageable.getPageSize())
                                              .fetch();

        Long totalCount = jpaQueryFactory.select(review.count())
                                         .from(review)
                                         .where(builder)
                                         .fetchOne();

        return new PageImpl<>(reviews, pageable, totalCount);
    }

    private OrderSpecifier<?> getOrderSpecifier(String sort, QReview review) {
        if (!StringUtils.hasText(sort)) {
            return review.createdAt.desc(); // 기본값
        }

        String[] parts = sort.split(",");
        if (parts.length != 2) {
            return review.createdAt.desc(); // 잘못된 형식일 때 기본값
        }

        String property = parts[0].trim();
        String direction = parts[1].trim()
                                   .toLowerCase();

        return switch (property) {
            case "createdAt" -> direction.equals("asc")
                                ? review.createdAt.asc()
                                : review.createdAt.desc();
            case "rating" -> direction.equals("asc")
                             ? review.rating.asc()
                             : review.rating.desc();
            case "title" -> direction.equals("asc")
                            ? review.title.asc()
                            : review.title.desc();
            default -> review.createdAt.desc(); // 허용되지 않은 필드는 기본값
        };
    }
}
