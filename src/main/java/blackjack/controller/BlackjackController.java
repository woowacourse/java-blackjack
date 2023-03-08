package blackjack.controller;

import static blackjack.view.InputView.askReceiveMoreCard;
import static blackjack.view.InputView.receivePlayersName;
import static blackjack.view.OutputView.printCurrentCards;
import static blackjack.view.OutputView.printDealerDrawOneMoreCard;
import static blackjack.view.OutputView.printDistributeCardsMessage;
import static blackjack.view.OutputView.printGameResult;
import static blackjack.view.OutputView.printParticipantsInitCards;
import static blackjack.view.OutputView.printParticipantsLastCards;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Result;
import java.util.Map;

public class BlackjackController {

    private Players players;
    private Dealer dealer;

    public void run() {
        players = new Players(receivePlayersName());
        dealer = new Dealer();

        settingGame();
        for (Player player : players.getPlayers()) {
            play(player);
        }
        turnOfDealer();

        finishGame();
    }

    private void settingGame() {
        dealer.settingCards(players);

        printDistributeCardsMessage(dealer, players);
        printParticipantsInitCards(dealer, players);
    }

    private void play(Player player) {
        while (ask(player) && !player.isBust()) {
            // do nothing
        }
    }

    private boolean ask(Player player) {
        printCurrentCards(player);
        boolean receive = askReceiveMoreCard(player.getName());

        if (!receive) {
            printCurrentCards(player);
            return false;
        }
        dealer.giveCard(player);
        return true;
    }

    private void turnOfDealer() {
        while (dealer.canDraw()) {
            dealer.drawCard();
            printDealerDrawOneMoreCard(dealer);
        }
    }

    private void finishGame() {
        printParticipantsLastCards(dealer, players);

        Map<Player, Result> playerResults = players.makeResult(dealer.totalScore());
        printGameResult(Result.resultOfDealer(playerResults), playerResults);
    }
}
