package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateOverlappedNames(participants);
        this.participants = participants;
    }

    public static Participants from(List<String> participantNames) {
        List<Participant> participants = participantNames.stream()
                                                         .map(Player::new)
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
            participant.receiveDefaultCards(cardDeck.drawDefaultCards());
        }
    }

    public List<String> getParticipantNames() {
        return participants.stream()
                           .map(Participant::getName)
                           .collect(Collectors.toList());
    }

//    public List<List<Card>> getParticipantsCards() {
//        return participants.stream()
//                           .map(Participant::getCards)
//                           .collect(Collectors.toList());
//    }

    public List<Participant> toList() {
        return participants;
    }
}
