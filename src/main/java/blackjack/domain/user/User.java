package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.vo.Name;
import java.util.List;

public abstract class User {

    protected final Cards cards;
    private final Name name;

    protected User(Name name) {
        cards = new Cards();
        this.name = name;
    }

    public List<Card> showCards() {
        return cards.getCards();
    }

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public boolean isBlackJack() {
        return ((cards.isDefaultSize()) && cards.isSameBlackJackScore());
    }

    public boolean isBlackJackScore() {
        return cards.isSameBlackJackScore();
    }

    public String getName(){
        return name.getName();
    }

    public abstract boolean isDealer();

    public abstract List<Card> showInitCards();

    public abstract boolean isDrawable();
}
