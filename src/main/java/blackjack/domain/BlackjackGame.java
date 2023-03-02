package blackjack.domain;

import blackjack.util.CardPickerGenerator;

public class BlackjackGame {

    private static final int FIRST_HIT_COUNT = 2;

    private final Participants participants;
    private final Cards cards;

    public BlackjackGame(Participants participants, Cards cards) {
        this.participants = participants;
        this.cards = cards;
    }

    public void settingGame(CardPickerGenerator cardPickerGenerator) {
        for (Participant participant : participants.getParticipants()) {
            firstHitRule(cardPickerGenerator, participant);
        }
    }

    private void firstHitRule(final CardPickerGenerator cardPickerGenerator, final Participant participant) {
        for (int count = 0; count < FIRST_HIT_COUNT; count++) {
            participant.hit(cards.pick(cardPickerGenerator));
        }
    }
}
