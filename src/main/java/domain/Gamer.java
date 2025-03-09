package domain;

public abstract class Gamer {

    private final String name;
    private final Cards cards = new Cards();

    protected Gamer(String name) {
        this.name = name;
    }

    public abstract boolean hit(Cards cards);

    public void prepareGame(Cards totalCard) {
        add(totalCard);
        add(totalCard);
    }

    public void add(Cards totalCards) {
        cards.add(totalCards.extractCard());
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getScore() {
        return cards.calculateTotalPoint();
    }

    public String getName() {
        return this.name;
    }

    public Cards getCards() {
        return cards;
    }
}

