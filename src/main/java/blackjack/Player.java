package blackjack;

public class Player implements User {

    private final Cards cards;
    private final String name;

    public Player(String name) {
        this.cards = new Cards();
        this.name = name;
    }

    @Override
    public void hit(Card card) {
        this.cards.addCard(card);
    }

    @Override
    public String showCards() {
        return this.cards.showCards();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return cards.getScore();
    }
}
