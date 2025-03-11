package controller;

import domain.CardDeck;
import domain.GameManger;
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
        List<String> playerNames = Parser.parseStringToList(inputView.inputUsers());
        GameManger gameManger = new GameManger(playerNames, new Dealer(), new CardDeck());
        distributionFirstCard(gameManger, playerNames);

        additionalPlayerCard(gameManger, playerNames);
        additionalDealerCard(gameManger);

        createGameResult(gameManger, playerNames);
        calculateGameResult(gameManger);

        inputView.close();
    }

    private void distributionFirstCard(GameManger gameManger, List<String> playerNames) {
        gameManger.firstHandOutCard();

        displayOpenCard(gameManger.getDealer().getName(), gameManger.getDealer());
        playerNames.forEach(playerName -> displayOpenCard(playerName, gameManger.findUserByUsername(playerName)));
    }

    private void additionalDealerCard(GameManger gameManger) {
        User dealer = gameManger.getDealer();
        while (!dealer.isImpossibleDraw()) {
            dealer.drawCard(gameManger.handOutCard());
            outputView.displayDealerAddCard();
        }
    }

    private void additionalPlayerCard(GameManger gameManger, List<String> playerNames) {
        playerNames.forEach(playerName ->
                addCardAllPlayer(gameManger, playerName));
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

    private void addCardAllPlayer(GameManger gameManger, String playerName) {
        User player = gameManger.findUserByUsername(playerName);
        while (!player.isImpossibleDraw()) {
            String yesOrNo = inputView.inputYesOrNo(playerName);
            if (yesOrNo.equalsIgnoreCase("N")) {
                return;
            }
            player.drawCard(gameManger.handOutCard());
            displayOpenCard(playerName, player);
        }
    }

    private void displayOpenCard(String playerName, User player) {
        List<String> cards = player.openInitialCard().stream()
                .map(card -> CardConverter.createTrumpCard(card.cardShape(), card.cardNumber())).toList();
        outputView.displayOpenCards(playerName, cards);
    }

    private void createGameResult(GameManger gameManger, List<String> playerNames) {
        displayDealer(gameManger);
        displayPlayers(gameManger, playerNames);
    }

    private void displayDealer(GameManger gameManger) {
        Dealer dealer = (Dealer) gameManger.getDealer();
        List<TrumpCard> dealerCards = dealer.openAllCard();
        int score = dealer.getCardHand().calculateScore();
        displayConvertCards(dealer.getName(), dealerCards, score);
    }

    private void displayPlayers(GameManger gameManger, List<String> playerNames) {
        playerNames.stream()
                .map(gameManger::findUserByUsername)
                .toList()
                .forEach(player -> displayConvertCards(player.getName(), player.openInitialCard(),
                        player.getCardHand().calculateScore()));
    }

    private void displayConvertCards(String name, List<TrumpCard> dealerCards, int score) {
        List<String> dealerPrintCards = dealerCards.stream()
                .map(dealerCard -> CardConverter.createTrumpCard(
                        dealerCard.cardShape(),
                        dealerCard.cardNumber()
                )).toList();
        outputView.displayOpenCardsResult(name, dealerPrintCards, score);
    }

    private void calculateGameResult(GameManger gameManger) {
        Map<User, Integer> gameResult = gameManger.createGameResult();
        calculateDealerResult(gameResult);

        outputView.displayGameResult(gameResult);
    }
}