package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Cards;
import blackJack.domain.card.Denomination;
import blackJack.domain.result.MatchResult;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final String ERROR_MESSAGE_BLANK_NAME = "플레이어의 이름이 존재하지 않습니다.";
    public static final String DEALER_NAME = "딜러";

    public static final int STANDARD_SCORE_OF_CHANGE_ACE = 11;
    private static final int OTHER_SCORE_OF_ACE_DENOMINATION = 11;

    private final String name;
    private final Cards cards;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BLANK_NAME);
        }
    }

    abstract boolean canHit();

    public void hit(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = cards.calculateScore();

        if (cards.containsAce() && score <= STANDARD_SCORE_OF_CHANGE_ACE) {
            score += OTHER_SCORE_OF_ACE_DENOMINATION - Denomination.ACE.getScore();
        }
        return score;
    }

    public MatchResult getMatchResult(int otherParticipantScore) {
        if (this.getScore() > otherParticipantScore) {
            return MatchResult.WIN;
        }
        if (this.getScore() == otherParticipantScore) {
            return MatchResult.DRAW;
        }
        return MatchResult.LOSE;
    }

    public List<String> getCardsInfo() {
        return cards.getCardsInfo();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Participant)) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
