package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateOverlappedNames(participants);
        this.participants = participants;
    }

    public static Participants of(Dealer dealer, List<Player> players) {
        List<Participant> participants = Stream.concat(Stream.of(dealer), players.stream())
                                               .collect(Collectors.toList());
        return new Participants(participants);
    }

    private void validateOverlappedNames(List<Participant> participants) {
        long participantsCount = participants.size();
        long distinctParticipantsCount = participants.stream()
                                                     .map(Participant::getName)
                                                     .distinct()
                                                     .count();
        if (participantsCount != distinctParticipantsCount) {
            throw new IllegalArgumentException("참가자들의 이름은 중복이 없어야 합니다.");
        }
    }

    public void receiveDefaultCards(CardDeck cardDeck) {
        for (Participant participant : participants) {
            participant.receiveCards(cardDeck.drawDefaultCards());
        }
    }

    public List<Participant> toList() {
        return Collections.unmodifiableList(participants);
    }
}
