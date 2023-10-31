package org.example;

import java.security.cert.CertPath;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class ReviewController {
    List<Review> reviewList = new ArrayList<>();
    List<Member> memberList = new ArrayList<>();
    long id = 0;
    int reservationId = 0;
    Member loginedMember = null;

    public void run() {
        System.out.println("리뷰 페이지\n작성\n목록\n삭제\n수정\n");
        while (true) {
            System.out.print("명령) ");
            String command = Container.getSc().nextLine().trim();

            if (command.equals("작성")) {
                if (loginedMember == null) {
                    System.out.println("로그인 후 이용해주세요.");
                    continue;
                }
                //일단 이렇게 처리, 나중에 db에서 정보 가져옴
                System.out.print("평점(1~5): ");
                int score = Integer.parseInt(Container.getSc().nextLine());
                if (score > 5) {
                    System.out.println("평점을 1~5사이의 점수로 등록해주세요.");
                    continue;
                }
                id++;
                reservationId++;
                System.out.print("리뷰 내용: ");
                String writing = Container.getSc().nextLine();

                Review review = new Review(id, reservationId, score, writing);  //db에서 불러온 reservationId값이 리뷰 리스트에 저장된다.
                reviewList.add(review);

            } else if (command.equals("목록")) {
//                사용자가 작성한 목록을 출력(다른 페이지에서 모든 사용자가 작성한 리뷰를 출력)
                System.out.println("리뷰 id / 평점 / 리뷰내용");
                for (int i = 0; i < reviewList.size(); i++) {
                    Review review = reviewList.get(i);
                    System.out.printf("%d, %d, %s\n", review.getId(), review.getScore(), review.getWriting());
                }
            } else if (command.equals("삭제")) {
                // 저장된 reservationId값이 일치하는 리뷰내용을 삭제 가능하도록 구현
//                boolean checkLoginedMember = false;
//                for (int i = 0; i < memberList.size(); i++) {
//                    Member member = memberList.get(i);
//                    if (member.getUserId().equals(loginedMember)) {
//                        checkLoginedMember = true;
//                    }
//                } if (checkLoginedMember == false) {
//                    System.out.println("회원정보가 일치하지 않습니다.");
//                    continue;
//                }
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
                }
                if (checkId == false) {
                    System.out.printf("%d번 리뷰내용이 존재하지 않습니다.\n", id);
                    continue;
                }
                System.out.printf("%d번 리뷰내용이 삭제 되었습니다.\n", id);

            } else if (command.equals("수정")) {
                // 로그인한 멤버가 자기 자신이 작성한 리뷰를 수정할수 있어야함
                // 수정이나 삭제 할 리뷰내용은 loginedMember가 일단 null값이 아닌경우에 가능
                // 또한 수정이나 삭제할시 loginedMember가 저장한 값을 수정이나 삭제가 가능하게 만들어야함
                // loginedMember == null -> 로그인 후 이용해달라
                // loginedMember == member(해당 리뷰를 작성한 유저) 삭제와 수정이 가능
                // 명언앱이나 텍스트앱은 로그인만 되면 그 어떠한 명언이나 텍스트를 삭제할 수 있었다.
                System.out.println("수정할 리뷰내용의 ID값을 입력해주세요");
                long id = Long.parseLong(Container.getSc().nextLine());
                boolean checkId = false;
                String e = "";
                for (int i = 0; i < reviewList.size(); i++) {
                    Review review = reviewList.get(i);
                    if (review.getId() == id) {
                        checkId = true;
                        System.out.print("평점 수정: ");
                        int score = Container.getSc().nextInt();
                        if (score > 5) {
                            System.out.println("평점을 1~5사이의 점수로 등록해주세요.");
                            continue;
                        }
                        System.out.print("리뷰 수정: ");
                        String writing = Container.getSc().nextLine();

                        review.setScore(score);
                        review.setWriting(writing);
                    }
                }
                if (checkId == false) {
                    System.out.printf("%d번 리뷰내용이 존재하지 않습니다.\n", id);
                    continue;
                }
                System.out.printf("%d번 리뷰내용이 수정 되었습니다.\n", id);
            } else if (command.equals("회원가입")) {
                String userId;
                String password;
                String passwordConfirm;

                while (true) {
                    System.out.printf("아이디) ");
                    userId = Container.getSc().nextLine();
                    boolean duplicatedUserId = false;

                    for (int i = 0; i < memberList.size(); i++) {
                        if (memberList.get(i).getUserId().equals(userId)) {
                            duplicatedUserId = true;
                        }
                    }

                    if (duplicatedUserId) {
                        System.out.println("존재하는 아이디 입니다.");
                        continue;
                    }

                    break;
                }

                while (true) {
                    System.out.printf("비번) ");
                    password = Container.getSc().nextLine();

                    System.out.printf("비번 확인) ");
                    passwordConfirm = Container.getSc().nextLine();

                    if (password.equals(passwordConfirm) == false) {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        continue;
                    }
                    break;
                }

                LocalDate now = LocalDate.now();

                Member member = new Member(userId, password, now.toString());
                memberList.add(member);

                System.out.println(userId + "님 회원가입이 완료되었습니다.");
            } else if (command.equals("로그인")) {
                if (loginedMember != null) {
                    System.out.println("로그인이 되어 있습니다.");
                    continue;
                }

                boolean checkedUserId = false;
                Member member = null;

                System.out.printf("아이디) ");
                String userId = Container.getSc().nextLine();
                System.out.printf("비번) ");
                String password = Container.getSc().nextLine();

                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getUserId().equals(userId)) {
                        member = memberList.get(i);
                        checkedUserId = true;
                        break;
                    }
                }

                if (checkedUserId == false) {
                    System.out.println("해당 회원이 존재하지 않습니다.");
                    continue;
                }

                if (member.getPassword().equals(password) == false) {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    continue;
                }

                loginedMember = member;

                System.out.println("로그인 성공!" + loginedMember.getUserId() + "님 환영합니다.");
            } else if (command.equals("로그아웃")) {

                if (loginedMember == null) {
                    System.out.println("로그인 상태가 아닙니다.");
                    continue;
                }

                loginedMember = null;
                System.out.println("로그아웃 처리가 되었습니다.");
            }
        }
    }
}
