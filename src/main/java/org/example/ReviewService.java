package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReviewService {
    ReviewRepository reviewRepository = new ReviewRepository();

    public int create(int reservationId, int score, String writing) {
        return this.reviewRepository.create(reservationId, score, writing);
    }

    public List<Review> getReviewAllList() {
        return this.reviewRepository.getReviewAllList();
    }

    public void remove(Review review) {
        this.reviewRepository.remove(review);
    }
    public void modify(Review review, int score, String writing) {
        reviewRepository.modify(review, score, writing);
    }
    public Review getReviewListById(int id) {
        return this.reviewRepository.getReviewListById(id);
    }
}
