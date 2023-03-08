package blackjack.view;

import blackjack.domain.result.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.dto.DealerFinalResult;
import blackjack.dto.FinalResult;
import blackjack.dto.PlayerFinalResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableList;

public class ViewRenderer {

    private static final Map<CardShape, String> CARD_SHAPE_STRING_MAPPER;
    private static final Map<CardNumber, String> CARD_NUMBER_STRING_MAPPER;
    private static final Map<GameResult, String> WINNING_STATUS_MAPPER;
    private static final String BLANK = "";

    static {
        CARD_SHAPE_STRING_MAPPER = Map.of(
                CardShape.SPADE, "스페이드",
                CardShape.CLOVER, "클로버",
                CardShape.DIAMOND, "다이아몬드",
                CardShape.HEART, "하트"
        );

        CARD_NUMBER_STRING_MAPPER = new HashMap<>();
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

        WINNING_STATUS_MAPPER = Map.of(
                GameResult.WIN, "승 ",
                GameResult.TIE, "무 ",
                GameResult.LOSE, "패 "
        );
    }

    public static List<String> renderCardsToString(final List<Card> cards) {
        return cards.stream()
                .map(card -> CARD_NUMBER_STRING_MAPPER.get(card.getNumber())
                        + CARD_SHAPE_STRING_MAPPER.get(card.getShape()))
                .collect(toUnmodifiableList());
    }

    public static String renderFinalResult(final FinalResult finalResult) {
        if (finalResult instanceof DealerFinalResult) {
            return renderDealerFinalResult((DealerFinalResult) finalResult);
        }
        return renderPlayerFinalResult((PlayerFinalResult) finalResult);
    }

    private static String renderDealerFinalResult(final DealerFinalResult finalResult) {
        StringBuilder stringBuilder = new StringBuilder();
        final Map<GameResult, Long> dealerWinningResult = finalResult.getResult();
        stringBuilder.append(renderWinningStatus(GameResult.WIN, dealerWinningResult));
        stringBuilder.append(renderWinningStatus(GameResult.TIE, dealerWinningResult));
        stringBuilder.append(renderWinningStatus(GameResult.LOSE, dealerWinningResult));

        return stringBuilder.toString();
    }

    private static String renderWinningStatus(final GameResult gameResult
            , final Map<GameResult, Long> dealerWinningResult) {
        if (dealerWinningResult.containsKey(gameResult)) {
            return dealerWinningResult.get(gameResult) + WINNING_STATUS_MAPPER.get(gameResult);
        }
        return BLANK;
    }

    private static String renderPlayerFinalResult(final PlayerFinalResult finalResult) {
        return WINNING_STATUS_MAPPER.get(finalResult.getResult());
    }
}
