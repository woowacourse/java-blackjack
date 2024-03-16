package participant.player;

import card.Card;
import java.util.List;
import participant.Participant;

public class Player extends Participant {

    private final BetMoney betMoney;

    private Player(List<Card> cardDeck, Name name, int money) {
        super(cardDeck, name);
        this.betMoney = new BetMoney(money);
    }

    public static Player joinGame(String name, List<Card> cards, int money) {
        return new Player(cards, new Name(name), money);
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }
}
