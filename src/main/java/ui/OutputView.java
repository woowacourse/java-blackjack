package ui;

import domain.card.Card;
import domain.user.AllWinningAmountDto;
import domain.user.User;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printCardsStatus(Dealer dealer, List<Player> players) {
        List<String> nameValues = players.stream()
                .map(User::getNameValue)
                .collect(Collectors.toList());
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.", String.join(", ", nameValues));
        System.out.println();
        printCardsStatusOfDealer(dealer);
        players.forEach(OutputView::printCardsStatusOfUser);
        System.out.println();
    }

    public static void printCardsStatusOfUser(User user) {
        List<String> cardTexts = user.getCards().stream()
                .map(Card::getText)
                .collect(Collectors.toList());
        System.out.printf("%s: %s", user.getNameValue(), String.join(", ", cardTexts));
        System.out.println();
    }

    private static void printCardsStatusOfDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().get(0);
        System.out.printf("%s: %s", dealer.getNameValue(), String.join(", ", firstCard.getText()));
        System.out.println();
    }

    public static void printCardsStatusAndScoreOfUser(User user) {
        List<String> cardTexts = user.getCards().stream()
                .map(Card::getText)
                .collect(Collectors.toList());
        System.out.printf("%s: %s", user.getNameValue(), String.join(", ", cardTexts));
        System.out.println(" - 결과: " + user.calculateScore());
    }

    public static void announceAddCardToDealerIfHit(boolean isHitByDealer) {
        if (isHitByDealer) {
            System.out.println();
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public static void printCardsStatusWithScore(Dealer dealer, List<Player> players) {
        System.out.println();
        printCardsStatusAndScoreOfUser(dealer);
        players.forEach(OutputView::printCardsStatusAndScoreOfUser);
    }

    public static void printWinningAmountsOfAllPlayers(AllWinningAmountDto allWinningAmountDto) {
        System.out.println();
        System.out.println("딜러: " + allWinningAmountDto.getWinningAmountOfDealer());
        allWinningAmountDto.getPlayerNameWinningAmounts()
                .forEach((playerName, winningAmount) -> System.out.println(playerName + ": " + winningAmount));
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println();
    }

    public static void printWinningAmountOfDealer(Dealer dealer, int dealerWinningAmount) {
        System.out.println();
        System.out.println(dealer.getNameValue() + ": " + dealerWinningAmount);
    }
}
