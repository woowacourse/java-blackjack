package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;

public class Participants implements Iterable<Participant> {
    private final List<Participant> values;

    private Participants(String[] values) {
        List<Participant> list = new ArrayList<>();
        list.add(Dealer.of(values[0]));
        for (int i = 1; i < values.length; i++) {
            list.add(Player.of(values[i]));
        }
        this.values = list;
    }

    public static Participants of(String[] splitValue) {
        return new Participants(splitValue);
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
        return Collections.unmodifiableList(values).iterator();
    }
}
