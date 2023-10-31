package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReviewController {
    List<Review> reviewList = new ArrayList<>();
    long id = 0;
    public void run() {
            System.out.println("리뷰 페이지");
        while (true) {
            System.out.print("명령) ");
            String command = Container.getSc().nextLine().trim();

            if (command.equals("작성")) {
                id++;
                System.out.print("평점(1~5): ");
                int score = Container.getSc().nextInt();
                Container.getSc().nextLine();
                System.out.print("리뷰 내용: ");
                String writing = Container.getSc().nextLine();

                Review review = new Review(id, score, writing);
                reviewList.add(review);

            } else if (command.equals("목록")) {
                System.out.println("리뷰 id / 평점 / 리뷰내용");
                for (int i = 0; i < reviewList.size(); i++) {
                    Review review = reviewList.get(i);
                    System.out.printf("%d, %d, %s\n", review.getId(), review.getScore(), review.getWriting());
                }
            } else if(command.equals("삭제")) {
                // 로그인한 멤버가 자기 자신이 작성한 리뷰를 지울수 있어야함
                System.out.println("삭제할 리뷰내용의 ID값을 입력해주세요");
                String id = Container.getSc().nextLine();
            } else if(command.equals("수정")) {
                // 로그인한 멤버가 자기 자신이 작성한 리뷰를 수정할수 있어야함
            }
        }
    }
}
