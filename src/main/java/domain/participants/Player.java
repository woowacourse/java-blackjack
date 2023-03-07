package domain.participants;

import domain.deck.Card;
import domain.deck.Cards;

import java.util.List;

public class Player {
    private static final int BLACK_JACK = 21;
    private static final String Y_COMMAND = "y";
    private final Name name;
    private final Cards cards;

    public Player(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
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

    public boolean canDrawCard(String command) {
        return !isOverPlayerBlackJack() && isCommandYes(command);
    }

    public boolean isCommandYes(String command) {
        return command.equals(Y_COMMAND);
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getPlayerCards() {
        return cards.getCards();
    }
}

