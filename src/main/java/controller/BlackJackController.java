package controller;

import domain.GameManager;
import domain.card.CardGenerator;
import domain.card.CardGroup;
import domain.card.RandomCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.GamerGenerator;

import java.util.List;

import domain.gamer.Player;
import util.LoopTemplate;
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
        final List<String> playersNames = requestPlayerNames();
        final CardGenerator randomCardGenerator = new RandomCardGenerator();
        final GameManager gameManager = GameManager.create(playersNames,randomCardGenerator);
        gameManager.initOpeningCards();

        requestHit(gameManager);
        gameManager.dealerHitUntilStand();
        printDealerReceiveCardCount(gameManager);

        outputView.printGamerCardsAndScore(gameManager.getDealer(), gameManager.getPlayers());
        outputView.printGameResult(gameManager.calculateDealerGameResult(), gameManager.calculatePlayerGameResult());
    }

    private List<String> requestPlayerNames() {
        return LoopTemplate.tryCatchLoop(() ->
                LoopTemplate.tryCatchLoop(inputView::readPlayers));
    }

    private void requestHit(GameManager gameManager) {
        outputView.printDealerAndPlayersCards(gameManager.getDealer(), gameManager.getPlayers());
        List<Player> isAbleToHitPlayer = gameManager.getAbleToHitPlayers();
        for (Player player : isAbleToHitPlayer) {
            receiveCardIsAbleToGetCard(gameManager,player);
        }
    }

    private void receiveCardIsAbleToGetCard(GameManager gameManager, Player player) {
        if (requestAnswerCommand(player) == AnswerCommand.NO) {
            outputView.printPlayerCards(player);
            return;
        }
        do {
            gameManager.dealCardToPlayer(player);
            outputView.printPlayerCards(player);
        } while (!player.isBust() && requestAnswerCommand(player) == AnswerCommand.YES);
    }

    private AnswerCommand requestAnswerCommand(Player player) {
        return LoopTemplate.tryCatchLoop(() -> inputView.readAnswer(player.getName()));
    }

    private void printDealerReceiveCardCount(GameManager gameManager) {
        int count = gameManager.getDealerHitCount();
        if (count > 0) {
            outputView.printDealerReceivedCardCount(count);
        }
    }
}
