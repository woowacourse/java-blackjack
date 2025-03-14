package controller;

import domain.GameManager;
import domain.card.CardGenerator;
import domain.card.RandomCardGenerator;
import domain.gamer.Player;
import util.LoopTemplate;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        final GameManager gameManager = GameManager.create(playersNames, randomCardGenerator);
        final Map<String, Integer> playerBets = requestPlayerBets(playersNames);
        gameManager.initOpeningCards();

        requestHit(gameManager);
        gameManager.dealerHitUntilStand();
        printDealerReceiveCardCount(gameManager);

        outputView.printGamerCardsAndScore(gameManager.getDealer(), gameManager.getPlayers());
        outputView.printGamerBetResult(
                gameManager.getDealerBetResult(playerBets),
                gameManager.getPlayerBetResult(playerBets));
    }

    private Map<String, Integer> requestPlayerBets(List<String> playerNames) {
        return playerNames.stream()
                .collect(Collectors.toMap(name -> name, this::requestBetAmount));
    }

    private List<String> requestPlayerNames() {
        return LoopTemplate.tryCatchLoop(() ->
                LoopTemplate.tryCatchLoop(inputView::readPlayers));
    }

    private Integer requestBetAmount(String playerName) {
        return LoopTemplate.tryCatchLoop(() -> LoopTemplate.tryCatchLoop(() -> inputView.readBet(playerName)));
    }

    private void requestHit(GameManager gameManager) {
        outputView.printDealerAndPlayersCards(gameManager.getDealer(), gameManager.getPlayers());
        List<Player> isAbleToHitPlayer = gameManager.getAbleToHitPlayers();
        for (Player player : isAbleToHitPlayer) {
            receiveCardIsAbleToGetCard(gameManager, player);
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
