package domain.gamer;

import domain.card.Deck;
import domain.card.PlayingCards;

public class Player {

    private final String name;
    private final int playerMoney;
    final PlayingCards playingCards;

    public Player(String name, int playerMoney, PlayingCards playingCards) {
        this.name = name;
        this.playerMoney = playerMoney;
        this.playingCards = playingCards;
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


