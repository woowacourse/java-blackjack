package domain.participant;

import domain.PlayingCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.BlackJackGame.BLACKJACK_CONDITION;
import static domain.BlackJackGame.INITIAL_DRAW;

public class Hand {
    private final List<PlayingCard> playingCards;

    private Hand(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Hand init() {
        return new Hand(new ArrayList<>());
    }

    private int getCardsSum(final int intermediate) {
        return playingCards.stream()
                .map(playingCard -> playingCard.getValue(intermediate))
                .reduce(0, Integer::sum);
    }

    public int getOptimizedSum() {
        return getCardsSum(getPrimitiveSum());
    }

    private int getPrimitiveSum() {
        return getCardsSum(BLACKJACK_CONDITION);
    }

    public boolean isNotBust() {
        return getOptimizedSum() <= BLACKJACK_CONDITION;
    }

    public void addCard(final PlayingCard card) {
        playingCards.add(card);
    }

    public List<PlayingCard> getPlayingCards() {
        return Collections.unmodifiableList(playingCards);
    }

    public boolean isNotMaximum() {
        return getOptimizedSum() != BLACKJACK_CONDITION;
    }

    public boolean isBlackJack() {
        return getOptimizedSum() == BLACKJACK_CONDITION && playingCards.size() == INITIAL_DRAW;
    }
}
