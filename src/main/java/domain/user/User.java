package domain.user;

import domain.deck.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class User {

    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_SIZE = 2;
    private static final int ACE_POINT = 10;

    protected final List<Card> cards;
    protected final Name name;

    protected User(String name) {
        cards = new ArrayList<>();
        this.name = Name.of(name);
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public List<String> getCards() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public int calculatePoint() {
        if (isBust() && hasAce()) {
            return sumPoint() - ACE_POINT;
        }
        return sumPoint();
    }

    private int sumPoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    public boolean isBust() {
        return sumPoint() > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && isBlackJackPoint();
    }

    public boolean isBlackJackPoint() {
        return sumPoint() == BLACK_JACK;
    }

    public abstract boolean isAvailableToDraw();

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public abstract String getTotalWinningResult();

    public String getName() {
        return name.getName();
    }
}
