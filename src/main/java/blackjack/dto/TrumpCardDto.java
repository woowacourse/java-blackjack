package blackjack.dto;

import blackjack.model.trumpcard.Hand;
import blackjack.model.trumpcard.TrumpCard;
import blackjack.model.trumpcard.TrumpNumber;
import blackjack.model.trumpcard.TrumpSymbol;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class TrumpCardDto {
    private static final Map<TrumpNumber, String> TRUMP_NUMBER_NAME_MAP;
    private static final String NAME_ACE = "A";
    private static final String NAME_JACK = "J";
    private static final String NAME_QUEEN = "Q";
    private static final String NAME_KING = "K";

    private static final Map<TrumpSymbol, String> TRUMP_SYMBOL_NAME_MAP;
    private static final String NAME_HEART = "하트";
    private static final String NAME_DIAMOND = "다이아몬드";
    private static final String NAME_CLOVER = "클로버";
    private static final String NAME_SPADE = "스페이드";

    private final String number;
    private final String symbol;

    static {
        TRUMP_NUMBER_NAME_MAP = new EnumMap<>(TrumpNumber.class);
        TRUMP_NUMBER_NAME_MAP.put(TrumpNumber.ACE, NAME_ACE);
        TRUMP_NUMBER_NAME_MAP.put(TrumpNumber.JACK, NAME_JACK);
        TRUMP_NUMBER_NAME_MAP.put(TrumpNumber.QUEEN, NAME_QUEEN);
        TRUMP_NUMBER_NAME_MAP.put(TrumpNumber.KING, NAME_KING);

        TRUMP_SYMBOL_NAME_MAP = new EnumMap<>(TrumpSymbol.class);
        TRUMP_SYMBOL_NAME_MAP.put(TrumpSymbol.HEART, NAME_HEART);
        TRUMP_SYMBOL_NAME_MAP.put(TrumpSymbol.DIAMOND, NAME_DIAMOND);
        TRUMP_SYMBOL_NAME_MAP.put(TrumpSymbol.CLOVER, NAME_CLOVER);
        TRUMP_SYMBOL_NAME_MAP.put(TrumpSymbol.SPADE, NAME_SPADE);
    }

    private TrumpCardDto(String number, String symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public static List<TrumpCardDto> from(Hand hand) {
        return hand.getCards().stream()
                .map(TrumpCardDto::from)
                .collect(Collectors.toList());
    }

    private static TrumpCardDto from(TrumpCard trumpCard) {
        return from(trumpCard.getNumber(), trumpCard.getSymbol());
    }

    private static TrumpCardDto from(TrumpNumber trumpNumber, TrumpSymbol trumpSymbol) {
        return new TrumpCardDto(trumpNumberToString(trumpNumber), trumpSymbolToString(trumpSymbol));
    }

    private static String trumpNumberToString(TrumpNumber trumpNumber) {
        if (TRUMP_NUMBER_NAME_MAP.get(trumpNumber) == null) {
            return String.valueOf(trumpNumber.getValue());
        }
        return TRUMP_NUMBER_NAME_MAP.get(trumpNumber);
    }

    private static String trumpSymbolToString(TrumpSymbol trumpSymbol) {
        return TRUMP_SYMBOL_NAME_MAP.get(trumpSymbol);
    }

    public String getNumber() {
        return number;
    }

    public String getSymbol() {
        return symbol;
    }
}
