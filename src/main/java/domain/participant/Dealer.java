package domain.participant;

import domain.Answer;
import domain.CardDeck;
import domain.Hands;

public class Dealer extends Participant {

    private static final String NAME = "딜러";
    private static final int INIT_HANDS_SIZE = 2;
    private static final int MIN_HANDS_SUM = 16;

    private final CardDeck cardDeck;

    public Dealer(final CardDeck cardDeck) {
        super(NAME, Hands.createEmptyPacket());
        this.cardDeck = cardDeck;
    }

    public Dealer(final CardDeck cardDeck, final Hands hands) {
        super(NAME, hands);
        this.cardDeck = cardDeck;
    }

    public void startDeal(final Players players) {
        for (int i = 0; i < INIT_HANDS_SIZE; i++) {
            players.forEach(player -> player.add(cardDeck.pop()));
            super.add(cardDeck.pop());
        }
    }

    public void deal(final Player player, final Answer answer) {
        if (Answer.HIT.equals(answer)) {
            player.add(cardDeck.pop());
        }
    }

    public int turn() {
        int result = 0;
        while (handsSum() <= MIN_HANDS_SUM) {
            super.add(cardDeck.pop());
            result++;
        }
        return result;
    }
}
