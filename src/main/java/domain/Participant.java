package domain;

import common.ErrorMessage;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Participant {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+$");

    private final String name;
    private final Deck deck;

    protected Participant(String name) {
        validateName(name);
        this.name = name;
        this.deck = new Deck();
    }

    private static void validateName(String name) {
        validateIsNotBlank(name);
        validateKoreanAndEnglish(name, NAME_PATTERN);
    }

    private static void validateIsNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_EMPTY_INPUT.getMessage());
        }
    }

    private static void validateKoreanAndEnglish(String name, Pattern NAME_PATTERN) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_KO_AND_ENG.getMessage());
        }
    }

    public abstract List<Card> getInitialVisibleCards();

    public abstract boolean isDrawable();

    public boolean isLessThanMaxScore() {
        return deck.isLessThanMaxScore();
    }

    public boolean isBust() {
        return deck.isBust();
    }

    public void addCard(Card newCard) {
        deck.addCard(newCard);
    }

    public int calculateDeckSum() {
        return deck.calculateCardScoreSum();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return deck.getCards();
    }
}
