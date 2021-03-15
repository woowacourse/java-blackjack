package blackjack.view;

import blackjack.domain.result.ProfitResult;
import blackjack.domain.user.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String COLON = ": ";
    private static final String COMMA = ", ";

    public static void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printInputMoneyByPlayer(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
    }

    public static void printDistribute(Dealer dealer, List<String> playersNames) {
        System.out.printf("\n%s와 %s에게 2장의 카드를 나누었습니다.\n",
                dealer.getName(), String.join(COMMA, playersNames));
    }

    public static void printDealerCard(Dealer dealer) {
        System.out.println(dealer.getName() + COLON + dealer.showOneCard());
    }

    public static void printPlayersCards(Players players) {
        players.getPlayers()
                .forEach(OutputView::printPlayerCards);
    }

    public static void printPlayerCards(Player player) {
        System.out.print(player.getName() + "카드" + COLON);
        printCards(player.cards());
    }

    private static void printCards(Cards cards) {
        System.out.println(cards.getCards()
                .stream()
                .map(card -> card.getDenomination() + card.getSuit())
                .collect(Collectors.joining(COMMA)));
    }

    public static void printAskOneMoreCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerDrawable() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDealerNotDrawable() {
        System.out.println("\n딜러는 17이상이라 한장의 카드를 추가로 받지 못하였습니다.\n");
    }

    public static void printCardsWithTotalValue(List<User> users) {
        for (User user : users) {
            System.out.printf("%s카드: %s - 결과: %s \n",
                    user.getName(), cardsByUser(user), user.cards()
                            .totalScore()
                            .getValue());
        }
    }

    private static String cardsByUser(User user) {
        return user.cards()
                .getCards()
                .stream()
                .map(card -> card.getDenomination() + card.getSuit())
                .collect(Collectors.joining(COMMA));
    }

    public static void printProfitResult(ProfitResult profitResult) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + profitResult.getDealerProfit());
        Map<Player, Long> playersProfits = profitResult.getPlayersProfits();
        for (Player player : playersProfits.keySet()) {
            System.out.println(player.getName() + COLON + playersProfits.get(player));
        }
    }
}
