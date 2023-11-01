package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReviewService {
    List<Review> reviewList = new ArrayList<>();
    int id = 0;

    public int create(int score, String writing) {
        id++;
        Review review = new Review(score, writing);  //db에서 불러온 reservationId값이 리뷰 리스트에 저장된다.
        reviewList.add(review);

        return id;
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
    public Review getListById(int id) {
        for (int i = 0; i < reviewList.size(); i++) {
            Review review = reviewList.get(i);
            if (review.getId() == id) {
                return review;
            }
        }
        return null;
    }
}
