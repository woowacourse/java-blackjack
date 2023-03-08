package blackjack.controller;

import blackjack.domain.result.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private Dealer dealer;

    public void run() {
        Players players = new Players(inputView.receivePlayersName());
        dealer = new Dealer(players);

        settingGame();

        for (Player player : dealer.getPlayers()) {
            play(player);
        }
        turnOfDealer();
        finishGame();
    }

    private void settingGame() {
        dealer.settingSelfCards();
        dealer.settingPlayersCards();

        outputView.printDistributeCardsMessage(dealer.getPlayers());
        outputView.printDealerInitCards(dealer.showOneCard());
        outputView.printPlayersInitCards(dealer.getPlayers());
    }

    private void play(Player player) {
        while (ask(player) && !player.isBust()) {
            // do nothing
        }
    }

    private boolean ask(Player player) {
        outputView.printCurrentCards(player);
        boolean receive = inputView.askReceiveMoreCard(player.getName());

        if (!receive) {
            outputView.printCurrentCards(player);
            return false;
        }
        dealer.giveACard(player);
        return true;
    }

    private void turnOfDealer() {
        while (dealer.canDraw()) {
            dealer.drawACard();
            outputView.printDealerDrawOneMoreCard();
        }
    }

    private void finishGame() {
        outputView.printDealerLastCards(dealer.getCards(), dealer.calculateTotalScore());
        outputView.printPlayerLastCards(dealer.getPlayers());

        Map<Player, Result> playerResults = dealer.requestResultToPlayers();
        outputView.printGameResult(dealer.countSelfResults(playerResults), playerResults);
    }
}
