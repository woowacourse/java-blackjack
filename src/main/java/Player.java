import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> cards = new ArrayList<>();
    private final PlayerName name;

    public Player(PlayerName name){
        this.name = name;
    }

    public void receiveCard(Card card){
        cards.add(card);
    }

}
