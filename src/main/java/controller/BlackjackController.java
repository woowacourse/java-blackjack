package controller;

import domain.BlackjackGame;
import domain.Command;
import domain.Player;
import domain.PlayerName;
import domain.Players;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

// TODO: 2023/02/28 결과 계산하기
// TODO: 2023/02/28 출력
public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = createPlayers();
        BlackjackGame blackjackGame = new BlackjackGame(players);
        blackjackGame.giveInitCards();

        outputView.printInitCards(blackjackGame.getDealer(), players);

        for (Player player : players.getPlayers()) {
            requestMoreCard(blackjackGame, player);
        }
        blackjackGame.giveAdditionalCardToDealer();
        blackjackGame.result();
    }

    private Players createPlayers() {
        return new Players(readNames().stream()
                .map(PlayerName::new)
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    private List<String> readNames() {
        return inputView.requestPlayerNames();
    }

    private void requestMoreCard(BlackjackGame blackjackGame, Player player) {
        while (!player.isBusted() && isHitCommand(player.getName())) {
            blackjackGame.giveCardTo(player);
            outputView.printPlayerCards(player);
        }

        if (player.isBusted()) {
            outputView.printBusted(player.getName());
            return;
        }

        outputView.printPlayerCards(player);
    }

    private boolean isHitCommand(String name) {
        return Command.from(inputView.requestMoreCard(name)) != Command.HOLD;
    }
}
