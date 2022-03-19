package blackjack;

import blackjack.user.Dealer;
import blackjack.user.Participant;
import blackjack.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackController {

    private void distributeInitCards(Dealer dealer, Players players, Deck deck) {
        dealer.drawAdditionalCard(deck);
        players.drawAdditionalCard(deck);
    }

    private void openInitialCards(Dealer dealer, Players players) {
        OutputView.printInitialDistributionEndMessage(dealer.getName(), players.getNames());
        OutputView.printCards(dealer.getName(), dealer.pickOpenCards());
        for (Object player : players) {
            OutputView.printCards(((Player)player).getName(), ((Player)player).pickOpenCards());
        }
    }

    private void distributeAdditionCards(Dealer dealer, Players players, Deck deck) {

    }

    public void run() {
        Deck deck = Deck.makeRandomShuffledDeck();
        Dealer dealer = Dealer.generate();
        Players players = Players.generateWithNames(InputView.enterPlayerNames());

        distributeInitCards(dealer, players, deck);
        openInitialCards(dealer, players);
        //distributeAdditionCards(dealer, players, deck);
    }
}
