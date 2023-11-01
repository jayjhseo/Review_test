package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReviewController {
    ReviewService reviewService = new ReviewService();


    public void write() {
        System.out.print("평점(1~5): ");
        int score = Integer.parseInt(Container.getSc().nextLine());
        if (score > 5) {
            System.out.println("평점을 1~5사이의 점수로 등록해주세요.");
            return;
        }
        System.out.print("리뷰 내용: ");
        String writing = Container.getSc().nextLine();

        int reservationId = 0;
        int id = reviewService.create(reservationId, score, writing);
        System.out.println(id + "번째 리뷰가 등록되었습니다.");
    }

    public void list() {
        List<Review> reviewList = reviewService.getReviewAllList();
        if (reviewList.size() == 0) {
            System.out.println("게시물이 없습니다.");
        } else {
            System.out.println("리뷰 ID / 예약 ID / 평점 / 리뷰내용 ");
            for (int i = 0; i < reviewList.size(); i++) {
                Review review = reviewList.get(i);
                System.out.printf("%d, %d, %d, %s\n", review.getId(), review.getReservationId(), review.getScore(), review.getWriting());
            }
        }

    }

    public void remove() {
        // 저장된 reservationId값이 일치하는 리뷰내용을 삭제 가능하도록 구현
        // 로그인한 멤버가 자기 자신이 작성한 리뷰를 지울수 있어야함
        System.out.println("삭제할 리뷰내용의 ID값을 입력해주세요");
        int id = Integer.parseInt(Container.getSc().nextLine());
        Review review = reviewService.getReviewListById(id);
        if (review == null) {
            System.out.printf("%d번 리뷰내용이 존재하지 않습니다.\n", id);
            return;
        }
        System.out.printf("%d번 리뷰내용이 삭제 되었습니다.\n", id);
        this.reviewService.remove(review);
    }

    public void modify() {
        System.out.println("수정할 리뷰내용의 ID값을 입력해주세요");
        int id = Integer.parseInt(Container.getSc().nextLine());
        Review review = reviewService.getReviewListById(id);
        if (review == null) {
            System.out.printf("%d번 리뷰내용이 존재하지 않습니다.\n", id);
            return;
        }
        System.out.print("평점 수정: ");
        int score = Integer.parseInt(Container.getSc().nextLine());
        if (score > 5) {
            System.out.println("평점을 1~5사이의 점수로 등록해주세요.");
            return;
        }
        System.out.print("리뷰 수정: ");
        String writing = Container.getSc().nextLine();

        reviewService.modify(review, score, writing);

        System.out.printf("%d번 리뷰내용이 수정 되었습니다.\n", id);
    }

}
