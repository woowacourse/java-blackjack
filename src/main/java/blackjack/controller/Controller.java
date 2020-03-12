package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Controller {

    public void run() {
        List<String> names = InputView.enterNames();
        Players players = new Players(names);
        Dealer dealer = new Dealer();
        CardDeck deck = new CardDeck();

        dealFirstCards(deck, dealer, players);
        OutputView.printInitialStatus(players, dealer);

        dealAdditionalCards(deck, dealer, players);

        OutputView.printFinalStatus(players, dealer);

        List<PlayerResult> playerResults = players.createPlayerResults(dealer);
        DealerResult dealerResult = dealer.createDealerResult(playerResults);
        OutputView.printFinalResult(dealerResult, playerResults);
    }

    private void dealFirstCards(CardDeck deck, Dealer dealer, Players players) {
        deck.dealFirstCards(dealer);
        for (Player player : players.getPlayers()) {
            deck.dealFirstCards(player);
        }
    }

    private void dealAdditionalCards(CardDeck deck, Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            while (player.canGetMoreCard()) {
                String reply = InputView.selectYesOrNo(player.getName());
                if (deck.dealAdditionalCard(player, reply)) {
                    OutputView.printCardsStatus(player.getName(), player.showCards());
                    continue;
                }
                break;
            }
        }

        while (dealer.canGetMoreCard()) {
            deck.dealAdditionalCard(dealer);
            OutputView.printDealerGetMoreCard(Dealer.LOWER_BOUND);
        }
    }
}
