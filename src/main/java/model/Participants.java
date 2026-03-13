package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.participant.Participant;

public final class Participants implements Iterable<Participant> {
    private final List<Participant> values;

    private Participants(List<Participant> values) {
        this.values = values;
    }

    public static Participants of(List<Participant> values) {

        return new Participants(values);
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

    public Participant findByName(String name) {
        for (Participant participant : values) {
            if (hasName(name, participant)) {
                return participant;
            }
        }

        throw new IllegalArgumentException("[ERROR] 해당 이름을 가진 참가자는 찾을 수 없습니다.");
    }

    private boolean hasName(String name, Participant participant) {
        if (participant.getName().equals(name)) {
            return true;
        }

        return false;
    }

    @Override
    public Iterator<Participant> iterator() {
        return Collections.unmodifiableList(values).iterator();
    }
}
