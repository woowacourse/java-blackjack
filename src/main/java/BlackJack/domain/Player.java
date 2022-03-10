package BlackJack.domain;

public class Player extends User{
    public Player(String name, Cards cards) {
        super(name,cards);
    }

    @Override
    public void addCard() {
        cards.add(CardFactory.drawOneCard());
    }

}
