package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<AbstractParticipant> participants;

    public Participants(List<AbstractParticipant> participants) {
        this.participants = participants;
    }

    public Dealer extractDealer() {
        return (Dealer) participants.stream()
                .filter(AbstractParticipant::isDealer)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Dealer가 존재하지 않습니다!"));
    }

    public List<Player> extractPlayers() {
        return participants.stream()
                .filter(participant -> !participant.isDealer())
                .map(Player.class::cast)
                .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }
}
