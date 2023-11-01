package org.example;


public class ReviewApp {
    ReviewController reviewController = new ReviewController();

    public void run() {
        System.out.println("리뷰 페이지\n작성\n목록\n삭제\n수정\n");
        while (true) {
            System.out.print("명령) ");
            String command = Container.getSc().nextLine().trim();

            if (command.equals("작성")) {
                reviewController.write();
            } else if (command.equals("목록")) {
                reviewController.list();
//                사용자가 작성한 목록을 출력(다른 페이지에서 모든 사용자가 작성한 리뷰를 출력)
            } else if (command.equals("삭제")) {
                reviewController.remove();
            } else if (command.equals("수정")) {
                reviewController.modify();

            }
        }
    }
}
