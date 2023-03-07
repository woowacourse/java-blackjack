package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;

    private final ParticipantName participantName;
    private final List<Card> cards;

    public Participant(final ParticipantName participantName) {
        this.cards = new ArrayList<>();
        this.participantName = participantName;
    }

    public void hit(final Card card) {
        cards.add(card);
    }

    public List<Card> getReceivedCards() {
        return cards;
    }

    public boolean hasAceCard() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int calculateCardNumberAceCardValueOne() {
        return cards.stream()
            .mapToInt(card -> card.getCardNumber().getValue())
            .sum();
    }

    public boolean judgeBlackjack() {
        return cards.size() == 2 && calculateCardNumber() == 21;
    }

    public int calculateCardNumber() {
        int totalSumAceCardValueOne = calculateCardNumberAceCardValueOne();
        if (hasAceCard() && totalSumAceCardValueOne <= JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM) {
            return totalSumAceCardValueOne + CALIBRATED_ACE_CARD_ELEVEN_VALUE;
        }
        return totalSumAceCardValueOne;
    }

    abstract boolean decideHit();

    public ParticipantName getParticipantName() {
        return participantName;
    }
    public String getName() {
        return participantName.getName();
    }

    public CardNumber getCardNumber(final int index) {
        return cards.get(index).getCardNumber();
    }

    public CardSuit getCardSuit(final int index) {
        return cards.get(index).getCardSuit();
    }
}
