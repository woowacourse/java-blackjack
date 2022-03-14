package domain.participant;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final String NOT_EXIST_DEALER_ERROR = "딜러가 존재하지 않습니다.";

    private final List<Participant> participants = new ArrayList<>();

    public Participants(List<Participant> participants) {
        this.participants.addAll(participants);
    }

    public void add(Participant participant) {
        this.participants.add(participant);
    }

    public List<Participant> findPlayers() {
        return participants.stream()
                .filter(participant -> participant.isSameClass(Player.class))
                .collect(Collectors.toUnmodifiableList());
    }

    public Participant findDealer() {
        return participants.stream()
                .filter(participant -> participant.isSameClass(Dealer.class))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_DEALER_ERROR));
    }

    public void drawCard(Participant participant, Card card) {
        int index = participants.indexOf(participant);
        Participant nowParticipant = participants.get(index);
        nowParticipant.drawCard(card);
    }
}
