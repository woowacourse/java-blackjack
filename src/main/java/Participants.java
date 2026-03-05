import java.util.Arrays;
import java.util.List;

public class Participants {
    private List<Participant> values;

    private Participants(String[] values) {
        this.values = Arrays.stream(values)
                .map(Participant::from)
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
}
