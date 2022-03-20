package blackjack.dto;

import blackjack.model.trumpcard.Hand;
import blackjack.model.trumpcard.TrumpCard;
import blackjack.model.trumpcard.TrumpDenomination;
import blackjack.model.trumpcard.TrumpSuit;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class TrumpCardDto {
    private static final Map<TrumpDenomination, String> TRUMP_DENOMINATION_NAME_MAP;
    private static final String NAME_ACE = "A";
    private static final String NAME_JACK = "J";
    private static final String NAME_QUEEN = "Q";
    private static final String NAME_KING = "K";

    private static final Map<TrumpSuit, String> TRUMP_SUIT_NAME_MAP;
    private static final String NAME_HEART = "하트";
    private static final String NAME_DIAMOND = "다이아몬드";
    private static final String NAME_CLOVER = "클로버";
    private static final String NAME_SPADE = "스페이드";

    private final String denomination;
    private final String suit;

    static {
        TRUMP_DENOMINATION_NAME_MAP = new EnumMap<>(TrumpDenomination.class);
        TRUMP_DENOMINATION_NAME_MAP.put(TrumpDenomination.ACE, NAME_ACE);
        TRUMP_DENOMINATION_NAME_MAP.put(TrumpDenomination.JACK, NAME_JACK);
        TRUMP_DENOMINATION_NAME_MAP.put(TrumpDenomination.QUEEN, NAME_QUEEN);
        TRUMP_DENOMINATION_NAME_MAP.put(TrumpDenomination.KING, NAME_KING);

        TRUMP_SUIT_NAME_MAP = new EnumMap<>(TrumpSuit.class);
        TRUMP_SUIT_NAME_MAP.put(TrumpSuit.HEART, NAME_HEART);
        TRUMP_SUIT_NAME_MAP.put(TrumpSuit.DIAMOND, NAME_DIAMOND);
        TRUMP_SUIT_NAME_MAP.put(TrumpSuit.CLOVER, NAME_CLOVER);
        TRUMP_SUIT_NAME_MAP.put(TrumpSuit.SPADE, NAME_SPADE);
    }

    private TrumpCardDto(String denomination, String suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    static List<TrumpCardDto> from(Hand hand) {
        return hand.getCards().stream()
                .map(TrumpCardDto::from)
                .collect(Collectors.toList());
    }

    private static TrumpCardDto from(TrumpCard trumpCard) {
        return from(trumpCard.getDenomination(), trumpCard.getSuit());
    }

    private static TrumpCardDto from(TrumpDenomination trumpDenomination, TrumpSuit trumpSuit) {
        return new TrumpCardDto(trumpDenominationToString(trumpDenomination), trumpSuitToString(trumpSuit));
    }

    private static String trumpDenominationToString(TrumpDenomination trumpDenomination) {
        if (TRUMP_DENOMINATION_NAME_MAP.get(trumpDenomination) == null) {
            return String.valueOf(trumpDenomination.getValue());
        }
        return TRUMP_DENOMINATION_NAME_MAP.get(trumpDenomination);
    }

    private static String trumpSuitToString(TrumpSuit trumpSuit) {
        return TRUMP_SUIT_NAME_MAP.get(trumpSuit);
    }

    public String getDenomination() {
        return denomination;
    }

    public String getSuit() {
        return suit;
    }
}
