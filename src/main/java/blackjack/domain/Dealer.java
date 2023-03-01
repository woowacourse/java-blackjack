package blackjack.domain;

public class Dealer extends Participant{

    public static final int  DEALER_HIT_BASED_NUMBER= 16;

    public Dealer(ParticipantName participantName) {
        super(participantName);
    }

    @Override
    int calculateCardNumber() {
        return 0;
    }

    public boolean judgeDealerHit() {
        return totalSum() <= DEALER_HIT_BASED_NUMBER;
    }

    private int totalSum() {
        return getReceivedCards().stream()
            .mapToInt(card -> card.getCardNumber().value)
            .sum();
    }
}
