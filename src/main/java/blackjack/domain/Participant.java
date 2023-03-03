package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;

    private final ParticipantName participantName;
    private final List<Card> receivedCards = new ArrayList<>();

    public Participant(ParticipantName participantName) {
        this.participantName = participantName;
    }

    public void hit(Card card) {
        receivedCards.add(card);
    }

    public List<Card> getReceivedCards() {
        return receivedCards;
    }

    public boolean participantHasAceCard() {
        return receivedCards.stream()
            .anyMatch(card -> card.getCardNumber().equals(CardNumber.ACE));
    }

    public int calculateCardNumberAceCardValueOne() {
        return receivedCards.stream()
            .mapToInt(card -> card.getCardNumber().value)
            .sum();
    }

    public boolean judgeBlackjack() {
        return receivedCards.size() == 2 && calculateCardNumber() == 21;
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
        return receivedCards.get(index).getCardNumber();
    }

    public CardSuit getCardSuit(int index) {
        return receivedCards.get(index).getCardSuit();
    }

    public boolean isDealer() {
       return participantName.equals(new ParticipantName("딜러"));
    }

    abstract boolean decideHit();
}
