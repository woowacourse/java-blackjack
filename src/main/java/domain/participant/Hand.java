package domain.participant;

import domain.playingcard.PlayingCard;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Hand {
    private final List<PlayingCard> playingCards;

    public Hand(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Hand init() {
        return new Hand(new ArrayList<>());
    }

    public void addCard(final PlayingCard card) {
        playingCards.add(card);
    }

    public Score getTotalScore() {
        return Score.of(playingCards);
    }

    public boolean isBlackJack() {
        if (playingCards.size() != 2) {
            return false;
        }

        PlayingCard firstCard = playingCards.get(0);
        PlayingCard secondCard = playingCards.get(1);
        return (firstCard.isAce() && secondCard.isTenValueCard())
                || (firstCard.isTenValueCard() || secondCard.isAce());
    }

    public List<PlayingCard> getPlayingCards() {
        return unmodifiableList(playingCards);
    }
}
