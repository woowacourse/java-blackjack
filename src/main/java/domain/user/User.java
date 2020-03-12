package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.deck.Card;

public abstract class User {

    private static final String EMPTY = "";
    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_SIZE = 2;

    protected List<Card> cards;
    protected final String name;

    protected User(String name) {
        validate(name);
        cards = new ArrayList<>();
        this.name = name;
    }

    private void validate(String name) {
        if (EMPTY.equals(name)) {
            throw new IllegalArgumentException("빈 이름이 있습니다.");
        }
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public String getDrawResult() {
        return name + "카드: "
                + cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public int calculatePointAccordingToHasAce() {
        if (isBust() && hasAce()) {
            return calculatePoint() - 10;
        }
        return calculatePoint();
    }

    private int calculatePoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    public boolean isBust() {
        return calculatePoint() > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && isBlackJackPoint();
    }

    public boolean isBlackJackPoint() {
        return calculatePoint() == BLACK_JACK;
    }

    public abstract boolean isAvailableToDraw();

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public String getTotalDrawResult() {
        return getDrawResult() + " - 결과: " + calculatePointAccordingToHasAce();
    }

    public abstract String getWinningResult();

    public String getName() {
        return name;
    }
}
