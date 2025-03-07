package blackjack.view;

import blackjack.domain.ResultStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
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

    public void printSpreadCard(final Participants participants) {
        printNames(participants);
        printCards(participants);
    }

    public void printParticipantTotalCards(final Gamer gamer) {
        System.out.println(makeParticipantsMessage(gamer));
    }

    public void printDealerExtraCard() {
        System.out.println(LINE + TITLE_DEALER_EXTRA_CARD);
    }

    private void printNames(final Participants participants) {
        final String joinedNames = participants.getPlayers().stream()
                .map(Player::getNickname)
                .collect(Collectors.joining(COMMA));
        System.out.printf(LINE + NAME_FORMAT + LINE, joinedNames);
    }

    private void printCards(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final String dealerMessage = String.format(CARD_FORMAT, dealer.getNickname(),
                getCardMessage(dealer.showOneCard()));
        System.out.println(dealerMessage);
        final List<Player> players = participants.getPlayers();
        players.stream()
                .map(this::makeParticipantsMessage)
                .forEach(System.out::println);
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

    public void printCardsAndSum(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final String dealerMessage = String.format(CARD_SUM_FORMAT, dealer.getNickname(),
                getCardMessage(dealer.showAllCard()), dealer.calculateMaxSum());
        System.out.println(LINE + dealerMessage);
        final List<Player> players = participants.getPlayers();
        players.stream()
                .map(this::makeParticipantsWithSumMessage)
                .forEach(System.out::println);
    }

    public void showWinningResult(final Map<String, ResultStatus> result) {
        System.out.println(LINE + TITLE_WINNING_RESULT);
        int winCount = countResultStatus(result, ResultStatus.LOSE);
        int drawCount = countResultStatus(result, ResultStatus.DRAW);
        int loseCount = countResultStatus(result, ResultStatus.WIN);
        System.out.printf(WINNING_DEALER_RESULT_FORMAT + LINE, winCount, drawCount, loseCount);
        showResultStatus(result);
    }

    private void showResultStatus(final Map<String, ResultStatus> result) {
        for (Entry<String, ResultStatus> entry : result.entrySet()) {
            System.out.printf(WINNING_PLAYER_RESULT_FORMAT + LINE, entry.getKey(),
                    getResultStatusName(entry.getValue()));
        }
    }

    public void showException(final RuntimeException exception) {
        System.out.println(exception.getMessage());
    }

    private int countResultStatus(final Map<String, ResultStatus> result, final ResultStatus input) {
        return (int) result.values().stream()
                .filter(resultStatus -> resultStatus == input)
                .count();
    }

    private String makeParticipantsWithSumMessage(final Gamer gamer) {
        return String.format(CARD_SUM_FORMAT, gamer.getNickname(),
                getCardMessage(gamer.showAllCard()), gamer.calculateMaxSum());
    }

    private String getShapeName(final Shape shape) {
        return SHAPE_KOREAN.get(shape);
    }

    private String getResultStatusName(final ResultStatus resultStatus) {
        return RESULT_STATUS_KOREAN.get(resultStatus);
    }
}
