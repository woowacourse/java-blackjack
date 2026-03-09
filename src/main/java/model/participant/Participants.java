package model.participant;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final List<Participant> values;

    private Participants(List<Participant> values) {
        this.values = List.copyOf(values);
    }

    public static Participants from(List<String> names) {
        List<Participant> participants = new ArrayList<>();
        participants.add(Dealer.from("딜러"));

        List<Participant> players = names.stream()
                .map(Player::of)
                .toList();

        participants.addAll(players);

        return new Participants(participants);
    }

    public Dealer getDealer() {
        return (Dealer) values.getFirst();
    }

    public List<Player> getPlayers() {
        return values.stream()
                .filter(participant -> participant instanceof Player)
                .map(participant -> (Player) participant)
                .toList();
    }

    public List<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Participant participant : values) {
            names.add(participant.getName());
        }

        return names;
    }

    public List<Participant> toList() {
        return List.copyOf(values);
    }
}
