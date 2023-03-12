package dto;

import domain.Card;

import java.util.HashMap;
import java.util.Map;

public class CardStatusDto {

    private static final Map<String, Map<String, CardStatusDto>> CACHE = new HashMap<>();

    private final String letterExpression;

    private final String shapeName;

    public CardStatusDto(String letterExpression, String shapeName) {
        this.letterExpression = letterExpression;
        this.shapeName = shapeName;
    }

    public static CardStatusDto from(Card card) {
        String letterExpression = card.getLetterExpression();
        String shapeName = card.getShapeName();

        return CACHE.computeIfAbsent(letterExpression,
                        ignored -> createMapAndPutCardIfAbsent(letterExpression, shapeName))
                .computeIfAbsent(letterExpression, ignored -> new CardStatusDto(letterExpression, shapeName));
    }

    public static Map<String, CardStatusDto> createMapAndPutCardIfAbsent(
            String letterExpression,
            String shapeName
    ) {
        Map<String, CardStatusDto> cardMapper = new HashMap<>();
        cardMapper.put(letterExpression, new CardStatusDto(letterExpression, shapeName));
        return cardMapper;
    }

    public String getLetterExpression() {
        return letterExpression;
    }

    public String getShapeName() {
        return shapeName;
    }

}
