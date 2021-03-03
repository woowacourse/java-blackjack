package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Users;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String COLON = ": ";
    private static final String COMMA = ", ";
    private static final String COMMA_WITH_BLANK = ", ";

    public static void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printDistribute(Users users) {
        System.out.println("딜러와 " + String.join(COMMA, users.showNames()) + "에게 2장의 카드를 나누었습니다.");
    }

    public static void printDealerCard(List<Card> card) {
        System.out.print("딜러" + COLON);
        printCards(card);
    }

    public static void printUsersCards(Users users) {
        users.users()
                .forEach(user -> {
                    System.out.print(user.getName() + "카드" + COLON);
                    printCards(user.show());
                });
    }

    private static void printCards(List<Card> cards) {
        String cardsGroup = cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
        System.out.println(cardsGroup);
    }
}
