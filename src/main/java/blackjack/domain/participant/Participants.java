package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Participants {
    private static final String DUPLICATION_NAME = "참가자들의 이름은 중복이 없어야 합니다.";

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateNameDuplication(participants);
        this.participants = participants;
    }

    public static Participants of(Dealer dealer, List<Player> players) {
        List<Participant> participants = Stream.concat(Stream.of(dealer), players.stream())
                .collect(Collectors.toList());
        return new Participants(participants);
    }

    private void validateNameDuplication(List<Participant> participants) {
        long participantsCount = participants.size();
        long distinctParticipantsCount = participants.stream()
                .map(Participant::getName)
                .distinct()
                .count();
        if (participantsCount != distinctParticipantsCount) {
            throw new IllegalArgumentException(DUPLICATION_NAME);
        }
    }

    public void receiveDefaultCards(CardDeck cardDeck) {
        participants.forEach(participant -> participant.receiveCard(cardDeck.draw()));
    }

    public List<Participant> toList() {
        return Collections.unmodifiableList(participants);
    }
}
