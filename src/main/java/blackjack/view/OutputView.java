package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String COLON = ": ";
    private static final String COMMA = ", ";
    private static final String COMMA_WITH_BLANK = ", ";

    public static void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printDistribute(Dealer dealer, Users users) {
        System.out.println(dealer.getName() + "와 " + String.join(COMMA, users.showNames()) + "에게 2장의 카드를 나누었습니다.");
    }

    public static void printDealerCard(Dealer dealer) {
        System.out.print(dealer.getName() + COLON + dealer.showOneCard());
    }

    public static void printUsersCards(Users users) {
        users.users()
                .forEach(OutputView::printUserCards);
    }

    public static void printUserCards(User user) {
        System.out.print(user.getName() + "카드" + COLON);
        printCards(user.showCards());
    }

    private static void printCards(Cards cards) {
        String cardsGroup = cards.cards()
                .stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
        System.out.println(cardsGroup);
    }

    public static void printMoreDraw(User user) {
        System.out.println(user.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealerDrawable(Dealer dealer) {
        System.out.println("\n" + dealer.getName() + "는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printDealerNotDrawable(Dealer dealer) {
        System.out.println("\n" + dealer.getName() + "는 17이상이라 한장의 카드를 추가로 받지 못하였습니다.\n");
    }

    public static void printResults(List<Participant> participants) {
        for (Participant participant : participants) {
            System.out.println(participant.getName() + "카드: " +
                    participant.showCards()
                            .cards()
                            .stream()
                            .map(Card::toString)
                            .collect(Collectors.joining(COMMA_WITH_BLANK)) + " - 결과: " +
                    participant.showCards().calculateTotalValue());
        }
    }

    public static void printResultBoard(Dealer dealer, ResultBoard resultBoard) {
        System.out.println("\n## 최종 승패");
        System.out.print(dealer.getName() + ": ");
        for (Result result : resultBoard.dealerResultBoard().keySet()) {
            System.out.print(resultBoard.dealerResultBoard().get(result) + result.getResult() + " ");
        }
        System.out.println();
        for (User user : resultBoard.userResultBoard().keySet()) {
            System.out.println(user.getName() + ": " + resultBoard.userResultBoard().get(user).getResult());
        }
    }
}
