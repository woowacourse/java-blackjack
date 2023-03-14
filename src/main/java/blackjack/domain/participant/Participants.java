package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.betting.BettingMoney;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final String DEALER_NAME = "딜러";

    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants generate(List<String> playersName, List<String> bettingMoney) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer(new ParticipantName(DEALER_NAME), new Hand()));
        for (int i = 0, end = playersName.size(); i < end; i++) {
            participants.add(new Player(new ParticipantName(
                playersName.get(i).trim()),
                new Hand(),
                new BettingMoney(bettingMoney.get(i))));
        }
        return new Participants(participants);
    }

    public List<Player> extractPlayers() {
        return participants.stream()
            .filter(participant -> participant.getClass() == Player.class)
            .map(participant -> (Player) participant)
            .collect(Collectors.toUnmodifiableList());
    }

    public Dealer extractDealer() {
        return (Dealer) participants.stream()
            .filter(participant -> participant.getClass() == Dealer.class)
            .findFirst()
            .get();
    }

    public List<String> getParticipantsName() {
        return participants.stream()
            .map(participant -> participant.getParticipantName().getName())
            .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
