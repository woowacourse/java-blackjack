package controller;

import domain.BlackJack;
import domain.Users;
import domain.shuffler.RandomCardShuffler;
import domain.user.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            BlackJack blackJack = ready();
            play(blackJack);
            end(blackJack);
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private BlackJack ready() {
        List<String> playerNames = inputView.askPlayerNames();
        Users users = Users.from(playerNames);
        BlackJack blackJack = BlackJack.of(users, new RandomCardShuffler());
        outputView.printInitMessage(playerNames);
        outputView.printDealerCardWithHidden(blackJack.getDealerCardWithHidden());
        outputView.printPlayerCards(blackJack.getPlayerToCard());
        return blackJack;
    }

    private void play(BlackJack blackJack) {
        List<Player> hittablePlayers = blackJack.getHittablePlayers();
        for (Player player : hittablePlayers) {
            askPlayerHitCommand(player, blackJack);
        }
        giveCardToDealer(blackJack);
    }

    private void askPlayerHitCommand(final Player player, final BlackJack blackJack) {
        String playerName = player.getName();
        while (player.isHittable() && inputView.askHitCommand(playerName)) {
            blackJack.giveCard(playerName);
            outputView.printEachPlayerCards(playerName, player.getCards());
        }
    }

    private void giveCardToDealer(BlackJack blackJack) {
        while (blackJack.isDealerHittable()) {
            blackJack.giveCardToDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void end(BlackJack blackJack) {
        outputView.printDealerCardWithScore(blackJack.getDealerCards(), blackJack.getDealerScore());
        outputView.printPlayerCardWithScore(blackJack.getPlayerToCard(), blackJack.getPlayerToScore());
        outputView.printGameResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResults());
    }
}
