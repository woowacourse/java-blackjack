package domain.participant;

import static domain.BlackjackGame.BLACKJACK_SCORE;
import static domain.BlackjackGame.DEALER_MIN_SCORE;
import static domain.BlackjackGame.INITIAL_CARDS;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.Hand;
import domain.result.GameResult;
import java.util.List;

public class Dealer implements Participant {
    private final Hand ownedHand;

    private Dealer() {
        this.ownedHand = Hand.of();
    }

    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public void receive(Card card) {
        ownedHand.add(card);
    }

    @Override
    public boolean canReceive() {
        return (calculateScore() <= DEALER_MIN_SCORE);
    }

    @Override
    public GameResult determineBlackjackResult(Participant opponent) {
        throw new IllegalStateException("딜러는 승패를 계산할 수 없습니다.");
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
}
