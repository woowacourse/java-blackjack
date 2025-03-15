package blackjack.view;

import blackjack.domain.result.DealerWinningResult;
import blackjack.domain.result.ResultStatus;
import blackjack.domain.card.Hand;
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
            ResultStatus.PUSH, "무"
    );
    private static final String LINE = System.lineSeparator();
    private static final String NAME_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String COMMA = ", ";
    private static final String CARD_FORMAT = "%s카드: %s";
    private static final String TITLE_DEALER_EXTRA_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_SCORE_FORMAT = "%s카드: %s - 결과: %d";

    private static final String TITLE_WINNING_RESULT = "## 최종 승패";
    private static final String WINNING_DEALER_RESULT_FORMAT = "딜러: %d승 %d무 %d패";
    private static final String WINNING_PLAYER_RESULT_FORMAT = "%s: %s";

    public void printSpreadCard(final List<String> playerNames, final Entry<String, Hand> dealerCards,
                                final Map<String, Hand> playersCards) {
        printNames(playerNames);
        printDealerCards(dealerCards);
        printPlayersCards(playersCards);
    }

    public void printParticipantTotalCards(final String nickname, final Hand hand) {
        System.out.println(makeCardMessage(nickname, hand));
    }

    public void printDealerExtraCard() {
        System.out.println(LINE + TITLE_DEALER_EXTRA_CARD);
    }

    private void printNames(final List<String> names) {
        final String joinedNames = String.join(COMMA, names);
        System.out.printf(LINE + NAME_FORMAT + LINE, joinedNames);
    }

    public void printDealerCards(final Entry<String, Hand> dealerCards) {
        final String dealerMessage = makeCardMessage(dealerCards.getKey(), dealerCards.getValue());
        System.out.println(dealerMessage);
    }

    public void printPlayersCards(final Map<String, Hand> playersCards) {
        playersCards.entrySet().stream()
                .map(entry -> makeCardMessage(entry.getKey(), entry.getValue()))
                .forEach(System.out::println);
        System.out.println();
    }

    private String makeCardMessage(final String nickname, final Hand hand) {
        return String.format(CARD_FORMAT, nickname, getCardMessage(hand));
    }

    private String getCardMessage(final Hand hand) {
        return hand.getHand().stream()
                .map(card -> card.getCardScoreName() + getShapeName(card.getShape()))
                .collect(Collectors.joining(COMMA));
    }

    public void showDealerWinningResult(final DealerWinningResult result) {
        System.out.println(LINE + TITLE_WINNING_RESULT);
        int winCount = result.countResultStatus(ResultStatus.WIN);
        int drawCount = result.countResultStatus(ResultStatus.PUSH);
        int loseCount = result.countResultStatus(ResultStatus.LOSE);
        System.out.printf(WINNING_DEALER_RESULT_FORMAT + LINE, winCount, drawCount, loseCount);
        showPlayerResultStatus(result.makePlayerWinningResult());
    }

    public void showln(final String line) {
        System.out.println(line);
    }

    public void makeParticipantsWithScoreMessage(final Entry<String, Hand> dealer,
                                                 final Map<String, Hand> participants) {
        System.out.println(LINE + makeParticipantsWithScoreMessage(dealer));
        participants.entrySet().stream()
                .map(this::makeParticipantsWithScoreMessage)
                .forEach(System.out::println);
    }

    private void showPlayerResultStatus(final Map<String, ResultStatus> result) {
        for (Entry<String, ResultStatus> entry : result.entrySet()) {
            System.out.printf(WINNING_PLAYER_RESULT_FORMAT + LINE, entry.getKey(),
                    getResultStatusName(entry.getValue()));
        }
    }

    private String makeParticipantsWithScoreMessage(final Entry<String, Hand> entry) {
        return String.format(CARD_SCORE_FORMAT, entry.getKey(),
                getCardMessage(entry.getValue()), entry.getValue().calculateResult());
    }

    private String getShapeName(final Shape shape) {
        return SHAPE_KOREAN.get(shape);
    }

    private String getResultStatusName(final ResultStatus resultStatus) {
        return RESULT_STATUS_KOREAN.get(resultStatus);
    }
}
