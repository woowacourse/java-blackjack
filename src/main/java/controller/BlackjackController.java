package controller;

import domain.CardSetting;
import domain.GameManger;
import domain.TrumpCard;
import domain.user.Dealer;
import domain.user.User;
import java.util.List;
import view.CardConverter;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final OutputView outputView;
    private final InputView inputView;

    public BlackjackController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        // 참여자 입력
        List<String> playerNames = inputView.inputUsers();
        GameManger gameManger = new GameManger(playerNames);

        // 첫 카드 배부 (2장씩)
        CardSetting.initCache();
        gameManger.firstHandOutCard();

        // 첫 카드를 출력한다
        displayOpenCard(gameManger.getDealer().getName(), gameManger.getDealer());
        playerNames.forEach(playerName -> displayOpenCard(playerName, gameManger.findUserByUsername(playerName)));

        // 모든 플레이어에게 추가 카드 배분, 단 21이상인 경우 선택지 없음
        playerNames.forEach(playerName ->

                addCardAllPlayer(gameManger, playerName));

        // 딜러 추가 카드 배분, 단 16이상인 경우 선택지 없음
        User dealer = gameManger.getDealer();
        while (!dealer.isImpossibleDraw()) {
            dealer.drawCard();
            outputView.displayDealerAddCard();
        }

        // 결과 출력
        createGameResult(gameManger, playerNames);

        // 승패 계산

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
}