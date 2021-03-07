package blackjack.domain.participant;

public class Player extends Participant {
    private static final int BLACK_JACK_SCORE= 21;
    private static final String NAME_INPUT_ERROR_MESSAGE = "이름은 1자 이상이어야 합니다.";

    private final String name;

    public Player(String name) {
        this.name = validateName(name);
    }

    private String validateName(String name) {
        if (name.trim().length() < 1) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);
        }
        return name.trim();
    }

    public boolean isNotBust() {
        return cards.calculateScore() <= BLACK_JACK_SCORE;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String showCardsAtFirst() {
        return getCards();
    }
}
