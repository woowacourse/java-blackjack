package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;

public abstract class Participant {

    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;
    private static final int WIN_MAX_VALUE = 21;
    private static final int BLACKJACK_SIZE = 2;
    private static final String DEALER_NAME = "딜러";

    private final ParticipantName participantName;
    private final Hand hand;

    public Participant(ParticipantName participantName, Hand hand) {
        this.participantName = participantName;
        this.hand = hand;
    }

    public void hit(Card card) {
        hand.hitCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public boolean participantHasAceCard() {
        return hand.getReceivedCards().stream()
            .anyMatch(card -> card.getCardNumber().equals(CardNumber.ACE));
    }

    public int calculateCardNumberAceCardValueOne() {
        return hand.getReceivedCards().stream()
            .mapToInt(card -> card.getCardNumber().getValue())
            .sum();
    }

    public boolean judgeBlackjack() {
        return hand.getReceivedCards().size() == BLACKJACK_SIZE && calculateCardNumber() == WIN_MAX_VALUE;
    }

    public int calculateCardNumber() {
        int totalSumAceCardValueOne = calculateCardNumberAceCardValueOne();
        if (participantHasAceCard() && totalSumAceCardValueOne <= JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM) {
            return totalSumAceCardValueOne + CALIBRATED_ACE_CARD_ELEVEN_VALUE;
        }
        return totalSumAceCardValueOne;
    }

    public ParticipantName getParticipantName() {
        return participantName;
    }

    public String getName() {
        return participantName.getName();
    }
    public CardNumber getCardNumber(int index) {
        return hand.getReceivedCards().get(index).getCardNumber();
    }

    public CardSuit getCardSuit(int index) {
        return hand.getReceivedCards().get(index).getCardSuit();
    }

    public boolean isDealer() {
       return participantName.equals(new ParticipantName(DEALER_NAME));
    }

    abstract boolean decideHit();
}
