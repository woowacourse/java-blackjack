package controller;

import domain.card.shuffler.RandomCardShuffler;
import domain.game.BlackJackGame;
import domain.user.Player;
import domain.user.Users;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        BlackJackGame blackJackGame = createBlackJackGame();
        outputView.printInitMessage(blackJackGame.getPlayerNames());
        outputView.printDealerCardWithHidden(blackJackGame.getDealerCardWithHidden());
        outputView.printPlayerCards(blackJackGame.getPlayerToCard());
        return blackJackGame;
    }

    private BlackJackGame createBlackJackGame() {
        List<String> playerNames = inputView.askPlayerNames();
        Users users = Users.from(playerNames);
        Map<String, Integer> playersBettingAmount = inputView.askPlayersBettingAmount(playerNames);
        for (Entry<String, Integer> nameToBettingAmount : playersBettingAmount.entrySet()) {
            users.bettingByName(nameToBettingAmount.getKey(), nameToBettingAmount.getValue());
        }
        return BlackJackGame.of(users, new RandomCardShuffler());
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
        while (player.isDrawable() && inputView.askHitCommand(playerName)) {
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
