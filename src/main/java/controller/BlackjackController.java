package controller;

import domain.CardSetting;
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
        List<String> playerNames = Parser.parserStringToList(inputView.inputUsers());
        GameManger gameManger = new GameManger(playerNames);

        distributionFirstCard(gameManger, playerNames);

        additionalPlayerCard(playerNames, gameManger);
        addtionalDealerCard(gameManger);

        createGameResult(gameManger, playerNames);

        caculateGameResult(gameManger);
    }

    private void distributionFirstCard(GameManger gameManger, List<String> playerNames) {
        CardSetting.initCache();
        gameManger.firstHandOutCard();

        displayOpenCard(gameManger.getDealer().getName(), gameManger.getDealer());
        playerNames.forEach(playerName -> displayOpenCard(playerName, gameManger.findUserByUsername(playerName)));
    }

    private void addtionalDealerCard(GameManger gameManger) {
        User dealer = gameManger.getDealer();
        while (!dealer.isImpossibleDraw()) {
            dealer.drawCard();
            outputView.displayDealerAddCard();
        }
    }

    private void additionalPlayerCard(List<String> playerNames, GameManger gameManger) {
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
            player.drawCard();
            displayOpenCard(playerName, player);
        }
    }

    private void displayOpenCard(String playerName, User player) {
        List<String> cards = player.openCard().stream()
                .map(card -> CardConverter.createTrumpCard(card.getCardShape(), card.getCardNumber())).toList();
        outputView.displayOpenCards(playerName, cards);
    }

    private void createGameResult(GameManger gameManger, List<String> playerNames) {
        displayDealer(gameManger);
        displayPlayers(gameManger, playerNames);
    }

    private void displayDealer(GameManger gameManger) {
        Dealer dealer = (Dealer) gameManger.getDealer();
        List<TrumpCard> dealerCards = dealer.openAllCard();

        displayConvertCards(dealer.getName(), dealerCards);
    }

    private void displayPlayers(GameManger gameManger, List<String> playerNames) {
        playerNames.stream()
                .map(playerName -> gameManger.findUserByUsername(playerName))
                .toList()
                .forEach(player -> displayConvertCards(player.getName(), player.openCard()));
    }

    private void displayConvertCards(String name, List<TrumpCard> dealerCards) {
        List<String> dealerPrintCards = dealerCards.stream()
                .map(dealerCard -> CardConverter.createTrumpCard(
                        dealerCard.getCardShape(),
                        dealerCard.getCardNumber()
                )).toList();
        outputView.displayOpenCards(name, dealerPrintCards);
    }

    private void caculateGameResult(GameManger gameManger) {
        Map<User, Integer> gameResult = gameManger.createGameResult();
        calculateDealerResult(gameResult);

        outputView.displayGameResult(gameResult);
    }
}