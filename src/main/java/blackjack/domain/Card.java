package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private String name;
    private List<String> cards = new ArrayList<>();

    static {
        // 카드 52개 생성
    }

    public Card(String name) {
        this.name = name;
    }

    public Card of(String name) {
        return new Card(name);
    }
}
