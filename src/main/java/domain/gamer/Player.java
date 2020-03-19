package domain.gamer;

import domain.card.Deck;
import domain.card.PlayingCards;

public class Player {
    private final String name;
    private int playerBettingMoney;
    final PlayingCards playingCards;

    public Player(String name, int playerBettingMoney, PlayingCards playingCards) {
        this.name = name;
        this.playerBettingMoney = playerBettingMoney;
        this.playingCards = playingCards;
    }

    public Player money(int playerBettingMoney) {
        return new Player(name, playerBettingMoney, playingCards);
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

    public int getPlayerBettingMoney() {
        return playerBettingMoney;
    }
}


