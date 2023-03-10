package view;

import static domain.Result.LOSE;
import static domain.Result.WIN;

import domain.Card;
import domain.Result;

public class Display {

    public static String of(Result result) {
        if (result == WIN) {
            return "승";
        }
        if (result == LOSE) {
            return "패";
        }
        return "무";
    }

    public static String of(Card card) {
        return card.getLetter().getLetter() + card.face().getName();
    }
}
