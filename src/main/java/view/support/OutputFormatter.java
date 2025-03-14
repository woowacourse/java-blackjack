package view.support;

import static card.CardNumberType.*;
import static card.CardType.*;
import static result.GameStatus.*;
import static result.GameStatus.LOSE;
import static result.GameStatus.WIN;

import card.Card;
import card.CardNumberType;
import card.CardType;
import card.Hand;
import result.GameStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputFormatter {

    private static final String NAME_SEPARATOR = ", ";
    private static final String CARD_SEPARATOR = ", ";

    private static final Map<CardType, String> CARD_TYPE_FORMATTER = Map.of(
            SPACE, "스페이드",
            HEART, "하트",
            CLOVER, "클로버",
            DIAMOND, "다이아몬드"
    );

    private static final Map<CardNumberType, String> CARD_NUMBER_TYPE_FORMATTER = new HashMap<>();

    static {
        CARD_NUMBER_TYPE_FORMATTER.put(ACE, "A");
        CARD_NUMBER_TYPE_FORMATTER.put(TWO, "2");
        CARD_NUMBER_TYPE_FORMATTER.put(THREE, "3");
        CARD_NUMBER_TYPE_FORMATTER.put(FOUR, "4");
        CARD_NUMBER_TYPE_FORMATTER.put(FIVE, "5");
        CARD_NUMBER_TYPE_FORMATTER.put(SIX, "6");
        CARD_NUMBER_TYPE_FORMATTER.put(SEVEN, "7");
        CARD_NUMBER_TYPE_FORMATTER.put(EIGHT, "8");
        CARD_NUMBER_TYPE_FORMATTER.put(NINE, "9");
        CARD_NUMBER_TYPE_FORMATTER.put(TEN, "10");
        CARD_NUMBER_TYPE_FORMATTER.put(JACK, "J");
        CARD_NUMBER_TYPE_FORMATTER.put(QUEEN, "Q");
        CARD_NUMBER_TYPE_FORMATTER.put(KING, "K");
    }

    private static final Map<GameStatus, String> GAME_RESULT_FORMATTER = Map.of(
            WIN, "승",
            DRAW, "무",
            LOSE, "패"
    );

    public String formatPlayerNames(Set<String> names) {
        return String.join(NAME_SEPARATOR, names).trim();
    }

    public String formatCards(Hand hand) {
        List<String> formattedCards = hand.getCards().stream()
                .map(this::formatCard)
                .toList();
        return String.join(CARD_SEPARATOR, formattedCards);
    }

    public String formatCard(Card card) {
        return CARD_NUMBER_TYPE_FORMATTER.get(card.cardNumberType()) + CARD_TYPE_FORMATTER.get(card.cardType());
    }

    public String formatGameResult(GameStatus gameStatus) {
        return GAME_RESULT_FORMATTER.get(gameStatus);
    }
}
