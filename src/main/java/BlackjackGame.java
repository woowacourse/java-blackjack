import card.Card;
import participant.Dealer;
import participant.Player;

public class BlackjackGame {

    public BlackjackGame() {
    }

    public void prepareDealer(final Dealer dealer, final Card card1, final Card card2) {
        dealer.prepareGame(card1, card2);
    }

    public void preparePlayerCards(final Player player, final Card card1, final Card card2) {
        player.prepareGame(card1, card2);
    }
}
