package view;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Gambler;
import dto.BlackjackResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printInitMessage(List<String> names) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerFirstCard(Card card) {
        System.out.println("딜러카드: " + formatCard(card));
    }

    public static void printPlayerCards(Gambler gambler) {
        String gamblerCards = formatCards(gambler.getCardInfo());
        System.out.println(gambler.getName() + " " + gamblerCards);
    }

    public static void printPlayerBust(String name) {
        System.out.println(name + " 버스트!");
    }

    public static void askHit(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalDealer(Dealer dealer, int score) {
        System.out.println("딜러카드: " + formatCards(dealer.getCards()) + " - 결과: " + score);
    }

    public static void printFinalPlayer(Gambler gambler) {
        System.out.println(gambler.getName() + "카드: " +
                formatCards(gambler.getCardInfo()) + " - 결과: " + gambler.score());
    }

    public static void printFinalResultHeader() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public static void printResult(BlackjackResult result) {
        System.out.println("딜러: " + result.winCount() + "승 " + result.lossCount() + "패 " + result.drawCount() + "무");
        for (String gamblerResult : result.logs()) {
            System.out.println(gamblerResult);
        }
    }

    private static String formatCard(Card card) {
        return card.getCardRank()
                .getName() + card.getCardSuit()
                .getSuit();
    }

    private static String formatCards(List<Card> cards) {
        return cards.stream()
                .map(OutputView::formatCard)
                .collect(Collectors.joining(", "));
    }

    public static void requestBettingMoney(String name) {
        System.out.printf("%s의 배팅 금액은?%n", name);
    }
}
