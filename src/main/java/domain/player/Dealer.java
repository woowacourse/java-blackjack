package domain.player;

import domain.card.GameCards;

public class Dealer extends Player {
    private final GameCards deck;

    public Dealer(String name, int amount) {
        super(name);
        this.deck = new GameCards(amount);
        this.deck.shuffle();
    }

    public void giveCard(Player player) {
        player.addCard(deck.drawCard());
    }

    public boolean isTotalValue16OrLess() {
        return getTotalValue() <= 16;
    }

    public String getFirstCardStatus() {
        return this.cardStatus.getFirstCardInfo();
    }
}
