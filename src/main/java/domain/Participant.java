package domain;

import common.ErrorMessage;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public abstract class Participant {
    private final Deck deck;
    private final String name;

    protected Participant(Deck participantDeck, String name) {
        validateName(name);
        this.deck = participantDeck;
        this.name = name;
    }

    private static void validateName(String name) {
        Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+$");
        validateIsNotBlank(name);
        validateKoreanAndEnglish(name, NAME_PATTERN);
    }

    private static void validateKoreanAndEnglish(String name, Pattern NAME_PATTERN) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_KO_AND_ENG.getMessage());
        }
    }

    private static void validateIsNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_EMPTY_INPUT.getMessage());
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public String getName() {
        return name;
    }

    public abstract List<Card> getInitialVisibleCards();

    public boolean isBust() {
        return deck.isBust();
    }

    public int calculateDeckSum() {
        return deck.calculateCardScoreSum();
    }

    public Optional<Card> addCard(Deck totalDeck) {
        Card newCard = totalDeck.drawCard();
        this.deck.addCard(newCard);
        return Optional.of(newCard);
    }
}
