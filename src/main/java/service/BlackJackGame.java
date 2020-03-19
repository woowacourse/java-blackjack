package service;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayersInfo;
import domain.user.User;
import util.YesOrNo;
import view.InputView;
import view.OutputView;

public class BlackJackGame {

    private static final int FIRST_CARD_COUNT = 2;

    public static Deck firstDealOut(Deck deck, Dealer dealer, PlayersInfo playersInfo) {
        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            dealer.draw(deck);
            playersInfo.draw(deck);
        }
        return deck;
    }

    public static void additionalDealOut(Deck deck, Dealer dealer, PlayersInfo playersInfo) {
        playersInfo.additionalDealOut(deck, BlackJackGame::isYes, OutputView::printPlayerDealOutResult);
        dealer.additionalDealOut(deck, OutputView::printDealerDealOut);
    }

    private static boolean isYes(String name) {
        String input = InputView.receiveYesOrNoInput(name);
        return YesOrNo.isYes(input);
    }

    public static Map<User, Integer> getUserToCardPoint(Dealer dealer, PlayersInfo playersInfo) {
        Map<User, Integer> userToCardPoint = new LinkedHashMap<>();

        userToCardPoint.put(dealer, dealer.calculatePoint());
        userToCardPoint.putAll(playersInfo.calculatePoint());

        return userToCardPoint;
    }

    public static Map<Player, Integer> getProfitOfPlayers(Dealer dealer, PlayersInfo playersInfo) {
        return playersInfo.calculateProfit(dealer);
    }

    public static int getProfitOfDealer(Dealer dealer, PlayersInfo playersInfo) {
        int totalProfitOfPlayers = playersInfo.calculateTotalProfit(dealer);

        return totalProfitOfPlayers * (-1);
    }
}
