package user;

import card.Deck;

import java.util.Objects;

public class Participant implements User {
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

    public void needMoreCard(String answer, Deck deck) {
        checkNullOrEmpty(answer);
        checkYesOrNo(answer);

        if (answer.equals(YES)) {
            hit(deck);
        }
    }

    private void checkNullOrEmpty(String answer) {
        if (Objects.isNull(answer) || answer.isEmpty()) {
            throw new InvalidParticipantException(InvalidParticipantException.NULL_OR_EMPTY);
        }
    }

    private void checkYesOrNo(String answer) {
        if (answer.equals(YES) || answer.equals(NO)) {
            return;
        }
        throw new InvalidParticipantException(InvalidParticipantException.INVALID_INPUT);
    }

    public boolean checkBlackJack() {
        return hands.isBlackJack();
    }

    @Override
    public void hit(Deck deck) {
        hands.draw(deck);
    }

    @Override
    public boolean checkBurst() {
        return hands.isBurst();
    }

    @Override
    public int handSize() {
        return hands.size();
    }
}
