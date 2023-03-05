package ui;

import domain.Card;
import domain.Result;
import domain.user.User;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.Map;
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

    public static void printResults(Map<Player, Result> resultsOfPlayers) {
        long dealerLose = resultsOfPlayers.values().stream().filter(result -> result == Result.WIN).count();
        long dealerWin = resultsOfPlayers.values().stream().filter(result -> result == Result.LOSE).count();
        long dealerDraws = resultsOfPlayers.values().stream().filter(result -> result == Result.DRAW).count();
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d패", dealerWin, dealerLose);
        if(dealerDraws > 0) {
            System.out.printf(" %d무", dealerDraws);
        }
        System.out.println();
        resultsOfPlayers.forEach((player, result) -> System.out.println(player.getNameValue()+ ": " + result.getKoreanText()));
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
