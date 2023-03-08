package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import java.util.List;

public class Player implements Decidable {

    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;
    private static final int PLAYER_MAX_RECEIVE_CARD = 21;

    private final String name;
    private final Participant participant;

    public Player(Participant participant, String name) {
        validateNameLength(name);
        this.name = name;
        this.participant = participant;
    }

    private void validateNameLength(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "[ERROR] 이름 길이는 최소 " + MIN_NAME_LENGTH + "글자에서 최대 "
                            + MAX_NAME_LENGTH + "글자 입니다.");
        }
    }

    public void receiveCard(Card card) {
        participant.receiveCard(card);
    }

    public Score calculateTotalScore() {
        return participant.calculateTotalScore();
    }

    public boolean isBust() {
        return participant.isBust();
    }

    @Override
    public List<Card> showInitCards() {
        return participant.getCards();
    }

    @Override
    public boolean canDraw() {
        return participant.calculateTotalScore() <= PLAYER_MAX_RECEIVE_CARD;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return participant.getCards();
    }
}
