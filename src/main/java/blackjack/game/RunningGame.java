package blackjack.game;

import blackjack.domain.player.*;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ResultMatcher;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RunningGame {
    public void run() {
        List<Name> names = (InputView.repeat(() -> new Names(InputView.inputPeopleNames()))).getNames();
        BlackJackGame blackJackGame = new BlackJackGame(names);

        Players players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();

        OutputView.printReadyMessage(names);
        printUserFirstCards(players.getPlayers(), dealer);

        giveCardOrNotForPlayers(blackJackGame, players);
        giveCardOrNotForDealer(blackJackGame, dealer);

        OutputView.printResults(dealer, players.getPlayers());
        printScore(players, dealer);
    }

    private void printUserFirstCards(List<Player> players, Dealer dealer) {
        OutputView.printDealerFirstCard(dealer);
        for (Player player : players) {
            OutputView.printPlayerCurrentCards(player);
        }
        System.out.println();
    }

    private void giveCardOrNotForPlayers(BlackJackGame blackJackGame, Players players) {
        for (Player player : players.getPlayers()) {
            giveCardOrNotForPlayer(blackJackGame, player);
        }
    }

    private void giveCardOrNotForPlayer(BlackJackGame blackJackGame, Player player) {
        while (player.isUnderLimit() &&
                Command.of(InputView.repeat(() -> InputView.askAdditionalCard(player.getPlayerName()))).isYes()) {
            blackJackGame.giveOneMoreCard(player);
            OutputView.printPlayerCurrentCards(player);
        }
    }

    private void giveCardOrNotForDealer(BlackJackGame blackJackGame, Dealer dealer) {
        while (dealer.isUnderLimit()) {
            OutputView.printMessageDealerOneMore();
            blackJackGame.giveOneMoreCard(dealer);
        }
    }

    private void printScore(Players players, Dealer dealer) {
        GameResult gameResult = new GameResult(players.getPlayers(), dealer);
        Map<Player, ResultMatcher> playersScore = gameResult.getPlayerGameResults();
        EnumMap<ResultMatcher, Integer> dealerScore = gameResult.calculateDealerScore(playersScore);
        OutputView.printScore(playersScore, dealerScore);
    }
}
