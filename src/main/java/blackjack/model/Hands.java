package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hands {

    private final List<Card> cards;

    public Hands(List<Card> cards) {
        this.cards = cards;
    }

    public static Hands empty() {
        return new Hands(new ArrayList<>());
    }

    //카드를 한 장 추가한다.
    public void addACard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("card가 null입니다.");
        }

        cards.add(card);
    }

    //핸즈가 가진 카드들의 점수를 계산한다.
    public int calculateTotalScore() {
        return this.cards.stream()
                .mapToInt(Card::score)
                .sum();
    }
}
