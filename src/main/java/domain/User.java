package domain;

public abstract  class User {
    private Name name;
    private Cards cards;

    public User(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public String getName() {
        return name.getValue();
    }

    public int getScore() {
        return cards.getScore();
    }

    public int getCardSize() {
        return cards.getSize();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public abstract boolean isWin(User user);
}
