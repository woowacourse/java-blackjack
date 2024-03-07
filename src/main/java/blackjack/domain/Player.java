package blackjack.domain;

import blackjack.service.BlackjackGame;
import java.util.ArrayList;

public class Player {
    private final PlayerName name;
    protected final Hands hands;

    protected Player(final PlayerName name, final Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public static Player from(final String name) {
        if (BlackjackGame.DEALER_SIGNAL.equals(name)) {
            return new Dealer(name);
        }
        return new Player(new PlayerName(name), new Hands(new ArrayList<>()));
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

    public PlayerName getName() {
        return name;
    }
}
