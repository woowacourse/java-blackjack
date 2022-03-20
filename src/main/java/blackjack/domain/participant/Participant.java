package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.BlackjackMatch;
import blackjack.domain.state.Ready;
import blackjack.domain.state.Status;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final String ERROR_MESSAGE_BLANK_NAME = "플레이어의 이름이 존재하지 않습니다.";

    private final String name;
    private Status status;

    protected Participant(String name, Status status) {
        validateName(name);
        this.name = name;
        this.status = status;
    }

    protected Participant(String name) {
        this(name, new Ready());
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BLANK_NAME);
        }
    }

    abstract boolean hasNextTurn();

    abstract BlackjackMatch isWin(Participant participant);

    public void receiveCard(Card card) {
        if (!isFinished()) {
            status = status.draw(card);
        }
    }

    private boolean isFinished() {
        return status.isFinished();
    }

    public void requestStay() {
        if (status.isRunning()) {
            status = getStatus().stay();
        }
    }

    public int getScore() {
        return status.getCards().calculateScore();
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public List<Card> getCards() {
        final Cards cards = status.getCards();
        return cards.getCards();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Participant))
            return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
