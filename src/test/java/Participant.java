import java.util.ArrayList;
import java.util.List;

public class Participant {
    private String name;
    private List<Card> hands;

    public Participant(String name) {
        this.name = name;
        this.hands = new ArrayList<Card>();
    }

    public static Participant from(String input) {
        return new Participant(input);
    }

    public String getName() {
        return name;
    }

    public List<Card> draw(Card card) {
        hands.add(card);
        return List.copyOf(hands);
    }
}
