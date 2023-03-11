package domain.player;

import domain.card.Deck;
import util.HitOrStay;
import util.Notice;

import java.util.HashSet;
import java.util.List;

public class Participants {

    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants of(final List<Participant> participants) {
        validateDuplicate(participants);

        return new Participants(participants);
    }

    public void takeCard(final Deck deck) {
        participants.forEach(participant -> participant.takeCard(deck.dealCard()));
    }

    public void playParticipantsTurn(final Deck deck, final HitOrStay hitOrStay, final Notice<Participant> notice) {
        participants.forEach(participant -> participant.playParticipantTurn(deck, hitOrStay, notice));
    }

    private static void validateDuplicate(final List<Participant> participants) {
        if (isDuplicate(participants)) {
            throw new IllegalArgumentException("중복되지 않은 이름만 입력해주세요");
        }
    }

    private static boolean isDuplicate(final List<Participant> participants) {
        final int uniqueNameCount = new HashSet<>(participants).size();
        return uniqueNameCount < participants.size();
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
