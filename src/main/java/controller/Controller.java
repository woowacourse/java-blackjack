package controller;

import domain.card.shuffler.RandomCardShuffler;
import domain.game.BlackJackGame;
import domain.user.Player;
import domain.user.PlayerName;
import domain.user.Users;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        PlayerName.validateDuplication(playerNames);
        Map<String, Integer> playerNameToBettingAmount = new LinkedHashMap<>();
        for (String playerName : playerNames) {
            int bettingAmount = inputView.askPlayerBettingAmount(playerName);
            playerNameToBettingAmount.put(playerName, bettingAmount);
        }
        Users users = Users.from(playerNameToBettingAmount);
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
