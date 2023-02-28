package blackjack.domain;

public class Player {

    private static final int BLACK_JACK_SCORE = 21;
    private static final int ACE_ALTER_VALUE = 10;

    private final String name;
    private final Cards cards;

    public Player(String name) {
        validateSpace(name);
        validateLength(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void validateLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("이름의 길이는 5자 이상일 수 없습니다.");
        }
    }

    private void validateSpace(String name) {
        if(name.isBlank() || name.contains(" ")) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.sum();
        int aceCount = cards.getAceCount();

        while (sum > BLACK_JACK_SCORE && aceCount > 0) {
            sum -= ACE_ALTER_VALUE;
            aceCount -= 1;
        }
        return sum;
    }

    public Cards getCards() {
        return cards;
    }

}
