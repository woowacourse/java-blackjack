package BlackJack.domain;

public abstract class User {

    protected String name;
    protected Cards cards;

    public User(String name){
        this.name = name;
        this.cards = CardFactory.drawTwoCards();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    abstract public void addCard();

}
