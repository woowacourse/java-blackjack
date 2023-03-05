package domain.participant;

import domain.Deck;

import java.util.List;
import java.util.stream.Collectors;

public final class Participants {

    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants create(final List<Name> playerNames) {
        final List<Participant> participantList = playerNames.stream()
                .map(Player::of)
                .collect(Collectors.toList());
        participantList.add(Dealer.create());
        return new Participants(participantList);
    }

    public void initGame(Deck deck) {
        for (Participant participant : participants) {
            participant.takeCard(deck.drawCard());
        }
    }
}
