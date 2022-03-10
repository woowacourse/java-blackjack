package BlackJack.domain;

public class Dealer extends User{
    public Dealer(String name) {
        super(name);
    }

    @Override
    public void addCard() {
        cards.add(CardFactory.drawOneCard());
    }
}
