package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import domain.result.BlackjackResult;
import java.util.List;
import java.util.Objects;

public class Player {

    private final String name;
    private final Hand ownedHand;

    private Player(final String name) {
        this.name = name;
        this.ownedHand = Hand.of();
    }

    public static Player of(final String name) {
        return new Player(name);
    }

    public void receive(final Card card) {
        ownedHand.add(card);
    }

    public int getScore() {
        return ownedHand.calculateScore();
    }

    public int getCardCount() {
        return ownedHand.getSize();
    }

    public List<Card> getOwnedCards() {
        return ownedHand.getCards();
    }

    public String getName() {
        return name;
    }

    public BlackjackResult getBlackjackResult(Dealer dealer) {
        return BlackjackResult.getPlayerResult(dealer, this);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player player)) {
            return false;
        }
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
