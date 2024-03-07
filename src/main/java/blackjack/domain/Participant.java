package blackjack.domain;

import blackjack.service.BlackjackGame;
import java.util.ArrayList;

public class Participant {
    private final ParticipantsName name;
    protected final Hands hands;

    protected Participant(final ParticipantsName name, final Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public static Participant from(final String name) {
        if (BlackjackGame.DEALER_SIGNAL.equals(name)) {
            return new Dealer(name);
        }
        return new Participant(new ParticipantsName(name), new Hands(new ArrayList<>()));
    }

    public Score calculate() {
        int sum = hands.sum();
        int aceCount = hands.countAce();

        while (sum > BlackjackStatus.BLACKJACK_NUMBER && aceCount > 0) {
            sum = sum - 10;
            aceCount--;
        }
        return new Score(sum);
    }

    public void addCard(final Card card) {
        hands.add(card);
    }

    public BlackjackStatus getStatus() {
        return BlackjackStatus.from(calculate());
    }

    public Hands getHands() {
        return new Hands(hands.getCards());
    }

    public ParticipantsName getName() {
        return name;
    }

    public boolean isName(final ParticipantsName name) {
        return this.name.equals(name);
    }
}
