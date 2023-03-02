package domain;

class Player {

    public static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        validateNameLength(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 5자 이하여야 합니다.");
        }
    }

    public boolean canAddCard() {
        return cards.getScore() < Cards.BLACKJACK_NUMBER;
    }
}
