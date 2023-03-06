package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Cards;
import blackjack.domain.factory.PlayersFactory;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Names;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final int PLAYERS_RESULTS = 0;
    private static final int DEALER_RESULTS = 1;

    private BlackjackGame blackjackGame;

    public void run() {
        Players players = generatePlayers();
        Dealer dealer = new Dealer();
        ready(players, dealer);
        printNewCards(players, dealer);
        giveAdditionalCards(players, dealer);
        printFinalResults(dealer, players);
    }

    private Players generatePlayers() {
        Names names = new Names(InputView.inputPeopleNames());
        return PlayersFactory.of(names);
    }

    private void ready(Players players, Dealer dealer) {
        blackjackGame = new BlackjackGame(players, dealer);
        blackjackGame.giveInitializedCards();
        OutputView.printReadyMessage(players.getPlayers().stream()
                .map(Player::getPlayerName)
                .collect(Collectors.toList())
        );
    }

    private void printNewCards(Players players, Dealer dealer) {
        OutputView.printDealerCurrentCards(dealer);
        OutputView.printPlayersCurrentCards(players);
    }

    private void giveAdditionalCards(Players players, Dealer dealer) {
        giveAdditionalCardsToPlayers(players);
        giveAdditionalCardsToDealer(dealer);
    }

    private void printFinalResults(Dealer dealer, Players players) {
        OutputView.printScore(dealer, players);
        List<HashMap> results = blackjackGame.getResults();
        OutputView.printDealerResults(results.get(DEALER_RESULTS));
        OutputView.printPlayersResults(results.get(PLAYERS_RESULTS));
    }

    private void stopServingCard(Player player) {
        if (!player.isUnderLimit()) {
            OutputView.printScoreUnderLimit();
        }
        OutputView.printPlayerCurrentCards(player);
    }

    private void giveAdditionalCardsToPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            String answer = InputView.askAdditionalCard(player.getPlayerName());
            giveAdditionalCard(answer, player);
        }
    }

    public void giveAdditionalCardsToDealer(Dealer dealer) {
        while (dealer.isUnderLimit()) {
            OutputView.printDealerOneMore();
            dealer.updateCardScore(Cards.giveFirstCard());
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
