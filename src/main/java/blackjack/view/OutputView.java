package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String INPUT_USER_NAMES_GUIDE_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String COMMA = ",";
    private static final String DISTRIBUTE_CONFIRM_MESSAGE_FORMAT = "%s와 %s에게 2장의 카드를 나누었습니다.";
    private static final String PLAYER_INFORMATION_FORMAT = "%s 카드: %s";
    private static final String COMMA_WITH_SPACE = ", ";
    private static final String ASK_ONE_MORE_CARD_MESSAGE_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_PLAY_CONFIRM_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void printInputUserNamesGuideMessage() {
        System.out.println(INPUT_USER_NAMES_GUIDE_MESSAGE);
    }

    public static void printDistributeConfirmMessage(Dealer dealer, List<User> users) {
        String userNames = users.stream()
                .map(Player::getName)
                .collect(Collectors.joining(COMMA));
        System.out.println(NEW_LINE + String.format(DISTRIBUTE_CONFIRM_MESSAGE_FORMAT, dealer.getName(), userNames));
    }

    public static void printInitialPlayerCards(Dealer dealer, List<User> users) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(users);

        for (Player player : players) {
            System.out.println(String.format(
                    PLAYER_INFORMATION_FORMAT, player.getName(), combineCards(player.getInitialCards())));
        }
    }

    public static void printUserCards(User user) {
        System.out.println(String.format(
                PLAYER_INFORMATION_FORMAT, user.getName(), combineCards(user.getCards())));
    }

    private static String combineCards(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA_WITH_SPACE));
    }

    public static void printAskOneMoreCardMessage(User user) {
        System.out.println(String.format(ASK_ONE_MORE_CARD_MESSAGE_FORMAT, user.getName()));
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printDealerPlayConfirmMessage() {
        System.out.println(DEALER_PLAY_CONFIRM_MESSAGE);
    }
}
