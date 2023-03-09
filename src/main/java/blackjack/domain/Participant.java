package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_MAX_NUMBER = 21;
    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;
    public static final int FIRST_HIT_CARD_SIZE = 2;

    private final ParticipantName participantName;
    private final List<Card> cards;

    public Participant(final ParticipantName participantName) {
        this.cards = new ArrayList<>();
        this.participantName = participantName;
    }

    public void hit(final Card card) {
        cards.add(card);
    }

    public int getCardsCount() {
        return cards.size();
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
        return cards.size() == FIRST_HIT_CARD_SIZE && calculateCardNumber() == BLACKJACK_MAX_NUMBER;
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
