package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCards {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_DIFFERENCE_UNIT = 10;
    private final List<PlayingCard> playingCards;

    public PlayingCards() {
        playingCards = new ArrayList<>();
    }

    public void addCard(PlayingCard playingCard) {
        playingCards.add(playingCard);
    }

    public int getResultWithPeekCard(final PlayingCard peekedCard) {
        return getCardSumWithPeek(peekedCard.getScore());
    }

    public List<PlayingCard> getPlayingCards() {
        return Collections.unmodifiableList(playingCards);
    }

    public int getCardSum() {
        int currentSum = getCurrentSum();
        if (hasAce() && currentSum > BLACKJACK_NUMBER) {
            final PlayingCard aceCard = getAce();
            playingCards.remove(aceCard);
            playingCards.add(new PlayingCard(aceCard.getSuit(), Denomination.ONE));
            return getCurrentSum();
        }
        if (hasOne() && currentSum <= BLACKJACK_NUMBER - ACE_DIFFERENCE_UNIT) {
            final PlayingCard oneCard = getOne();
            playingCards.remove(oneCard);
            playingCards.add(new PlayingCard(oneCard.getSuit(), Denomination.ACE));
        }
        return currentSum;
    }

    private int getCurrentSum() {
        return playingCards.stream()
            .mapToInt(playingCard -> playingCard.getDenomination().getScore())
            .sum();
    }

    private boolean hasAce() {
        return playingCards.stream()
            .anyMatch(PlayingCard::isAce);
    }

    private boolean hasOne() {
        return playingCards.stream()
            .anyMatch(PlayingCard::isOne);
    }


    private PlayingCard getAce() {
        return playingCards.stream()
            .filter(PlayingCard::isAce)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("알 수 없는 오류가 발생했습니다."));
    }

    private PlayingCard getOne() {
        return playingCards.stream()
            .filter(PlayingCard::isOne)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("알 수 없는 오류가 발생했습니다."));
    }

    public int getCardSumWithPeek(final int peekedScore) {
        int currentSum = getCurrentSum() + peekedScore;
        if (hasAce() && currentSum > BLACKJACK_NUMBER) {
            return currentSum - ACE_DIFFERENCE_UNIT;
        }
        return currentSum;
    }
}
