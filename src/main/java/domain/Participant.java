package domain;

import java.util.List;

public abstract class Participant {
    private final String name;
    private final Hand hand;

    protected Participant(final String name) {

        Validator.validateName(name);
        this.name = name;
        this.hand = new Hand();
    }

    public List<Card> pickCard(final Deck deck, final int count) {
        for (int index = 0; index < count; index++) {
            hand.saveCard(deck.pick());
        }
        return hand.getCards();
    }

    private static class Validator {
        private static void validateName(final String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("이름으로 빈 문자열이 입력되었습니다.");
            }
        }
    }
}
