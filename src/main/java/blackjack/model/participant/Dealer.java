package blackjack.model.participant;

import blackjack.model.MatchResult;
import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import java.util.ArrayList;

public final class Dealer extends Participant {

    private static final int INITIAL_HAND_SIZE = 2;
    public static final int DEALER_HIT_THRESHOLD = 16;

    private final Deck deck;

    public Dealer(Deck deck) {
        super(new ArrayList<>());
        this.deck = deck;
    }

    public void dealCard(Participant participant) {
        participant.receiveHand(drawFromDeck());
    }

    private Card drawFromDeck() {
        return deck.draw();
    }

    public boolean decideHit() {
        if (hand.size() != INITIAL_HAND_SIZE) {
            throw new IllegalStateException("딜러가 가진 패가 %d장이 아니어서 히트 여부를 결정할 수 없습니다.".formatted(INITIAL_HAND_SIZE));
        }
        return getTotal() <= DEALER_HIT_THRESHOLD;
    }

    public Card getVisibleCard() {
        if (hand.isEmpty()) {
            throw new IllegalStateException("딜러가 가진 패가 없습니다.");
        }
        return hand.getFirst();
    }

    public MatchResult compareWith(Participant participant) {
        if (participant.isBust()) {
            return MatchResult.WIN;
        }
        if (this.isBust()) {
            return MatchResult.LOSE;
        }
        if (this.isBlackjack() && participant.isBlackjack()) {
            return MatchResult.DRAW;
        }
        if (participant.isBlackjack()) {
            return MatchResult.LOSE;
        }
        if (this.isBlackjack()) {
            return MatchResult.WIN;
        }
        return determineByCompareTotal(participant.getTotal());
    }

    private MatchResult determineByCompareTotal(int participantTotal) {
        int dealerTotal = getTotal();
        if (participantTotal < dealerTotal) {
            return MatchResult.WIN;
        }
        if (dealerTotal < participantTotal) {
            return MatchResult.LOSE;
        }
        return MatchResult.DRAW;
    }
}
