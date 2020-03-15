package domain.gamer;

import domain.card.Card;
import domain.card.PlayingCards;

import java.util.List;

public class Gamer {
    final PlayingCards playingCards;
    private final String name;

    public Gamer(PlayingCards playingCards, String name) {
        this.playingCards = playingCards;
        this.name = name;
    }

    public void addCard(Card card) {
        playingCards.add(card);
    }

    public int calculateScore() {
        return playingCards.calculateScore();
    }

    public boolean isBust() {
        return playingCards.isBust();
    }

    public boolean isNotBust() {return playingCards.isNotBust();}

    public String getName() {
        return name;
    }

    public List<Card> getPlayingCards() {
        return playingCards.getCards();
    }

    int countCards() {
        return playingCards.size();
    }
}
