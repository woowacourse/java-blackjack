package domain.participants;

import domain.BettingMoney;
import domain.Command;
import domain.deck.Card;
import domain.deck.Cards;

import java.util.List;

public class Player {
    private static final int BLACK_JACK = 21;
    private final Name name;
    private final Cards cards;
    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney money) {
        this.name = new Name(name);
        this.cards = new Cards();
        this.bettingMoney = money;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardsSum() {
        return cards.getSum();
    }

    public boolean isOverPlayerBlackJack() {
        return getCardsSum() >= BLACK_JACK;
    }

    public boolean canDrawCard(Command command) {
        return !isOverPlayerBlackJack() && isCommandYes(command);
    }

    public boolean isCommandYes(Command command) {
        return command.equals(Command.YES);
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getPlayerCards() {
        return cards.getCards();
    }
}

