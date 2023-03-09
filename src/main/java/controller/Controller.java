package controller;

import domain.BlackJackGame;
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
            BlackJackGame blackJackGame = ready();
            play(blackJackGame);
            end(blackJackGame);
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private BlackJackGame ready() {
        List<String> playerNames = inputView.askPlayerNames();
        Users users = Users.from(playerNames);
        BlackJackGame blackJackGame = BlackJackGame.of(users, new RandomCardShuffler());
        outputView.printInitMessage(playerNames);
        outputView.printDealerCardWithHidden(blackJackGame.getDealerCardWithHidden());
        outputView.printPlayerCards(blackJackGame.getPlayerToCard());
        return blackJackGame;
    }

    private void play(BlackJackGame blackJackGame) {
        List<Player> hittablePlayers = blackJackGame.getHittablePlayers();
        for (Player player : hittablePlayers) {
            askPlayerHitCommand(player, blackJackGame);
        }
        giveCardToDealer(blackJackGame);
    }

    private void askPlayerHitCommand(final Player player, final BlackJackGame blackJackGame) {
        String playerName = player.getName();
        while (player.isHittable() && inputView.askHitCommand(playerName)) {
            blackJackGame.giveCard(playerName);
            outputView.printEachPlayerCards(playerName, player.getCards());
        }
    }

    private void giveCardToDealer(BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerHittable()) {
            blackJackGame.giveCardToDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void end(BlackJackGame blackJackGame) {
        outputView.printDealerCardWithScore(blackJackGame.getDealerCards(), blackJackGame.getDealerScore());
        outputView.printPlayerCardWithScore(blackJackGame.getPlayerToCard(), blackJackGame.getPlayerToScore());
        outputView.printGameResult(blackJackGame.getResult());
    }
}
