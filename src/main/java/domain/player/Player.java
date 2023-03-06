package domain.player;

import domain.card.Card;
import domain.card.PlayingCards;

import java.util.List;

public abstract class Player {
    private final PlayingCards playingCards;
    private final String name;

    protected Player(String name) {
        this.playingCards = new PlayingCards();
        this.name = name;
    }

    public void addCard(Card card) {
        playingCards.addCard(card);
    }

    public int getTotalScore() {
        return playingCards.getTotalScore();
    }

    public boolean isBurst() {
        return playingCards.isBurst();
    }

    public List<Card> getCards() {
        return playingCards.getCards();
    }

    public abstract void battle(Player player);

    public abstract List<Integer> getGameResult();

    public abstract boolean isNameEqualTo(String playerName);

    public String getName() {
        return this.name;
    }
}
