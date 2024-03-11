package domain.participant;

import controller.dto.PlayerOutcome;
import domain.constants.Outcome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Participants {
    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(final List<String> playerNames) {
        List<Participant> participants = new ArrayList<>();
        for (String playerName : playerNames) {
            participants.add(new Player(playerName));
        }
        participants.add(new Dealer());
        return new Participants(participants);
    }

    public List<PlayerOutcome> getPlayersOutcomeIf(final Function<Player, Boolean> function) {
        return getPlayers().stream()
                .map(participant -> new PlayerOutcome(
                        participant.getName(),
                        Outcome.from(function.apply(participant))
                ))
                .toList();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public List<Player> getPlayers() {
        return participants.stream()
                .filter(player -> player instanceof Player)
                .map(participant -> (Player) participant)
                .toList();
    }
}
