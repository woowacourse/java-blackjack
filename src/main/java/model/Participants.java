package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;

public class Participants implements Iterable<Participant> {
    private final List<Participant> values;

    private Participants(List<Participant> values) {
        this.values = values;
    }

    public static Participants of(List<String> names) {
        List<Participant> participants = new ArrayList<>();
        participants.add(Dealer.of("딜러"));

        List<Participant> players = names.stream()
                .map(Player::of)
                .toList();
        participants.addAll(players);

        return new Participants(participants);
    }

    public Participant getDealer() {
        return values.getFirst();
    }

    public List<Participant> getPlayers() {
        return values.subList(1, values.size());
    }

    public List<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Participant participant : values) {
            names.add(participant.getName());
        }

        return names;
    }

    @Override
    public Iterator<Participant> iterator() {
        return List.copyOf(values).iterator();
    }
}
