package blackjack.controller;

import static blackjack.view.InputView.requestMorePlayerCardInput;
import static blackjack.view.OutputView.printPlayerBustInfo;
import static blackjack.view.OutputView.printPlayerCardsInfo;

import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultStatistics;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public BlackjackGame initializeGame(List<String> playerNames) {
        return new BlackjackGame(new CardDeck(), playerNames);
    }

    public void givePlayerCards(Player player, BlackjackGame game) {
        while (player.canReceive()) {
            if (!requestMorePlayerCardInput(player.getName())) {
                return;
            }
            player.receiveCard(game.popCard());
            printPlayerCardsInfo(player);
        }

        printPlayerBustInfo();
    }

    public List<ResultStatistics> finishGame(BlackjackGame game) {
        List<ResultStatistics> results = new ArrayList<>();

        Dealer dealer = game.getDealer();
        List<Player> players = game.getParticipants();

        ResultStatistics dealerResult = ResultStatistics.of(dealer.getName());
        Score dealerScore = dealer.getCurrentScore();

        for (Player player : players) {
            ResultStatistics playerResult = ResultStatistics.of(player.getName());
            Score playerScore = player.getCurrentScore();

            int compareResult = dealerScore.compareTo(playerScore);

            if (compareResult > 0) {
                dealerResult.incrementCountOf(ResultType.WIN);
                playerResult.incrementCountOf(ResultType.LOSE);
            }
            if (compareResult == 0) {
                dealerResult.incrementCountOf(ResultType.DRAW);
                playerResult.incrementCountOf(ResultType.DRAW);
            }
            if (compareResult < 0) {
                dealerResult.incrementCountOf(ResultType.LOSE);
                playerResult.incrementCountOf(ResultType.WIN);
            }
            results.add(playerResult);
        }

        results.add(0, dealerResult);

        return results;
    }
}

