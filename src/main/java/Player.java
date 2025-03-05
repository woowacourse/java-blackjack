import java.util.Objects;

//GameManager
//Map<P, Cs>
//setGame

public class Player {

    private final String name;
    private final Cards cards = new Cards();

    public Player(String name) {
        this.name = name;
    }

    public void draw(Cards totalCards) {
        cards.add(totalCards.extractCard());
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
