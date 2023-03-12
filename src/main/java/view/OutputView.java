package view;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = "\n";
    private static final String PRINT_CARDS_MESSAGE = NEW_LINE + "딜러와 %s에게 2장을 나누었습니다.";
    private static final String COLON = ": ";
    private static final String DEALER_RECEPTION_NOTICE = NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_STATE_RESULT_SIGN = " - 결과: ";
    private static final String CARD = " 카드";
    private static final String PROFIT_MESSAGE = NEW_LINE + "## 최종 수익";

    public static void printInitializedPlayers(final List<NameCardScoreDto> players) {
        final StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        players.stream()
            .map(NameCardScoreDto::getName)
            .forEach(stringJoiner::add);
        System.out.printf(PRINT_CARDS_MESSAGE, stringJoiner);
        System.out.println();
    }

    public static void printFirstCard(final NameCardScoreDto nameCardScore) {
        System.out.println(nameCardScore.getName() + COLON + nameCardScore.getCards().get(0));
    }

    public static void printCards(final List<NameCardScoreDto> nameCardScore) {
        nameCardScore.forEach(OutputView::printCards);
        System.out.println();
    }

    public static void printCards(final NameCardScoreDto nameCardScore) {
        System.out.println(nameCardScore.getName() + CARD + COLON + concatCards(nameCardScore.getCards()));
    }

    private static String concatCards(final List<String> cards) {
        final StringJoiner sj = new StringJoiner(DELIMITER);
        cards.forEach(sj::add);
        return sj.toString();
    }

    public static void printDealerReceptionNotice() {
        System.out.println(DEALER_RECEPTION_NOTICE);
    }

    public static void printTotalCardState(final List<NameCardScoreDto> nameCardScores) {
        System.out.println();
        for (NameCardScoreDto nameCardScore : nameCardScores) {
            System.out.println(nameCardScore.getName() + CARD + COLON + concatCards(nameCardScore.getCards())
            + CARD_STATE_RESULT_SIGN + nameCardScore.getScore());
        }
    }

    public static void printProfitMessage() {
        System.out.println(PROFIT_MESSAGE);
    }

    public static void printProfits(final List<NameProfitDto> nameProfits) {
        for (NameProfitDto nameProfit : nameProfits) {
            printProfits(nameProfit);
        }
    }

    public static void printProfits(final NameProfitDto nameProfit) {
        System.out.println(nameProfit.getName() + COLON + nameProfit.getProfit());
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
