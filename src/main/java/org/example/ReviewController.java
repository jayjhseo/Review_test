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
//                사용자가 작성한 목록을 출력(다른 페이지에서 모든 사용자가 작성한 리뷰를 출력)
                System.out.println("리뷰 id / 평점 / 리뷰내용");
                for (int i = 0; i < reviewList.size(); i++) {
                    Review review = reviewList.get(i);
                    System.out.printf("%d, %d, %s\n", review.getId(), review.getScore(), review.getWriting());
                }
            } else if(command.equals("삭제")) {
                // 로그인한 멤버가 자기 자신이 작성한 리뷰를 지울수 있어야함
                System.out.println("삭제할 리뷰내용의 ID값을 입력해주세요");
                long id = Long.parseLong(Container.getSc().nextLine());
                boolean checkId = false;
                for (int i = 0; i < reviewList.size(); i++) {
                    Review review = reviewList.get(i);
                    if (review.getId() == id) {
                        checkId = true;
                        reviewList.remove(review);
                    }
                } if (checkId == false) {
                    System.out.printf("%d번 리뷰내용이 존재하지 않습니다.\n", id);
                    continue;
                } System.out.printf("%d번 리뷰내용이 삭제 되었습니다.\n", id);

            } else if(command.equals("수정")) {
                // 로그인한 멤버가 자기 자신이 작성한 리뷰를 수정할수 있어야함
                // 수정이나 삭제 할 리뷰내용은 loginedMember가 일단 null값이 아닌경우에 가능
                // 또한 수정이나 삭제할시 loginedMember가 저장한 값을 수정이나 삭제가 가능하게 만들어야함
                // loginedMember == null -> 로그인 후 이용해달라
                // loginedMember == member(해당 리뷰를 작성한 유저) 삭제와 수정이 가능
                System.out.println("수정할 리뷰내용의 ID값을 입력해주세요");
                long id = Long.parseLong(Container.getSc().nextLine());
                boolean checkId = false;
                for (int i = 0; i < reviewList.size(); i++) {
                    Review review = reviewList.get(i);
                    if (review.getId() == id) {
                        checkId = true;
                        System.out.print("평점 수정: ");
                        int score = Container.getSc().nextInt();
                        Container.getSc().nextLine();
                        System.out.print("리뷰 수정: ");
                        String writing = Container.getSc().nextLine();

                        review.setScore(score);
                        review.setWriting(writing);
                    }
            } if (checkId == false) {
                    System.out.printf("%d번 리뷰내용이 존재하지 않습니다.\n", id);
                    continue;
                } System.out.printf("%d번 리뷰내용이 수정 되었습니다.\n", id);
            }
        }
    }
}
