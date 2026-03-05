package domain.player;

import domain.card.GameCards;

public class Dealer extends Player {
    private final GameCards cards;

    public Dealer(String name, int amount) {
        super(name);
        this.cards = new GameCards(amount);
        this.cards.shuffle();
    }

    public void giveCard(Player player) {
        player.addCard(cards.drawCard());
    }
}
