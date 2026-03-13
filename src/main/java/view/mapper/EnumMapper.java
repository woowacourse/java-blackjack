package view.mapper;

import java.util.Map;
import model.card.CardShape;
import model.card.CardValue;
import model.judgement.ResultStatus;

public class EnumMapper {

    public static final Map<ResultStatus, String> RESULT_STATUS_MAPPER = Map.of(
            ResultStatus.WIN, "승",
            ResultStatus.LOSE, "패",
            ResultStatus.DRAW, "무",
            ResultStatus.BLACKJACK, "블랙잭"
    );

    public static final Map<CardValue, String> CARD_VALUE_MAPPER = Map.ofEntries(
            Map.entry(CardValue.ACE, "A"),
            Map.entry(CardValue.TWO, "2"),
            Map.entry(CardValue.THREE, "3"),
            Map.entry(CardValue.FOUR, "4"),
            Map.entry(CardValue.FIVE, "5"),
            Map.entry(CardValue.SIX, "6"),
            Map.entry(CardValue.SEVEN, "7"),
            Map.entry(CardValue.EIGHT, "8"),
            Map.entry(CardValue.NINE, "9"),
            Map.entry(CardValue.TEN, "10"),
            Map.entry(CardValue.JACK, "J"),
            Map.entry(CardValue.QUEEN, "Q"),
            Map.entry(CardValue.KING, "K")
    );

    public static final Map<CardShape, String> CARD_SHAPE_MAPPER = Map.of(
            CardShape.HEART, "하트",
            CardShape.SPADE, "스페이드",
            CardShape.CLOVER, "클로버",
            CardShape.DIAMOND, "다이아몬드"
    );

    public EnumMapper(){}
}
