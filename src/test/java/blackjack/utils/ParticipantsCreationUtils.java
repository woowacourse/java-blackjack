package blackjack.utils;

import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.HEART;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class ParticipantsCreationUtils {

    private ParticipantsCreationUtils() {
    }

    public static Dealer createDealerWithDenominations(Denomination... denominations) {
        Dealer dealer = new Dealer();
        for (Denomination denomination : denominations) {
            dealer.receiveCard(new Card(denomination, HEART));
        }
        return dealer;
    }

    public static Player createPlayerWithDenominations(String playerName, Denomination... denominations) {
        Player player = new Player(playerName);
        for (Denomination denomination : denominations) {
            player.receiveCard(new Card(denomination, CLOVER));
        }
        return player;
    }
}
