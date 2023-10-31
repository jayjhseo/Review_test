package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class Review {
    private long id;
    //리뷰 id(고유번호)

    private int reservationId;
    //예매정보id

    private int score;
    //평점

    private String writing;
    //글(한줄평)


}
