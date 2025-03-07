package controller;

import domain.AnswerCommand;
import domain.GameManager;
import domain.card.RandomCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.GamerGenerator;
import domain.gamer.Player;
import java.util.List;
import java.util.Objects;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void gameStart() {
        final List<String> playerNames = inputView.readPlayers();
        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final Dealer dealer = GamerGenerator.generateDealer(randomCardGenerator);
        final List<Player> players = GamerGenerator.generatePlayer(playerNames, randomCardGenerator);
        final GameManager gameManager = GameManager.create(dealer, players);
        gameManager.giveCardToGamer(dealer);
        gameManager.giveCardToGamer(dealer);
        for (Player player : players) {
            gameManager.giveCardToGamer(player);
            gameManager.giveCardToGamer(player);
        }
        outputView.printDealerAndPlayersCards(dealer, players);

        final int size = gameManager.calculatePlayerSize();
        for (int index = 0; index < size; index++) {
            final Player player = gameManager.findPlayerByIndex(index);
            if (!gameManager.isAbleToHit(player)) {
                continue;
            }
            AnswerCommand answerCommand = inputView.readAnswer(player.getName());
            outputView.printPlayerCards(player);
            while (Objects.equals(answerCommand, AnswerCommand.YES) && gameManager.isAbleToHit(player)) {
                answerCommand = inputView.readAnswer(player.getName());
                if (Objects.equals(answerCommand, AnswerCommand.NO)) {
                    continue;
                }
                player.receiveCard();
            }
        }

        final int count = gameManager.giveCardsToDealer();
        if (count > 0) {
            outputView.printDealerReceivedCardCount(count);
        }

        outputView.printGamerCardsAndScore(dealer, players);
        outputView.printGameResult(gameManager.calculateDealerGameResult(), gameManager.calculatePlayerGameResult());
    }



}
