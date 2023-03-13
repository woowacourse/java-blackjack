package blackjack.domain.user.player;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.user.Participant;
import blackjack.domain.user.Score;

import java.util.List;

public class Player {

    private static final int MAX_PLAYER_NAME_LENGTH = 10;
    private static final String DEALER_NAME = "딜러";

    private final Participant participant;

    public Player(final String name) {
        validateName(name);
        this.participant = new Participant(name);
    }

    private void validateName(final String name) {
        final String candidateName = name.strip();

        validateBlank(candidateName);
        validateLength(candidateName);
        validateReservedWord(candidateName);
    }

    private static void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("사용자 이름은 한글자 이상이여야 합니다.");
        }
    }

    private static void validateLength(final String name) {
        if (name.length() > MAX_PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException("사용자 이름은 10자 이하여야 합니다.");
        }
    }

    private static void validateReservedWord(final String name) {
        if (DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException("사용자 이름은 딜러일 수 없습니다.");
        }
    }

    public void drawCard(final CardPack cardPack) {
        participant.drawCard(cardPack);
    }

    public List<Card> showCards() {
        return List.copyOf(participant.showCards());
    }

    public boolean isBust() {
        return participant.isBust();
    }

    public Score getScore() {
        return participant.getScore();
    }

    public String getName() {
        return participant.getName();
    }
}
