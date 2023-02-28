package blackjack.domain;

public class Player {

    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private final Cards cards = Cards.generateEmptyCards();

    public Player(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름 길이는 최소 1글자에서 최대 5글자 입니다.");
        }
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
