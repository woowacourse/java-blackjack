package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.dto.BlackjackResult;
import blackjack.dto.PlayerProfit;
import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String CARD_DISTRIBUTE = "딜러와 %s에게 2장을 나누었습니다." + NEW_LINE;
    private static final String DEALER_CARD_STATUS = "딜러: %s" + NEW_LINE;
    private static final String CARD_STATUS = "%s: %s" + NEW_LINE;
    private static final String DEALER_ADDED_CARD = NEW_LINE + "딜러는 %d이하라 한장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String RESULT_CARDS_STATUS = "%s카드: %s - 결과: %d" + NEW_LINE;
    private static final String DEALER_RESULT_CARDS_STATUS = NEW_LINE + "딜러 카드: %s - 결과: %d" + NEW_LINE;
    private static final String INVALID_CHOICE_EXCEPTION = "%s 또는 %s을 입력해주세요.";
    private static final String YES_CHOICE = "y";
    private static final String NO_CHOICE = "n";
    private static final String NAME_DELIMITER = ", ";
    private static final String PROFIT_TITLE = NEW_LINE + "## 최종 수익";
    private static final String PROFIT_FORMAT = "%s: %s" + NEW_LINE;

    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printCardDistribute(final List<String> playerNames) {
        System.out.printf(CARD_DISTRIBUTE, makeCommaString(playerNames));
    }

    public void printHandCards(final String card) {
        System.out.printf(DEALER_CARD_STATUS, card);
    }

    public void printHandCards(final String playerName, final List<String> card) {
        System.out.printf(CARD_STATUS, playerName + "카드", makeCommaString(card));
    }

    public void printAddDealerCard() {
        System.out.printf(DEALER_ADDED_CARD, Dealer.DEALER_BOUND);
    }

    public void printCardResultStatus(final List<String> card, final long score) {
        System.out.printf(DEALER_RESULT_CARDS_STATUS, makeCommaString(card), score);
    }

    public void printCardResultStatus(final String name, final List<String> card, final long score) {
        System.out.printf(RESULT_CARDS_STATUS, name, makeCommaString(card), score);
    }

    private String makeCommaString(final List<String> strings) {
        return String.join(NAME_DELIMITER, strings);
    }

    public boolean isMoreChoice(final String choice) {
        if (!List.of(YES_CHOICE, NO_CHOICE).contains(choice)) {
            throw new IllegalArgumentException(String.format(INVALID_CHOICE_EXCEPTION, YES_CHOICE, NO_CHOICE));
        }

        return choice.equals(YES_CHOICE);
    }

    public void printTotalProfit(final BlackjackResult blackjackResult) {
        printProfitTitle();
        printProfit(blackjackResult.dealerProfit());
        printProfit(blackjackResult.playerProfits());
    }

    private void printProfitTitle() {
        System.out.println(PROFIT_TITLE);
    }

    private void printProfit(final String dealerProfit) {
        System.out.printf(PROFIT_FORMAT, "딜러", dealerProfit);
    }

    private void printProfit(final List<PlayerProfit> playerProfits) {
        playerProfits.forEach(
                playerProfit -> System.out.printf(PROFIT_FORMAT, playerProfit.name(), playerProfit.profit()));
    }

    public void printNewLine() {
        System.out.println();
    }
}
