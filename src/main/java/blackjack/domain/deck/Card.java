package blackjack.domain.deck;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Card {
    private static final int MAX_CARD_VALUE = 13;
    private static final int MAX_CARD_COUNT = 52;
    private static final Map<Integer, Card> CACHE = IntStream.range(0, MAX_CARD_COUNT)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), Card::create));

    private final Kind kind;
    private final Value value;

    private Card(Kind kind, Value value) {
        this.kind = kind;
        this.value = value;
    }

    public static Card valueOf(int cardOrder) {
        if (CACHE.containsKey(cardOrder)) {
            return CACHE.get(cardOrder);
        }
        throw new IllegalStateException("입력값: (" + cardOrder + ") 는 존재하지 않는 카드번호 입니다.");
    }

    private static Card create(int cardOrder) {
        Kind kind = Kind.findKind(cardOrder / MAX_CARD_VALUE);
        int cardNumber = cardOrder % MAX_CARD_VALUE + 1;
        Value value = Value.findValue(cardNumber);

        return new Card(kind, value);
    }

    public String getCardName() {
        return value.getValueName() + kind.getKindName();
    }

    public int getScore() {
        return value.getScore();
    }

    public Kind getKind() {
        return kind;
    }

    public Value getValue() {
        return value;
    }
}
