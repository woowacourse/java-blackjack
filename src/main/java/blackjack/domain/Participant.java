package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_MAX_NUMBER = 21;
    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;
    public static final int FIRST_HIT_CARD_SIZE = 2;

    private final ParticipantName participantName;
    private final Cards cards;

    public Participant(final ParticipantName participantName) {
        this.cards = new Cards();
        this.participantName = participantName;
    }

    public void hit(final Card card) {
        cards.addCard(card);
    }

    public int getCardsCount() {
        return cards.getCardSize();
    }

    public boolean hasAceCard() {
        return cards.hasAceCard();
    }

    public boolean judgeBlackjack() {
        return cards.getCardSize() == FIRST_HIT_CARD_SIZE && calculateCardNumber() == BLACKJACK_MAX_NUMBER;
    }

    public int calculateCardNumber() {
        int totalSumAceCardValueOne = cards.calculateCardNumberAceCardValueOne();
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
        return cards.getCardNumberByIndex(index);
    }

    public CardSuit getCardSuit(final int index) {
        return cards.getCardSuitByIndex(index);
    }
}
