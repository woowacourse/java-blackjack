package player;

import card.Card;
import card.CardDeck;
import cardGame.GameParticipantCards;
import java.util.List;
import java.util.function.BiConsumer;
import player.dto.CardsStatus;

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

    public CardsStatus play(BiConsumer<Player, CardDeck> playMatch, CardDeck cardDeck) {
        playMatch.accept(this, cardDeck);
        return new CardsStatus(name, getCards());
    }

    public Name getName() {
        return name;
    }

    public int betMoneyResult(double percent) {
        return (int) betMoney.betMoneyResult(percent);
    }
}
