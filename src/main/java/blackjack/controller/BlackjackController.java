package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.view.dto.RewardDTO;
import blackjack.domain.result.Rewards;
import blackjack.domain.user.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BlackjackController {

    private BlackjackGame blackjackGame;

    public void run() {
        ready(generatePlayers());
        Players players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();
        printNewCards(players, dealer);
        giveAdditionalCards(players, dealer);
        printFinalResults(dealer, players);
    }

    private void ready(Players players) {
        blackjackGame = new BlackjackGame(players);
        blackjackGame.giveInitialCardsToUsers();
        OutputView.printReadyMessage(players.getPlayers().stream()
                .map(Player::getPlayerName)
                .collect(Collectors.toList())
        );
        setBetAmount(players);
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
        Rewards rewards = blackjackGame.getRewards();
        OutputView.printPrize(RewardDTO.of(rewards));
    }

    private void setBetAmount(Players players) {
        for (Player player : players.getPlayers()) {
            player.setBetAmount(InputView.readBetAmount(player.getPlayerName()));
        }
    }

    private Players generatePlayers() {
        Names names = new Names(InputView.inputPeopleNames());
        return Players.from(names);
    }

    private void giveAdditionalCardsToPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            String answer = InputView.askAdditionalCard(player.getPlayerName());
            giveAdditionalCardToPlayer(answer, player);
        }
    }

    public void giveAdditionalCardsToDealer(Dealer dealer) {
        while (dealer.hasUnderMinimumScore()) {
            OutputView.printDealerOneMore();
            blackjackGame.updateCard(dealer);
        }
    }

    private void giveAdditionalCardToPlayer(String answer, Player player) {
        while (GameCommand.isContinue(answer) && !player.isBust()) {
            blackjackGame.updateCard(player);
            OutputView.printPlayerCurrentCards(player);
            answer = InputView.askAdditionalCard(player.getPlayerName());
        }
        stopServingCard(player);
    }

    private void stopServingCard(Player player) {
        if (player.isBust()) {
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
                    .filter(gameCommand -> gameCommand.input.equalsIgnoreCase(input.toUpperCase()))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("'y' 또는 'n' 중에 입력하세요."));
            return CONTINUE.input.equals(input);
        }
    }
}
