package domain.participant;

import static domain.BlackjackGame.BLACKJACK_SCORE;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.Hand;
import domain.result.GameResult;
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

    public boolean canReceive() {
        return (getScore() < BLACKJACK_SCORE);
    }

    public GameResult getBlackjackResult(Dealer dealer) {
        if (isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (getScore() > dealer.getScore()) {
            return GameResult.WIN;
        }
        if (getScore() < dealer.getScore()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public boolean isBust() {
        return getScore() > BlackjackGame.BLACKJACK_SCORE;
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
