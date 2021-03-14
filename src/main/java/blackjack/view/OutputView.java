package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.scoreboard.GameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String FIRST_DRAW_MESSAGE_PREFIX = "딜러와 ";
    private static final String FIRST_DRAW_MESSAGE_SUFFIX = "에게 2장의 카드를 나누었습니다.";
    private static final String PRINT_CARD_LIST_MSG_FORMAT = "%s 카드: %s%n";
    private static final String PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT = "%s 카드: %s - 결과 : %d%n";
    private static final String FINAL_PROFIT_MSG = "##최종 수익";
    private static final String DEALER_MORE_DRAW_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_PROFIT_FORMAT = "%s: %.0f";

    public static void printDrawMessage(List<String> userNames) {
        String names = String.join(DELIMITER, userNames);
        String firstDrawMessage = FIRST_DRAW_MESSAGE_PREFIX + names + FIRST_DRAW_MESSAGE_SUFFIX;
        System.out.println(firstDrawMessage);
    }

    public static void printDealerFirstCard(Card card) {
        String dealerFirstCard = card.getLetterOfValueAndSuit();

        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, Dealer.DEALER_NAME.toString(), dealerFirstCard);
    }

    public static void printCardList(Participant participant) {
        String cards = participant.getCards()
                .stream()
                .map(Card::getLetterOfValueAndSuit)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, participant.getName(), cards);
    }

    public static void printCardListAndScore(Participant participant) {
        String cards = participant.getCards()
                .stream()
                .map(Card::getLetterOfValueAndSuit)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT, participant.getName(), cards, participant.calculateScore());
    }

    public static void printDealerMoreDrawMessage() {
        System.out.println(DEALER_MORE_DRAW_CARD_MSG);
        println();
    }

    public static void printFinalDealerProfit(GameResult gameResult) {
        System.out.println(FINAL_PROFIT_MSG);
        System.out.println(createDealerResultMessage(gameResult));
    }

    private static String createDealerResultMessage(GameResult gameResult) {

        return String.format(FINAL_PROFIT_FORMAT, Dealer.DEALER_NAME.toString(), gameResult.calculateDealerProfit());
    }

    public static void printFinalUserProfit(GameResult gameResult) {
        gameResult.getResultEntrySet().stream()
                .map(result -> String.format(FINAL_PROFIT_FORMAT, result.getKey().getName(), result.getValue()))
                .forEach(System.out::println);
    }

    public static void println() {
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(message);

    }
}
