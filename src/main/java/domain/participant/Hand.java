package domain.participant;

import domain.PlayingCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.BlackJackGame.BLACKJACK_CONDITION;
import static domain.BlackJackGame.INITIAL_DRAW;

public class Hand {
    private static final int ACE_GAP = 10;
    
    private final List<PlayingCard> playingCards;

    private Hand(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Hand init() {
        return new Hand(new ArrayList<>());
    }

    public int getHandSum() {
        int primitiveSum = getPrimitiveSum();
        if (isAddable()) {
            return primitiveSum + ACE_GAP;
        }
        return primitiveSum;
    }

    private boolean hasAce() {
        return playingCards.stream()
                .anyMatch(PlayingCard::isAce);
    }

    private boolean isAddable() {
        return hasAce() && (getPrimitiveSum() + ACE_GAP <= BLACKJACK_CONDITION);
    }

    private int getPrimitiveSum() {
        return playingCards.stream()
                .map(PlayingCard::getValue)
                .reduce(0, Integer::sum);
    }

    public boolean isNotBust() {
        return getHandSum() <= BLACKJACK_CONDITION;
    }

    public void addCard(final PlayingCard card) {
        playingCards.add(card);
    }

    public List<PlayingCard> getPlayingCards() {
        return Collections.unmodifiableList(playingCards);
    }

    public boolean isNotMaximum() {
        return getHandSum() != BLACKJACK_CONDITION;
    }

    public boolean isBlackJack() {
        return getHandSum() == BLACKJACK_CONDITION && playingCards.size() == INITIAL_DRAW;
    }
}
