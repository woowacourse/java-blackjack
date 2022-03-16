package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCards {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int NO_COUNT = 0;
    private static final int ACE_DECREASE_UNIT = 1;
    private static final int ACE_DIFFERENCE_UNIT = 10;

    private final List<PlayingCard> playingCards;

    public PlayingCards() {
        playingCards = new ArrayList<>();
    }

    public void addCard(PlayingCard playingCard) {
        playingCards.add(playingCard);
    }

    public boolean isBlackjack() {
        return isCardSumBlackjack() && hasCardJustTwo();
    }

    private boolean isCardSumBlackjack() {
        return getCardSum() == BLACKJACK_NUMBER;
    }

    private boolean hasCardJustTwo() {
        return playingCards.size() == 2;
    }

    public boolean isBurst() {
        return getCardSum() > BLACKJACK_NUMBER;
    }

    public boolean isNotFinishedWithBound(final int boundNumber) {
        return getCardSum() < boundNumber;
    }

    public int getCardSum() {
        return adjustSumByAce(getCurrentSum());
    }

    private int adjustSumByAce(int currentSum) {
        int aceCount = getAceCount();
        while (currentSum > BLACKJACK_NUMBER && aceCount > NO_COUNT) {
            aceCount -= ACE_DECREASE_UNIT;
            currentSum -= ACE_DIFFERENCE_UNIT;
        }
        return currentSum;
    }

    public List<PlayingCard> getPlayingCards() {
        return Collections.unmodifiableList(playingCards);
    }

    private int getCurrentSum() {
        return playingCards.stream()
            .mapToInt(playingCard -> playingCard.getDenomination().getScore())
            .sum();
    }

    private int getAceCount() {
        return (int) playingCards.stream()
            .filter(PlayingCard::isAce)
            .count();
    }
}
