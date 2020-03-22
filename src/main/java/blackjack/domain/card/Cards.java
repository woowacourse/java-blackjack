package blackjack.domain.card;

import blackjack.domain.card.component.CardNumber;
import blackjack.domain.user.component.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        Objects.requireNonNull(cards);
        this.cards = cards;
    }

    public void add(Card card) {
        Objects.requireNonNull(card);
        cards.add(card);
    }

    public boolean hasAce() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();
        return aceCount > 0;
    }

    public int computePoint() {
        int totalPoint =  cards.stream()
                .mapToInt(x -> x.getCardPoint())
                .sum();
        if (hasAce() && totalPoint < Point.BLACK_JACK) {
            totalPoint += CardNumber.ACE_DIFF;
        }
        return totalPoint;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
