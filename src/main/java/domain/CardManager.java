package domain;

import java.util.List;

public class CardManager {

    private final Dealer dealer;
    private final List<Player> players;
    private final CardPack cardPack;

    public CardManager(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        this.cardPack = new CardPack();
    }

    public void distributeSetUpCards() {
        dealer.setUpCardDeck(cardPack.poll(), cardPack.poll());
        players.forEach(player -> player.setUpCardDeck(cardPack.poll(), cardPack.poll()));
    }
}
