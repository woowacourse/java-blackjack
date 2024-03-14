package blackjack.domain.participant;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Player {
    private final Name name;
    private final Hands hands;
    private final BetMoney betMoney;

    private Player(Name name, Hands hands, BetMoney betMoney) {
        this.name = name;
        this.hands = hands;
        this.betMoney = betMoney;
    }

    public static Player createPlayer(Name name, List<Card> initialCards, BetMoney betMoney) {
        Hands initialHands = new Hands();
        for (Card card : initialCards) {
            initialHands.addCard(card);
        }
        return new Player(name, initialHands, betMoney);
    }

    public boolean attemptDraw(BooleanSupplier supplier, Deck deck) {
        if (supplier.getAsBoolean()) {
            hands.addCard(deck.draw());
            return true;
        }
        return false;
    }

    public boolean canDraw() {
        return !hands.isBust();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }

    public Hands getHands() {
        return hands;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }

    public int getHandsScore() {
        return hands.getHandsScore();
    }
}
