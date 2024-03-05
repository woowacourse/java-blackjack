package blackjack.domain;

public class CardGame {
    public void giveCard(final Player player, final Card card) {
        player.addCard(card);
    }
}
