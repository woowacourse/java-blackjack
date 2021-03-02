package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public abstract class Gamer {
    private final Hands hands; // 초기 카드 두장
    private String name;

    public Gamer(String name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public void receiveCard(Card card) {
        hands.add(card);
    }

}
