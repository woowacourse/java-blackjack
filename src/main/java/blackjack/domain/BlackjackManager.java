package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

public class BlackjackManager {

    private BlackjackManager() {
    }

    public static void initGame(Players players, Dealer dealer) {
        dealer.receiveCard(dealer.giveCard());
        dealer.receiveCard(dealer.giveCard());
        players.initTwoCardsByDealer(dealer);
    }
}
