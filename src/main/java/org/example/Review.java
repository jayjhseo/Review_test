package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Review {
    private int id;
    //리뷰 id(고유번호)

    private int reservationId;
    //예매정보id

    private int score;
    //평점

    private String writing;

    public Review(int score, String writing) {
        this.score = score;
        this.writing = writing;
    }
    //글(한줄평)


}
