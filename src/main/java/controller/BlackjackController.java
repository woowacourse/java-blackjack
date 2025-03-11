package controller;

import domain.TrumpCardManager;
import domain.GameManager;
import domain.TrumpCard;
import domain.user.Dealer;
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

    public BlackjackController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        List<String> playerNames = Parser.parserStringToList(inputView.inputUsers());
        GameManager gameManager = new GameManager(playerNames);

        distributionFirstCard(gameManager, playerNames);

        additionalPlayerCard(playerNames, gameManager);
        additionalDealerCard(gameManager);

        createGameResult(gameManager, playerNames);

        calculateGameResult(gameManager);
    }

    private void displayOpenCard(String playerName, User player) {
        List<String> cards = player.openCard().stream()
            .map(card -> CardConverter.createTrumpCard(card.getCardShape(), card.getCardNumber())).toList();
        outputView.displayOpenCards(playerName, cards);
    }

    private void distributionFirstCard(GameManager gameManager, List<String> playerNames) {
        TrumpCardManager.initCache();
        gameManager.firstHandOutCard();

        displayOpenCard(gameManager.getDealer().getName(), gameManager.getDealer());
        playerNames.forEach(playerName -> displayOpenCard(playerName, gameManager.findUserByUsername(playerName)));
    }

    private void additionalDealerCard(GameManager gameManager) {
        User dealer = gameManager.getDealer();
        while (!dealer.isImpossibleDraw()) {
            dealer.drawCard();
            outputView.displayDealerAddCard();
        }
    }

    private void additionalPlayerCard(List<String> playerNames, GameManager gameManager) {
        playerNames.forEach(playerName ->
                addCardAllPlayer(gameManager, playerName));
    }

    private void calculateDealerResult(Map<User, Integer> gameResult) {
        int loseCount = getResultStateCount(gameResult, 1);
        int winCount = getResultStateCount(gameResult, 2);
        int mooCount = getResultStateCount(gameResult, 3);

        outputView.displayDealerGameResult(winCount, loseCount, mooCount);
    }

    private int getResultStateCount(Map<User, Integer> gameResult, int status) {
        return (int) gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == status)
                .count();
    }

    private void addCardAllPlayer(GameManager gameManager, String playerName) {
        User player = gameManager.findUserByUsername(playerName);
        while (!player.isImpossibleDraw()) {
            String yesOrNo = inputView.inputYesOrNo(playerName);
            if (yesOrNo.equalsIgnoreCase("N")) {
                return;
            }
            player.drawCard();
            displayOpenCard(playerName, player);
        }
    }

    private void createGameResult(GameManager gameManager, List<String> playerNames) {
        displayDealer(gameManager);
        displayPlayers(gameManager, playerNames);
    }

    private void displayDealer(GameManager gameManager) {
        Dealer dealer = (Dealer) gameManager.getDealer();
        List<TrumpCard> dealerCards = dealer.openAllCard();
        int score = dealer.getCardDeck().calculateScore();
        displayConvertCards(dealer.getName(), dealerCards, score);
    }

    private void displayPlayers(GameManager gameManager, List<String> playerNames) {
        playerNames.stream()
                .map(gameManager::findUserByUsername)
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

    private void calculateGameResult(GameManager gameManager) {
        Map<User, Integer> gameResult = gameManager.createGameResult();
        calculateDealerResult(gameResult);

        outputView.displayGameResult(gameResult);
    }
}
