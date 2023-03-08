package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public final class Player {

    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;

    private final Participant participant;

    public Player(final String name) {
        validateNameLength(name);
        this.participant = new Participant(name);
    }

    private void validateNameLength(final String name) {
        if (MAX_NAME_LENGTH < name.length() || name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름 길이는 최소 " + MIN_NAME_LENGTH + "글자에서 최대 " + MAX_NAME_LENGTH + "글자 입니다. 입력값: " + name);
        }
    }

    public void receiveCard(final Card card) {
        participant.receiveCard(card);
    }

    public int calculateTotalScore() {
        return participant.calculateTotalScore();
    }

    public boolean isBust() {
        return participant.isBust();
    }

    public List<Card> getCards() {
        return participant.getCards();
    }

    public String getName() {
        return participant.getName();
    }
}
