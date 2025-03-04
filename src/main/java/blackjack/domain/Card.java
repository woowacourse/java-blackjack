package blackjack.domain;

import java.util.List;
import java.util.regex.Pattern;

public class Card {

    private final String suit; //카드 무늬:다이아몬드, 하트, 스페이드, 클로버
    private final String rank; //카드 숫자:1~9, J, Q, K, A

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public List<Integer> checkScore() {
        if (rank.equals("A")) {
            return List.of(1, 11);
        }
        if (!Pattern.matches("^[0-9]$", rank)) {
            return List.of(10);
        }
        return List.of(Integer.parseInt(rank));
    }
}
