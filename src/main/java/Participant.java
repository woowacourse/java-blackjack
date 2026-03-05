import java.util.ArrayList;
import java.util.List;

public class Participant {
    private String name;
    private List<Card> hands;
    private boolean isDealer;

    private Participant(String name) {
        this.name = name;
        this.hands = new ArrayList<Card>();
    }

    private Participant(String name, boolean isDealer) {
        this.name = name;
        this.hands = new ArrayList<Card>();
        this.isDealer = true;
    }

    public static Participant from(String input) {
        return new Participant(input);
    }

    public static Participant from(String input, boolean isDealer) {
        return new Participant(input, isDealer);
    }

    public String getName() {
        return name;
    }

    public List<Card> draw(Card card) {
        hands.add(card);
        return List.copyOf(hands);
    }

    public List<Card> open(int round) {
        if (isDealer && round == 1) {
            return List.of(hands.getFirst());
        }

        return List.copyOf(hands);
    }

    public int calculateScore() {
        return hands.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::toValue)
                .sum();
    }
}
