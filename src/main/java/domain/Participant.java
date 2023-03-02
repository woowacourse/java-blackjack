package domain;

abstract class Participant {

    public static final int INITIAL_CARDS_SIZE = 2;
    final String name;
    final Cards cards;

    public Participant(String name, Cards cards) {
        validateCardsSize(cards);
        this.name = name;
        this.cards = cards;
    }

    private void validateCardsSize(Cards cards) {
        if (cards.getSize() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException("초기 카드는 2장이어야 합니다.");
        }
    }

    public abstract boolean canAddCard();
}
