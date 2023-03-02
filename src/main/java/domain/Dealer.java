package domain;

class Dealer {

    public static final int STANDARD_FOR_HIT = 16;

    private final String name;
    private final Cards cards;

    public Dealer(Cards cards) {
        this("딜러", cards);
    }

    private Dealer(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean canAddCard() {
        return cards.getScore() <= STANDARD_FOR_HIT;
    }
}
