package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import util.InputParser;

public class Participants implements Iterable<Participant> {
    private static final String DEALER_PARTICIPANT = "딜러,";
    private List<Participant> values;

    private Participants(List<Participant> values) {
        this.values = values;
    }

    public static Participants of(String input) {
        String rawPariticipants = DEALER_PARTICIPANT + input;
        String[] names = InputParser.parseName(rawPariticipants);

        List<Participant> participants = new ArrayList<>();
        participants.add(Dealer.of(names[0]));
        for (int i = 1; i < names.length; i++) {
            participants.add(Player.of(names[i]));
        }

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
        return Collections.unmodifiableList(values).iterator();
    }
}
