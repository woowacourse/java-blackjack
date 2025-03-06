package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public class Dealer implements Participant {

    private final Cards ownedCards;

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

    @Override
    public int getCardCount() {
        return ownedCards.getSize();
    }

    public List<Card> getOwnedCards() {
        return ownedCards.getCards();
    }
}
