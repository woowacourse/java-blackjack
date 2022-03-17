package blackjack.domain.User;

import blackjack.domain.Card.Card;
import blackjack.domain.Card.Cards;

public abstract class User {

    protected String name;
    protected Cards cards;

    public User(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public int getScore() {
        return cards.getScore();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public boolean isBust(){
        return cards.isBust();
    }

    public boolean isSameScoreWithNotBlackJack(User user) {
        return this.cards.isSameScoreWithNotBlackJack(user.cards);
    }

    public boolean isGreaterScoreThan(User user) {
        return this.cards.isGreaterThan(user.cards);
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    abstract public boolean isHit();
}
