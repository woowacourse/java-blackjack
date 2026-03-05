package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Participants implements Iterable<Participant> {
    private List<Participant> values;

    private Participants(String[] values) {
        this.values = Arrays.stream(values)
                .map(Participant::of)
                .toList();
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
