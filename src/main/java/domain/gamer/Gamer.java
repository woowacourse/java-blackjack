package domain.gamer;

import domain.card.Card;
import domain.card.PlayingCards;

public class Gamer {
    final PlayingCards playingCards;
    final String name;

    Gamer(PlayingCards playingCards, String name) {
        this.playingCards = playingCards;
        this.name = name;
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
