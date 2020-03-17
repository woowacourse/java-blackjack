package domain.participant;

import java.util.Objects;

import domain.ParticipantCards;
import domain.card.Card;
import domain.exception.OverScoreException;

public class Participant implements ParticipantInterface {
    private static final int INITIAL_CARD_NUMBER = 2;
    private static final int MAX_SCORE = 21;
    private static final int CRITERION = 21;

    private String name;
    private ParticipantCards cards;

    Participant(String name) {
        this.name = name;
        this.cards = new ParticipantCards();
    }

    public void receive(Card card) {
        if (calculateScore() >= CRITERION) {
            throw new OverScoreException("카드의 총 점수가 21을 넘으므로 카드를 뽑으실 수 없습니다.");
        }
        cards.add(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    boolean isBust() {
        return (this.calculateScore() > MAX_SCORE);
    }

    public String getName() {
        return this.name;
    }

    ParticipantCards getCards() {
        return this.cards;
    }

    @Override
    public String toString() {
        return name + "카드: " + cards.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Player player = (Player)o;
        return Objects.equals(name, player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
