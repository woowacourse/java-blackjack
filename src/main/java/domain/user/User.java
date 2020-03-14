package domain.user;

import domain.deck.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class User {

    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_SIZE = 2;

    protected final List<Card> cards;
    protected final Name name;

    protected User(String name) {
        cards = new ArrayList<>();
        this.name = Name.of(name);
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

    public abstract String getTotalWinningResult();

    public String getName() {
        return name.getName();
    }
}
