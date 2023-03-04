package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Cards;
import blackjack.domain.factory.PlayersFactory;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Names;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.HashMap;

public class BlackjackController {
    private Players players;
    private Dealer dealer;

    public void run() {
        ready();
        printNewCards();
        giveAdditionalCards();
        printFinalResults();
    }

    private void ready() {
        getReady();
        BlackjackGame.giveFirstCards(players, dealer);
    }

    private void printNewCards() {
        OutputView.printDealerCurrentCards(dealer);
        OutputView.printPlayersCurrentCards(players);
    }

    private void giveAdditionalCards() {
        giveAdditionalCardsToPlayers();
        giveAdditionalCardToDealer();
    }

    private void printFinalResults() {
        OutputView.printScore(dealer, players);
        printResults();
    }

    private void getReady() {
        Names names = new Names(InputView.inputPeopleNames());
        this.players = PlayersFactory.of(names);
        this.dealer = new Dealer();
        OutputView.printReadyMessage(names.getNames());
    }

    private void printResults() {
        int dealerScore = dealer.getTotalScore();
        HashMap<Player, Result> playerResults = new HashMap<>();
        HashMap<Result, Integer> dealerResults = initializedResults();
        BlackjackGame.calculateResults(dealerScore, players, playerResults, dealerResults);
        OutputView.printResults(playerResults, dealerResults);
    }

    private HashMap<Result, Integer> initializedResults() {
        HashMap<Result, Integer> dealerResults = new HashMap<>();
        Arrays.stream(Result.values())
                .forEach(result -> dealerResults.put(result, 0));
        return dealerResults;
    }

    private void giveAdditionalCardsToPlayers() {
        for (Player player : players.getPlayers()) {
            String answer = InputView.askAdditionalCard(player.getPlayerName());
            giveAdditionalCard(answer, player);
        }
    }

    private void giveAdditionalCard(String answer, Player player) {
        while (GameCommand.isContinue(answer) && player.isUnderLimit()) {
            player.updateCardScore(Cards.giveFirstCard());
            OutputView.printPlayerCurrentCards(player);
            answer = InputView.askAdditionalCard(player.getPlayerName());
        }
        stopServingCard(player);
    }

    public void giveAdditionalCardToDealer() {
        while (dealer.isUnderLimit()) {
            OutputView.printDealerOneMore();
            dealer.updateCardScore(Cards.giveFirstCard());
        }
    }

    private void stopServingCard(Player player) {
        if (!player.isUnderLimit()) {
            OutputView.printScoreUnderLimit();
        }
        OutputView.printPlayerCurrentCards(player);
    }

    private enum GameCommand {
        CONTINUE("y"),
        QUIT("n");

        private final String input;

        GameCommand(String input) {
            this.input = input;
        }

        public static boolean isContinue(String input) {
            Arrays.stream(GameCommand.values())
                    .filter(gameCommand -> gameCommand.input.equals(input))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("'y' 또는 'n' 중에 입력하세요."));
            return CONTINUE.input.equals(input);
        }
    }
}
