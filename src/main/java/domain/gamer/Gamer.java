package domain.gamer;

import domain.card.Card;
import domain.card.PlayingCards;

public class Gamer {
    final PlayingCards playingCards;

    Gamer(PlayingCards playingCards) {
        this.playingCards = playingCards;
    }

    public void addCard(Card card) {
        playingCards.add(card);
    }

    public int countCards() {
        return playingCards.size();
    }

    public boolean isBust() {
        return playingCards.isBust();
    }
}
