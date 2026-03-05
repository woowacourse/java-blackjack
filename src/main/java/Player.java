import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand = new ArrayList<>();
    private boolean isBust = false;

    public boolean isBust() {
        return isBust;
    }

    public void setBust() {
        isBust = true;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return List.copyOf(hand);
    }

    public List<String> getHandToString() {
        return hand.stream().map(Card::toString).toList();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                ", isBust=" + isBust +
                '}';
    }}
