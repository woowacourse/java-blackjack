package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public class Dealer implements Participant {

    private final Cards ownedCards; // 변수명 추천

    private Dealer() {
        this.ownedCards = Cards.of();
    }

    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public void receive(Card card) {
        ownedCards.add(card);
    }

    @Override
    public int getScore() {
        return ownedCards.calculateScore();
    }

    public List<Card> getOwnedCards() {
        return ownedCards.getCards();
    }
}
