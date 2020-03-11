package domain.Gamer;

import domain.Card.Card;
import domain.Card.PlayingCards;

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
}
