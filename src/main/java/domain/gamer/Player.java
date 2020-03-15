package domain.gamer;

import domain.card.Deck;
import domain.card.PlayingCards;

public class Player {

    final PlayingCards playingCards;
    private final String name;

    public Player(PlayingCards playingCards, String name) {
        this.playingCards = playingCards;
        this.name = name;
    }

    public void addCard(Deck deck) {
        playingCards.add(deck.handOutCard());
    }

    public int calculateScore() {
        return playingCards.calculateScore();
    }

    public int countCards() {
        return playingCards.size();
    }

    public boolean isBust() {
        return playingCards.isBust();
    }

    public String getName() {
        return name;
    }

    public PlayingCards getPlayingCards() {
        return playingCards;
    }
}


