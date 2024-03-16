package player;

import card.Card;
import cardGame.GameParticipantCards;
import java.util.List;

public class Player extends GameParticipantCards {

    private final Name name;
    private final BetMoney betMoney;

    private Player(List<Card> cardDeck, Name name, int money) {
        super(cardDeck);
        this.name = name;
        this.betMoney = new BetMoney(money);
    }

    public static Player joinGame(String name, List<Card> cards, int money) {
        return new Player(cards, new Name(name), money);
    }

    public Name getName() {
        return name;
    }

    public int betMoneyResult(double percent) {
        return (int) betMoney.betMoneyResult(percent);
    }
}
