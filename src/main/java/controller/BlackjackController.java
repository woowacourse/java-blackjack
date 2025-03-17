package controller;

import domain.GameManager;
import domain.Profit;
import domain.TrumpCard;
import domain.TrumpCardManager;
import domain.user.Betting;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.CardConverter;
import view.InputView;
import view.OutputView;
import view.Parser;
import view.Validator;

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
        Map<String, Betting> playerBetting = inputBettingMoney(playerNames);

        GameManager gameManager = GameManager.initailizeGameManager(playerBetting, trumpCardManager);

        distributionFirstCard(gameManager, playerNames);
        additionalPlayerCard(playerNames, gameManager);
        additionalDealerCard(gameManager);

        createGameResult(gameManager, playerNames);
        calculateGameResult(gameManager);
    }

    private Map<String, Betting> inputBettingMoney(final List<String> playerNames) {
        Validator.validateNames(playerNames);
        return playerNames.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> new Betting(inputView.inputBettingMoney(name))
                ));
    }

    private void distributionFirstCard(final GameManager gameManager, final List<String> playerNames) {
        gameManager.firstHandOutCard();
        Dealer dealer = gameManager.getDealer();
        displayOpenCard(dealer.getName(), dealer);
        playerNames.forEach(playerName -> displayOpenCard(playerName, gameManager.findPlayerByUsername(playerName)));
    }

    private void displayOpenCard(final String userName, final User user) {
        List<String> cards = user.openCard().stream()
                .map(card -> CardConverter.createTrumpCard(card.getCardShape(), card.getCardNumber())).toList();
        outputView.displayOpenCards(userName, cards);
    }

    private void additionalPlayerCard(final List<String> playerNames, final GameManager gameManager) {
        playerNames.forEach(playerName -> addCardAllPlayer(gameManager, playerName));
    }

    private void addCardAllPlayer(final GameManager gameManager, final String playerName) {
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

    private void additionalDealerCard(final GameManager gameManager) {
        Dealer dealer = gameManager.getDealer();
        while (!dealer.isImpossibleDraw()) {
            gameManager.drawMoreCard(dealer);
            outputView.displayDealerAddCard();
        }
    }

    private void createGameResult(final GameManager gameManager, final List<String> playerNames) {
        displayDealer(gameManager);
        displayPlayers(gameManager, playerNames);
    }

    private void displayDealer(final GameManager gameManager) {
        Dealer dealer = gameManager.getDealer();
        List<TrumpCard> dealerCards = dealer.openAllCard();
        int score = dealer.getCardDeck().calculateScore();
        displayConvertCards(dealer.getName(), dealerCards, score);
    }

    private void calculateGameResult(final GameManager gameManager) {
        List<Profit> profitResult = gameManager.createProfitResult();
        long dealerProfit = gameManager.calculateDealerProfit(profitResult);
        outputView.displayProfitResult(dealerProfit, profitResult);
    }

    private void displayPlayers(final GameManager gameManager, final List<String> playerNames) {
        playerNames.stream()
                .map(gameManager::findPlayerByUsername)
                .toList()
                .forEach(player -> displayConvertCards(player.getName(), player.openCard(),
                        player.getCardDeck().calculateScore()));
    }

    private void displayConvertCards(final String name, final List<TrumpCard> dealerCards, final int score) {
        List<String> dealerPrintCards = dealerCards.stream()
                .map(dealerCard -> CardConverter.createTrumpCard(
                        dealerCard.getCardShape(),
                        dealerCard.getCardNumber()
                )).toList();
        outputView.displayOpenCardsResult(name, dealerPrintCards, score);
    }
}
