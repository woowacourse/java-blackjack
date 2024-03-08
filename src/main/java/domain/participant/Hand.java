package domain.participant;

import domain.playingcard.PlayingCard;

import java.util.ArrayList;
import java.util.List;

import static domain.constant.GameOption.BLACKJACK_CONDITION;
import static java.util.Collections.unmodifiableList;

public class Hand {
    private final List<PlayingCard> playingCards;

    Hand(final List<PlayingCard> playingCards) {
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

    public boolean isBust() {
        return getCardsNumberSum() > BLACKJACK_CONDITION;
    }

    public void addCard(final PlayingCard card) {
        playingCards.add(card);
    }

    public List<PlayingCard> getPlayingCards() {
        return unmodifiableList(playingCards);
    }

    public boolean isBlackJack() {
        return getCardsNumberSum() == BLACKJACK_CONDITION;
    }
}
