package BlackJack.domain;

public class Player extends User{
    public Player(String name) {
        super(name);
    }

    @Override
    void addCard() {
        cards.add(CardFactory.drawOneCard());
    }

}
