package controller;

import domain.GameManager;
import domain.GameResult;
import domain.TrumpCard;
import domain.TrumpCardManager;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import view.CardConverter;
import view.InputView;
import view.OutputView;
import view.Parser;

public class BlackjackController {
    private final OutputView outputView;
    private final InputView inputView;
    private final TrumpCardManager trumpCardManager;

    public BlackjackController(OutputView outputView, InputView inputView, TrumpCardManager trumpCardManager) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.trumpCardManager = trumpCardManager;
    }

    public void run() {
        List<String> playerNames = Parser.parserStringToList(inputView.inputUsers());
        GameManager gameManager = GameManager.initailizeGameManager(playerNames, trumpCardManager);

        distributionFirstCard(gameManager, playerNames);

        additionalPlayerCard(playerNames, gameManager);
        additionalDealerCard(gameManager);

        createGameResult(gameManager, playerNames);

        calculateGameResult(gameManager);
    }

    private void distributionFirstCard(GameManager gameManager, List<String> playerNames) {
        gameManager.firstHandOutCard();

        displayOpenCard(gameManager.findDealerName(), gameManager.getDealer());
        playerNames.forEach(playerName -> displayOpenCard(playerName, gameManager.findPlayerByUsername(playerName)));
    }


    private void displayOpenCard(String userName, User user) {
        List<String> cards = user.openCard().stream()
                .map(card -> CardConverter.createTrumpCard(card.getCardShape(), card.getCardNumber())).toList();
        outputView.displayOpenCards(userName, cards);
    }

    private void additionalPlayerCard(List<String> playerNames, GameManager gameManager) {
        playerNames.forEach(playerName -> addCardAllPlayer(gameManager, playerName));
    }

    private void addCardAllPlayer(GameManager gameManager, String playerName) {
        Player player = gameManager.findPlayerByUsername(playerName);
        while (!player.isImpossibleDraw()) {
            String yesOrNo = inputView.inputYesOrNo(playerName);
            if (yesOrNo.equalsIgnoreCase("N")) {
                return;
            }
            gameManager.drawMoreCard(player);
            displayOpenCard(playerName, player);
        }
    }

    private void additionalDealerCard(GameManager gameManager) {
        Dealer dealer = gameManager.getDealer();
        while (!dealer.isImpossibleDraw()) {
            gameManager.drawMoreCard(dealer);
            outputView.displayDealerAddCard();
        }
    }

    private void createGameResult(GameManager gameManager, List<String> playerNames) {
        displayDealer(gameManager);
        displayPlayers(gameManager, playerNames);
    }

    private void displayDealer(GameManager gameManager) {
        Dealer dealer = gameManager.getDealer();
        List<TrumpCard> dealerCards = dealer.openAllCard();
        int score = dealer.getCardDeck().calculateScore();
        displayConvertCards(dealer.getName(), dealerCards, score);
    }

    private void calculateGameResult(GameManager gameManager) {
        Map<User, GameResult> gameResult = gameManager.createGameResult();
        calculateDealerResult(gameResult);

        outputView.displayGameResult(gameResult);
    }

    private void calculateDealerResult(Map<User, GameResult> gameResult) {
        int loseCount = getResultStateCount(gameResult, GameResult.WIN);
        int winCount = getResultStateCount(gameResult, GameResult.LOSE);
        int mooCount = getResultStateCount(gameResult, GameResult.DRAW);

        outputView.displayDealerGameResult(winCount, loseCount, mooCount);
    }

    private int getResultStateCount(Map<User, GameResult> gameResult, GameResult status) {
        return (int) gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == status)
                .count();
    }
    
    private void displayPlayers(GameManager gameManager, List<String> playerNames) {
        playerNames.stream()
                .map(gameManager::findPlayerByUsername)
                .toList()
                .forEach(player -> displayConvertCards(player.getName(), player.openCard(),
                        player.getCardDeck().calculateScore()));
    }

    private void displayConvertCards(String name, List<TrumpCard> dealerCards, int score) {
        List<String> dealerPrintCards = dealerCards.stream()
                .map(dealerCard -> CardConverter.createTrumpCard(
                        dealerCard.getCardShape(),
                        dealerCard.getCardNumber()
                )).toList();
        outputView.displayOpenCardsResult(name, dealerPrintCards, score);
    }
}
