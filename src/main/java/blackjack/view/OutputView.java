package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String COLON = ": ";
    private static final String COMMA = ", ";
    private static final String COMMA_WITH_BLANK = ", ";

    public static void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printDistribute(Dealer dealer, Players players) {
        System.out.println("\n" + dealer.getName() + "와 " + String.join(COMMA, players.showNames()) + "에게 2장의 카드를 나누었습니다.");
    }

    public static void printDealerCard(Dealer dealer) {
        System.out.println(dealer.getName() + COLON + dealer.showOneCard());
    }

    public static void printUsersCards(Players players) {
        players.players()
                .forEach(OutputView::printPlayerCards);
    }

    public static void printPlayerCards(Player player) {
        System.out.print(player.getName() + "카드" + COLON);
        printCards(player.showCards());
    }

    private static void printCards(Cards cards) {
        String cardsGroup = cards.cards()
                .stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
        System.out.println(cardsGroup);
    }

    public static void printWantHit(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerDrawable(Dealer dealer) {
        System.out.println("\n" + dealer.getName() + "는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printDealerNotDrawable(Dealer dealer) {
        System.out.println("\n" + dealer.getName() + "는 17이상이라 한장의 카드를 추가로 받지 못하였습니다.\n");
    }

    public static void printCardsWithTotalValue(List<User> users) {
        for (User user : users) {
            System.out.println(user.getName() + "카드: " +
                    user.showCards()
                            .cards()
                            .stream()
                            .map(Card::toString)
                            .collect(Collectors.joining(COMMA_WITH_BLANK)) + " - 결과: " +
                    user.showCards().calculateTotalValue());
        }
    }

    public static void printResults(Dealer dealer, ResultBoard resultBoard) {
        System.out.println("\n## 최종 승패");
        System.out.print(dealer.getName() + ": ");
        for (Result result : resultBoard.dealerResultBoard().keySet()) {
            System.out.print(resultBoard.dealerResultBoard().get(result) + result.getResult() + " ");
        }
        System.out.println();
        for (Player player : resultBoard.userResultBoard().keySet()) {
            System.out.println(player.getName() + ": " + resultBoard.userResultBoard().get(player).getResult());
        }
    }
}
