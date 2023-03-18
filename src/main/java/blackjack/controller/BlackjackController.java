package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.result.Rewards;
import blackjack.domain.user.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.RewardDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    private BlackjackGame blackjackGame;

    public void run() {
        ready();
        Players players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();
        printNewCards(players, dealer);
        giveAdditionalCards(players, dealer);
        printFinalResults(dealer, players);
    }

    private void ready() {
        Players players = generatePlayers();
        blackjackGame = new BlackjackGame(players);
        blackjackGame.giveInitialCardsToUsers();
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
        Rewards rewards = blackjackGame.getRewards();
        OutputView.printPrize(RewardDTO.of(rewards));
    }

    private Players generatePlayers() {
        Names names = new Names(InputView.inputPeopleNames());
        List<Player> players = addBetAmountToPlayers(names);
        return Players.from(players);
    }

    private List<Player> addBetAmountToPlayers(Names playerNames) {
        List<Player> players = new ArrayList<>();
        for (Name name : playerNames.getNames()) {
            players.add(new Player(name, InputView.readBetAmount(name.getName())));
        }
        return players;
    }

    private void giveAdditionalCardsToPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            giveAdditionalCardToPlayer(player);
        }
    }

    public void giveAdditionalCardsToDealer(Dealer dealer) {
        while (dealer.hasUnderMinimumScore()) {
            OutputView.printDealerOneMore();
            blackjackGame.updateCard(dealer);
        }
    }

    private void giveAdditionalCardToPlayer(Player player) {
        while (!player.isBust() && isContinue(player.getPlayerName())) {
            blackjackGame.updateCard(player);
            OutputView.printPlayerCurrentCards(player);
        }
        stopServingCard(player);
    }

    private boolean isContinue(String playerName) {
        return GameCommand.isContinue(InputView.askAdditionalCard(playerName));
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
