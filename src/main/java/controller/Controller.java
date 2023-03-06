package controller;

import domain.BlackJack;
import domain.Users;
import domain.user.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private BlackJack blackJack;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            ready();
            play();
            end();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void ready() {
        List<String> playerNames = inputView.askPlayerNames();
        Users users = Users.from(playerNames);
        blackJack = BlackJack.of(users, cards -> {
        });
        outputView.printInitMessage(playerNames);
        outputView.printDealerCardWithHidden(blackJack.getDealerCardWithHidden());
        outputView.printPlayerCards(blackJack.getPlayerToCard());
    }

    private void play() {
        List<Player> hittablePlayers = blackJack.getHittablePlayers();
        for (Player player : hittablePlayers) {
            askPlayerHitCommand(player);
        }
        giveCardToDealer();
    }

    private void askPlayerHitCommand(final Player player) {
        String playerName = player.getName();
        while (player.isHittable() && inputView.askHitCommand(playerName)) {
            blackJack.giveCard(playerName);
            outputView.printEachPlayerCards(playerName, player.getCards());
        }
    }

    private void giveCardToDealer() {
        while (blackJack.isDealerHittable()) {
            blackJack.giveCardToDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void end() {
        outputView.printDealerCardWithScore(blackJack.getDealerCards(), blackJack.getDealerScore());
        outputView.printPlayerCardWithScore(blackJack.getPlayerToCard(), blackJack.getPlayerToScore());
        outputView.printGameResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResults());
    }
}
