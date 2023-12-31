package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    List<Review> reviewList = new ArrayList<>();
    int lastId = 0;

    public int create(int reservationId, int score, String writing) {
        lastId++;
        Review review = new Review(lastId, reservationId, score, writing);  //db에서 불러온 reservationId값이 리뷰 리스트에 저장된다.
        reviewList.add(review);

        return lastId;
    }

    public List<Review> getReviewAllList() {
        return reviewList;
    }

    public void remove(Review review) {
        reviewList.remove(review);
    }
    public void modify(Review review, int score, String writing) {
        review.setScore(score);
        review.setWriting(writing);
    }
    public Review getReviewListById(int id) {
        for (int i = 0; i < reviewList.size(); i++) {
            Review review = reviewList.get(i);
            if (review.getId() == id) {
                return review;
            }
        }
        return null;
    }
}
