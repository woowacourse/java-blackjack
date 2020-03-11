package user;

import card.Deck;

import java.util.Objects;

public class Participant {
    private static final String YES = "y";
    private static final String NO = "n";

    private ParticipantName name;
    private Hands hands;

    public Participant(ParticipantName name, Deck deck) {
        this.name = name;
        this.hands = new Hands(deck);
    }

    public Participant(ParticipantName name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    // TODO : 메서드 분리
    public void needMoreCard(String answer, Deck deck) {
        if (Objects.isNull(answer) || answer.isEmpty()) {
            throw new InvalidParticipantException(InvalidParticipantException.NULL_OR_EMPTY);
        }

        if (answer.equals(YES)) {
            hit(deck);
        }

        if (answer.equals(NO)) {
            return;
        }

        throw new InvalidParticipantException(InvalidParticipantException.INVALID_INPUT);
    }

    private void hit(Deck deck) {
        hands.draw(deck);
    }

    public int handSize() {
        return hands.size();
    }

    public boolean checkBlackJack() {
        return hands.isBlackJack();
    }

    public boolean checkBurst() {
        return hands.isBurst();
    }
}
