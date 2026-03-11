package domain;

import common.ErrorMessage;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Participant {
    protected final Hand hand;
    protected final String name;

    protected Participant(String name, Hand hand) {
        this.hand = hand;
        validateName(name);
        this.name = name;
    }

    private static void validateName(String name) {
        validateIsNotBlank(name);
        validateKoreanAndEnglish(name);
    }

    private static void validateKoreanAndEnglish(String name) {
        Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+$");
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_KO_AND_ENG.getMessage());
        }
    }

    private static void validateIsNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_EMPTY_INPUT.getMessage());
        }
    }

    public abstract List<Card> showInitialCard();

    public String getName() {
        return name;
    }

    public List<Card> showOwnCards() {
        return hand.showCards();
    }

    public int getOwnCardsSum() {
        return this.hand.calculateCardScoreSum();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}