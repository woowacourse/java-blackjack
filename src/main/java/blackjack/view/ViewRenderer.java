package blackjack.view;

import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.WinningStatus;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ViewRenderer {

    private static final Map<CardShape, String> CARD_SHAPE_STRING_MAPPER = Map.of(
            CardShape.SPADE, "스페이드",
            CardShape.CLOVER, "클로버",
            CardShape.DIAMOND, "다이아몬드",
            CardShape.HEART, "하트"
    );
    private static final Map<CardNumber, String> CARD_NUMBER_STRING_MAPPER = new EnumMap<>(CardNumber.class);
    private static final Map<WinningStatus, String> WINNING_STATUS_MAPPER = new LinkedHashMap<>();
    private static final String CARD_RESULT_FORMAT = "%s - 결과: %d";

    static {
        WINNING_STATUS_MAPPER.put(WinningStatus.WIN, "승 ");
        WINNING_STATUS_MAPPER.put(WinningStatus.TIE, "무 ");
        WINNING_STATUS_MAPPER.put(WinningStatus.LOSE, "패");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.ACE, "A");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.TWO, "2");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.THREE, "3");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.FOUR, "4");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.FIVE, "5");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.SIX, "6");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.SEVEN, "7");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.EIGHT, "8");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.NINE, "9");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.TEN, "10");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.JACK, "J");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.QUEEN, "Q");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.KING, "K");
    }

    private ViewRenderer() {
    }

    public static Map<String, List<String>> renderStatus(final Map<String, CardGroup> status) {
        final Map<String, List<String>> renderedStatus = new LinkedHashMap<>();
        status.forEach((name, cardGroup) -> renderedStatus.put(name, renderCardGroupToString(cardGroup)));
        return Collections.unmodifiableMap(renderedStatus);
    }

    public static List<String> renderCardGroupToString(final CardGroup cardGroup) {
        return cardGroup.getCards().stream()
                .map(card -> CARD_NUMBER_STRING_MAPPER.get(card.getNumber())
                        + CARD_SHAPE_STRING_MAPPER.get(card.getShape()))
                .collect(toUnmodifiableList());
    }

    public static Map<String, Long> renderDealerWinningResult(
            final Map<WinningStatus, Long> dealerWinningResult) {
        final Map<String, Long> renderedDealerWinningResult = new LinkedHashMap<>();
        WINNING_STATUS_MAPPER.keySet()
                .stream()
                .filter(dealerWinningResult::containsKey)
                .forEach(winningStatus -> renderedDealerWinningResult.put(WINNING_STATUS_MAPPER.get(winningStatus),
                        dealerWinningResult.get(winningStatus)));
        return Collections.unmodifiableMap(renderedDealerWinningResult);
    }

    public static Map<String, String> renderPlayersWinningResults(
            final Map<String, WinningStatus> playersWinningResults) {
        final Map<String, String> renderedWinningResult = new LinkedHashMap<>();
        playersWinningResults.forEach((name, winningStatus) ->
                renderedWinningResult.put(name, WINNING_STATUS_MAPPER.get(winningStatus)));
        return Collections.unmodifiableMap(renderedWinningResult);
    }

    public static Map<String, String> renderUserNameAndCardResults(
            final Map<String, CardResult> userNameAndCardResults) {
        final Map<String, String> renderedUserNameAndCardResults = new LinkedHashMap<>();

        userNameAndCardResults
                .forEach((key, value) -> renderedUserNameAndCardResults.put(key, renderCardResults(value)));

        return Collections.unmodifiableMap(renderedUserNameAndCardResults);
    }

    private static String renderCardResults(final CardResult cardResult) {
        final List<String> cardNames = renderCardGroupToString(cardResult.getCards());
        return String.format(CARD_RESULT_FORMAT, String.join(", ", cardNames)
                , cardResult.getScore().getValue());
    }
}
