package blackjack.view;

import blackjack.domain.ResultStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
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
    private static final String TITLE_FINAL_EARNING = "## 최종 수익";
    private static final String EARNING_FORMAT = "%s: %d";

    public void printParticipantTotalCards(final Gamer gamer) {
        System.out.println(makeParticipantsMessage(gamer));
    }

    public void printDealerExtraCard() {
        System.out.println(TITLE_DEALER_EXTRA_CARD + LINE);
    }

    public void printNames(final Participants participants) {
        final String joinedNames = participants.getPlayers().stream()
                .map(Player::getNickname)
                .collect(Collectors.joining(COMMA));
        System.out.printf(LINE + NAME_FORMAT + LINE, joinedNames);
    }

    public void printCards(final Gamer gamer, final List<Card> cards) {
        final String message = String.format(CARD_FORMAT, gamer.getNickname(),
                getCardMessage(cards));
        System.out.println(message);
    }

    private String makeParticipantsMessage(final Gamer gamer) {
        return String.format(CARD_FORMAT, gamer.getNickname(),
                getCardMessage(gamer.showAllCard()));
    }

    private String getCardMessage(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getDenominationName() + getShapeName(card.getShape()))
                .collect(Collectors.joining(COMMA));
    }

    public void printCardsAndSum(final Gamer gamer, final List<Card> cards) {
        final String message = String.format(CARD_SUM_FORMAT, gamer.getNickname(),
                getCardMessage(cards), gamer.calculateMaxSum());
        System.out.println(message);
    }

    public void showException(final RuntimeException exception) {
        System.out.println(exception.getMessage());
    }

    private String getShapeName(final Shape shape) {
        return SHAPE_KOREAN.get(shape);
    }

    private String getResultStatusName(final ResultStatus resultStatus) {
        return RESULT_STATUS_KOREAN.get(resultStatus);
    }

    public void printEarningTitle() {
        System.out.println(LINE + TITLE_FINAL_EARNING);
    }

    public void printEarning(final Gamer gamer) {
        System.out.println(String.format(EARNING_FORMAT, gamer.getNickname(), gamer.getEarnedMoney()));
    }
}
