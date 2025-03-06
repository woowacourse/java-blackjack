package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.result.BlackjackResult;
import java.util.List;

public class Player implements Participant {

    private final String name;
    private final Cards ownedCards;

    private Player(final String name) {
        this.name = name;
        this.ownedCards = Cards.of();
    }

    public static Player of(final String name) {
        return new Player(name);
    }

    @Override
    public void receive(final Card card) {
        ownedCards.add(card);
    }

    @Override
    public int getScore() {
        return ownedCards.calculateScore();
    }

    public List<Card> getOwnedCards() {
        return ownedCards.getCards();
    }

    public String getName() {
        return name;
    }

    public BlackjackResult getBlackjackResult(Dealer dealer) {
        return BlackjackResult.getPlayerResult(dealer, this);
    }
}
