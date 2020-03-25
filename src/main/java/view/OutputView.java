package view;

import domain.BlackjackUserProfit;
import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.user.Dealer.DRAW_MAX_SCORE;

public class OutputView {

    public static final String NEW_LINE = System.lineSeparator();

    public static void printNameFormat() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printBettingMoneyFormat(String name) {
        System.out.println(NEW_LINE + name + "의 베팅 금액은?");
    }

    public static void printFirstDrawMessage(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));
        StringBuilder stringBuilder = new StringBuilder()
                .append(NEW_LINE)
                .append(dealer.getName())
                .append("와 ")
                .append(playerNames)
                .append("에게 2장의 카드를 나누었습니다.");

        System.out.println(stringBuilder);
    }

    public static void printFirstCardOnly(String name, List<Card> cards) {
        String firstCardName = cards.get(0).getName();
        System.out.println(name + " 카드: " + firstCardName);
    }

    public static void printCards(String name, List<Card> cards) {
        String cardsName = getCardNames(cards);
        System.out.println(name + " 카드: " + cardsName);
    }

    private static String getCardNames(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public static void printCardFormat(String name) {
        System.out.println(NEW_LINE + name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerDraw(String name) {
        System.out.println(NEW_LINE + name + "는 " + DRAW_MAX_SCORE + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardsWithScore(String name, List<Card> cards, int score) {
        String cardsName = getCardNames(cards);
        System.out.println(name + " 카드: " + cardsName + " - 결과 : " + score);
    }

    public static void printBlackjackUserProfit(BlackjackUserProfit blackjackUserProfit, String dealerName) {
        System.out.println(NEW_LINE + "## 최종 수익");
        int dealerProfit = (int) Math.ceil(blackjackUserProfit.calculateDealerProfitSum());
        System.out.println(dealerName + ": " + dealerProfit);

        Map<String, Double> playerProfit = blackjackUserProfit.get();
        for (String player : playerProfit.keySet()) {
            int profit = (int) Math.ceil(playerProfit.get(player));
            System.out.println(player + ": " + profit);
        }
    }
}
