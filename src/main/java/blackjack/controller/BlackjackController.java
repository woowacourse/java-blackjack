package blackjack.controller;

import static blackjack.view.InputView.NOT_RECEIVE_CARD;

import blackjack.domain.Result;
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
        dealer.settingCards();

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
        String answer = inputView.askReceiveMoreCard(player.getName());

        if (answer.equals(NOT_RECEIVE_CARD)) {
            outputView.printCurrentCards(player);
            return false;
        }
        dealer.giveACard(player);
        outputView.printCurrentCards(player);
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

        Map<Player, Result> playerResults = dealer.makePlayerResults();
        outputView.printGameResult(dealer.countSelfResults(playerResults), playerResults);
    }
}
