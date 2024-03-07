package blackjack.view;

import blackjack.dto.GameResult;
import java.util.List;

public class OutputView {
    private static final String CARD_DISTRIBUTE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_STATUS = "%s: %s";
    private static final int DEALER_BOUND = 16;
    private static final String DEALER_ADDED_CARD = "%s는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_CARDS_STATUS = "%s카드: %s - 결과: %d" + System.lineSeparator();
    private static final String FINAL_RESULT_TITLE = System.lineSeparator() + "## 최종 승패";
    private static final String DEALER_FINAL_RESULT = "딜러: %d승 %d패" + System.lineSeparator();
    private static final String WINS_VIEW = "승";
    private static final String LOSES_VIEW = "패";


    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printCardDistribute(final String dealerName, final List<String> playerNames) {
        System.out.printf(
                System.lineSeparator() + CARD_DISTRIBUTE + System.lineSeparator(),
                dealerName, makeCommaString(playerNames)
        );
    }

    public void printHandCards(final String dealerName, final String card) {
        System.out.printf(CARD_STATUS + System.lineSeparator(), dealerName, card);
    }

    public void printHandCards(final String playerName, final List<String> card) {
        System.out.printf(CARD_STATUS + System.lineSeparator(), playerName + "카드", makeCommaString(card));
    }

    private String makeCommaString(final List<String> strings) {
        return String.join(", ", strings);
    }

    public void printAddDealerCard(final String name) {
        System.out.printf(
                System.lineSeparator() + DEALER_ADDED_CARD + System.lineSeparator() + System.lineSeparator(),
                name, DEALER_BOUND
        );
    }

    public void printCardResultStatus(final String name, final List<String> card, final long score) {
        System.out.printf(RESULT_CARDS_STATUS, name, makeCommaString(card), score);
    }

    public void printFinalResult(final List<String> names, final GameResult gameResult) {
        System.out.println(FINAL_RESULT_TITLE);

        printDealerFinalResult(gameResult);
        for (String name : names) {
            System.out.printf(CARD_STATUS + System.lineSeparator(), name,
                    toResultView(gameResult.findPlayerResultByName(name)));
        }
    }

    private void printDealerFinalResult(final GameResult gameResult) {
        System.out.printf(DEALER_FINAL_RESULT, gameResult.countWins(), gameResult.countLoses());
    }

    private String toResultView(boolean result) {
        if (result) {
            return WINS_VIEW;
        }
        return LOSES_VIEW;
    }
}
