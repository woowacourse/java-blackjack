package blackjack.domain;

import java.util.List;

public class CardGame {
    private final CardDeck cardDeck;

    public CardGame() {
        this.cardDeck = new CardDeck();
    }

    public void giveCard(final Player player, final Card card) {
        player.addCards(card);
    }

    // TODO: 이름 수정
    public void giveTwoCardsEachPlayer(final List<Player> players) {
        for (Player player : players) {
            // TODO: 개선 필요
            giveCard(player, cardDeck.draw());
            giveCard(player, cardDeck.draw());
        }
    }
}
