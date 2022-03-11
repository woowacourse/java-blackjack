package blackjack.controller;

import static blackjack.view.InputView.requestMoreCardInput;
import static blackjack.view.OutputView.printDealerExtraCardInfo;
import static blackjack.view.OutputView.printPlayerBustInfo;
import static blackjack.view.OutputView.printPlayerCardsInfo;

import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultStatistics;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.InitialDistributionDto;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    public BlackjackGame initializeGame(List<String> playerNames) {
        return new BlackjackGame(new CardDeck(), playerNames);
    }

    public InitialDistributionDto getInitialDistribution(BlackjackGame game) {
        return new InitialDistributionDto(game.getParticipants());
    }

    public void distributeAllCards(BlackjackGame game) {
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            drawAllPlayerCards(player, game);
        }

        drawDealerCard(game);
    }

    private void drawAllPlayerCards(Player player, BlackjackGame game) {
        while (player.canReceive()) {
            if (!requestMoreCardInput(player.getName())) {
                return;
            }
            player.receiveCard(game.popCard());
            printPlayerCardsInfo(player);
        }
        printPlayerBustInfo();
    }

    private void drawDealerCard(BlackjackGame game) {
        if (game.giveCardToDealer()) {
            printDealerExtraCardInfo();
        }
    }

    public List<ResultStatistics> getGameResult(BlackjackGame game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();

        ResultStatistics dealerResult = ResultStatistics.of(dealer);
        Score dealerScore = dealer.getCurrentScore();

        if (dealerScore.toInt() > Score.BLACKJACK) {
            return getResultsOnDealerBust(players, dealerResult);
        }

        List<ResultStatistics> results = getResults(players, dealerResult, dealerScore);
        results.add(0, dealerResult);

        return results;
    }

    private List<ResultStatistics> getResultsOnDealerBust(List<Player> players, ResultStatistics dealerResult) {
        List<ResultStatistics> results = new ArrayList<>();
        for (Player player : players) {
            ResultStatistics playerResult = ResultStatistics.of(player);
            dealerResult.incrementCountOf(ResultType.LOSE);
            playerResult.incrementCountOf(ResultType.WIN);
            results.add(playerResult);
        }
        results.add(0, dealerResult);
        return results;
    }

    private List<ResultStatistics> getResults(List<Player> players, ResultStatistics dealerResult, Score dealerScore) {
        List<ResultStatistics> results = new ArrayList<>();

        for (Player player : players) {
            ResultStatistics playerResult = ResultStatistics.of(player);
            Score playerScore = player.getCurrentScore();

            if (playerScore.toInt() > Score.BLACKJACK) {
                dealerResult.incrementCountOf(ResultType.WIN);
                playerResult.incrementCountOf(ResultType.LOSE);
                results.add(playerResult);
                continue;
            }

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
        return results;
    }
}
