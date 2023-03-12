package view;

import static domain.Result.LOSE;
import static domain.Result.WIN;
import static domain.Result.WIN_BY_BLACKJACK;

import domain.Card;
import domain.Result;

public class Display {

    public static String of(Result result) {
        if (result == WIN || result == WIN_BY_BLACKJACK) {
            return "승";
        }
        if (result == LOSE) {
            return "패";
        }
        return "무";
    }

    public static String of(Card card) {
        return card.getLetter().getLetter() + card.getFace().getName();
    }
}
