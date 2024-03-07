package domain;

public class Dealer extends Participant {

    private static final String NAME = "딜러";
    private static final int INIT_HANDS_SIZE = 2;

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

    public void deal(final Participant participant, final Answer answer) {
        if (Answer.HIT.equals(answer)) {
            participant.add(cardDeck.pop());
        }
    }

    public void deal() {
        super.add(cardDeck.pop());
    }

    public int getTotalCardSum() {
        return super.handsSum();
    }

    public Hands getHands() {
        return super.getHands();
    }

    public String getName() {
        return NAME;
    }
}
