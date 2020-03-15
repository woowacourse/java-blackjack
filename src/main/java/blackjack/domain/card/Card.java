package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Card {
    private static final String NULL_ERROR_MSG = "생성자에 Null 이 들어올 수 없습니다.";
    private static final String INVALID_CARD_TYPE_OR_FIGURE_ERR_MSG = "존재하지 않는 카드 패턴입니다.";
    private static final List<Card> cards;

    private Type type;
    private Figure figure;

    static {
        cards = new ArrayList<>();
        for (Type type : Type.values()) {
            for (Figure figure : Figure.values()) {
                cards.add(new Card(type, figure));
            }
        }
    }

    private Card(Type type, Figure figure) {
        Objects.requireNonNull(type, NULL_ERROR_MSG);
        Objects.requireNonNull(figure, NULL_ERROR_MSG);

        this.type = type;
        this.figure = figure;
    }

    public static List<Card> getInstance() {
        Collections.shuffle(cards);
        return Collections.unmodifiableList(cards);
    }

    public static Card of(Type type, Figure figure) {
        Objects.requireNonNull(type, NULL_ERROR_MSG);
        Objects.requireNonNull(figure, NULL_ERROR_MSG);

        Card expectedCard = new Card(type, figure);

        return cards.stream()
                .filter(expectedCard::equals)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_TYPE_OR_FIGURE_ERR_MSG));
    }

    public boolean isAce() {
        return this.type == Type.ACE;
    }

    public int getCardValue() {
        return type.getValue();
    }

    public String cardInfo() {
        return type.getType() + figure.getFigure();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type &&
                figure == card.figure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, figure);
    }
}
