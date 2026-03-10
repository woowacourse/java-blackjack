package domain;

import common.ErrorMessage;
import java.util.regex.Pattern;

public abstract class Participant {
    private final Hand hand;
    private final String name;

    protected Participant(String name, Card card1, Card card2) {
        this.hand = Hand.of(card1, card2);
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

//    public String getName() {
//        return name;
//    }
//
////    public abstract List<Card> getInitialVisibleCards();
//
//    public boolean isBust() {
//        return hand.isBust();
//    }
//
//    public int calculateHandSum() {
//        return hand.calculateCardScoreSum();
//    }
//
//    public Optional<Card> addCard(Deck totalDeck) {
//        Card newCard = totalDeck.drawCard();
//        this.hand.addCard(newCard);
//        return Optional.of(newCard);
//    }
}