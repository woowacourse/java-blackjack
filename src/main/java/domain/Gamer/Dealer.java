package domain.Gamer;

import domain.Card.Card;
import domain.Card.PlayingCards;

public class Dealer extends Gamer {
    private final PlayingCards playingCards;

    Dealer(PlayingCards playingCards) {
        this.playingCards = playingCards;
    }

    public void addCard(Card card) {
        playingCards.add(card);
    }

    public int countCards() {
        return playingCards.size();
    }
}
