package domain;

public abstract class Gamer {

    private final String name;
    private final Cards cards = new Cards();

    protected Gamer(String name) {
        this.name = name;
    }

    public void prepareGame(Cards totalCard) {
        add(totalCard);
        add(totalCard);
    }

    public boolean isBurst() {
        return cards.isBurst();
    }

    public int getScore() {
        return cards.calculateTotalPoint();
    }

    public void add(Cards totalCards) {
        cards.add(totalCards.extractCard());
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return this.name;
    }

    public abstract void hit(Cards totalCards);
}

