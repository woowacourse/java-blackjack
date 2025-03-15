package domain.participant;

import static domain.BlackjackGame.BLACKJACK_SCORE;
import static domain.BlackjackGame.INITIAL_CARDS;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.Hand;
import domain.result.GameResult;
import java.util.List;
import java.util.Objects;

public class Player implements Participant {

    private final String name;
    private final Hand ownedHand;

    private Player(final String name) {
        this.name = name;
        this.ownedHand = Hand.of();
    }

    public static Player of(final String name) {
        return new Player(name);
    }

    @Override
    public void receive(final Card card) {
        ownedHand.add(card);
    }

    @Override
    public boolean canReceive() {
        return (calculateScore() < BLACKJACK_SCORE);
    }

    @Override
    public GameResult determineBlackjackResult(Participant opponent) {
        if (isBust()) {
            return GameResult.LOSE;
        }
        if (opponent.isBust()) {
            return GameResult.WIN;
        }

        return determineResultByScore(opponent);
    }

    private GameResult determineResultByScore(Participant opponent) {
        if (isBlackjack() && !opponent.isBlackjack()) {
            return GameResult.WIN;
        }
        if (!isBlackjack() && opponent.isBlackjack()) {
            return GameResult.LOSE;
        }

        return compareScoresWith(opponent);
    }

    private GameResult compareScoresWith(Participant opponent) {
        if (calculateScore() > opponent.calculateScore()) {
            return GameResult.WIN;
        }
        if (calculateScore() < opponent.calculateScore()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    @Override
    public boolean isBust() {
        return calculateScore() > BlackjackGame.BLACKJACK_SCORE;
    }

    @Override
    public boolean isBlackjack() {
        return countCard() == INITIAL_CARDS && calculateScore() == BLACKJACK_SCORE;
    }

    @Override
    public int calculateScore() {
        return ownedHand.calculateScore();
    }

    public int countCard() {
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
