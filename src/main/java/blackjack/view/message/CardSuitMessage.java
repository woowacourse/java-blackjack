package blackjack.view.message;

import blackjack.constants.ErrorCode;
import blackjack.domain.card.CardSuit;
import blackjack.view.exception.MessageDoesNotExistException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CardSuitMessage {
    CLUB(CardSuit.CLUB, "클로버"),
    HEART(CardSuit.HEART, "하트"),
    SPADE(CardSuit.SPADE, "스페이드"),
    DIAMOND(CardSuit.DIAMOND, "다이아몬드");

    private static final Map<CardSuit, CardSuitMessage> SUIT_MESSAGE = Arrays.stream(values())
            .collect(Collectors.toMap(CardSuitMessage::getCardSuit, Function.identity()));

    private final CardSuit cardSuit;
    private final String message;

    CardSuitMessage(CardSuit cardSuit, String message) {
        this.cardSuit = cardSuit;
        this.message = message;
    }

    public static CardSuitMessage from(CardSuit cardSuit) {
        if (SUIT_MESSAGE.containsKey(cardSuit)) {
            return SUIT_MESSAGE.get(cardSuit);
        }
        throw new MessageDoesNotExistException(ErrorCode.NOT_EXIST_MESSAGE);
    }

    public String getMessage() {
        return message;
    }

    private CardSuit getCardSuit() {
        return cardSuit;
    }
}
