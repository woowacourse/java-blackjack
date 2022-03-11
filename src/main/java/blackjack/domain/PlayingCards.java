package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCards {
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
        if (hasAce() && currentSum > 21 ) {
            final PlayingCard aceCard = getAce();
            playingCards.remove(aceCard);
            playingCards.add(new PlayingCard(aceCard.getSuit(), Denomination.ONE));
            return getCurrentSum();
        }
        return currentSum;
    }

    private PlayingCard getAce() {
        return playingCards.stream()
            .filter(PlayingCard::isAce)
            .findFirst()
            .orElseThrow(()-> new RuntimeException("알 수 없는 오류가 발생했습니다."));
    }

    public int getCardSumWithPeek(final int peekedScore) {
        int currentSum = getCurrentSum() + peekedScore;
        if (hasAce() && currentSum > 21 ) {
            return currentSum-10;
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
}
