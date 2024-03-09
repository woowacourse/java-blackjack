package domain.participant;

import domain.PlayingCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.constant.GameOption.BLACKJACK_CONDITION;

public class Hand {
    private final List<PlayingCard> playingCards;

    private Hand(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Hand init() {
        return new Hand(new ArrayList<>());
    }

    public int getCardsNumberSum() {
        int result = 0;
        for (PlayingCard playingCard : playingCards) {
            result = playingCard.addValue(result);
        }

        return result;
    }

    public boolean isBurst() {
        return getCardsNumberSum() > BLACKJACK_CONDITION;
    }

    public void addCard(final PlayingCard card) {
        playingCards.add(card);
    }

    public List<PlayingCard> getPlayingCards() {
        return Collections.unmodifiableList(playingCards);
    }

    public boolean isBlackJack() {
        return getCardsNumberSum() == BLACKJACK_CONDITION;
    }
}
