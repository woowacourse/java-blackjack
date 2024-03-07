package blackjack.domain;

import java.util.List;

public class CardGame {
    private final CardDeck cardDeck;

    public CardGame() {
        this.cardDeck = new CardDeck();
    }

    public void giveCard(final Player player) {
        player.addCards(cardDeck.draw());
    }

    // TODO: 이름 수정
    public void giveTwoCardsEachPlayer(final List<Player> players) {
        for (Player player : players) {
            giveCard(player);
            giveCard(player);
        }
    }
}
