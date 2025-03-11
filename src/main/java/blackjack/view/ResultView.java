package blackjack.view;

import blackjack.domain.ResultStatus;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ResultView {

    private static final Map<Shape, String> SHAPE_KOREAN = Map.of(
            Shape.SPADE, "스페이드",
            Shape.DIAMOND, "다이아몬드",
            Shape.HEART, "하트",
            Shape.CLOB, "클로버"
    );
    private static final Map<ResultStatus, String> RESULT_STATUS_KOREAN = Map.of(
            ResultStatus.WIN, "승",
            ResultStatus.LOSE, "패",
            ResultStatus.DRAW, "무"
    );
    private static final String LINE = System.lineSeparator();
    private static final String NAME_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String COMMA = ", ";
    private static final String CARD_FORMAT = "%s카드: %s";
    private static final String TITLE_DEALER_EXTRA_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_SUM_FORMAT = "%s카드: %s - 결과: %d";

    private static final String TITLE_WINNING_RESULT = "## 최종 승패";
    private static final String WINNING_DEALER_RESULT_FORMAT = "딜러: %d승 %d무 %d패";
    private static final String WINNING_PLAYER_RESULT_FORMAT = "%s: %s";

    public void printSpreadCard(final List<String> names, final Entry<String, Cards> dealerCards,
                                final Map<String, Cards> playersCards) {
        printNames(names);
        printDealerCards(dealerCards);
        printPlayersCards(playersCards);
    }

    public void printParticipantTotalCards(final String nickname, final Cards cards) {
        System.out.println(makeCardMessage(nickname, cards));
    }

    public void printDealerExtraCard() {
        System.out.println(LINE + TITLE_DEALER_EXTRA_CARD);
    }

    private void printNames(final List<String> names) {
        final String joinedNames = String.join(COMMA, names);
        System.out.printf(LINE + NAME_FORMAT + LINE, joinedNames);
    }

    public void printDealerCards(final Entry<String, Cards> dealerCards) {
        final String dealerMessage = makeCardMessage(dealerCards.getKey(), dealerCards.getValue());
        System.out.println(dealerMessage);
    }

    public void printPlayersCards(final Map<String, Cards> playersCards) {
        playersCards.entrySet().stream()
                .map(entry -> makeCardMessage(entry.getKey(), entry.getValue()))
                .forEach(System.out::println);
        System.out.println();
    }

    private String makeCardMessage(final String nickname, final Cards cards) {
        return String.format(CARD_FORMAT, nickname, getCardMessage(cards));
    }

    private String getCardMessage(final Cards cards) {
        return cards.getCards().stream()
                .map(card -> card.getDenominationName() + getShapeName(card.getShape()))
                .collect(Collectors.joining(COMMA));
    }

    public void showWinningResult(final Map<String, ResultStatus> result) {
        System.out.println(LINE + TITLE_WINNING_RESULT);
        int winCount = countResultStatus(result, ResultStatus.LOSE);
        int drawCount = countResultStatus(result, ResultStatus.DRAW);
        int loseCount = countResultStatus(result, ResultStatus.WIN);
        System.out.printf(WINNING_DEALER_RESULT_FORMAT + LINE, winCount, drawCount, loseCount);
        showResultStatus(result);
    }

    public void showln(final String line) {
        System.out.println(line);
    }

    public void makeParticipantsWithSumMessage(final Entry<String, Cards> dealer,
                                               final Map<String, Cards> participants) {
        System.out.println(LINE + makeParticipantsWithSumMessage(dealer));
        participants.entrySet().stream()
                .map(this::makeParticipantsWithSumMessage)
                .forEach(System.out::println);
    }

    private void showResultStatus(final Map<String, ResultStatus> result) {
        for (Entry<String, ResultStatus> entry : result.entrySet()) {
            System.out.printf(WINNING_PLAYER_RESULT_FORMAT + LINE, entry.getKey(),
                    getResultStatusName(entry.getValue()));
        }
    }

    private int countResultStatus(final Map<String, ResultStatus> result, final ResultStatus input) {
        return (int) result.values().stream()
                .filter(resultStatus -> resultStatus == input)
                .count();
    }

    private String makeParticipantsWithSumMessage(final Entry<String, Cards> entry) {
        return String.format(CARD_SUM_FORMAT, entry.getKey(),
                getCardMessage(entry.getValue()), entry.getValue().calculateResult());
    }

    private String getShapeName(final Shape shape) {
        return SHAPE_KOREAN.get(shape);
    }

    private String getResultStatusName(final ResultStatus resultStatus) {
        return RESULT_STATUS_KOREAN.get(resultStatus);
    }
}
