package domain;

import java.util.Set;

public class Player {

    public static final int SUM_LIMIT = 21;
    private final Cards cards;

    private Player(Cards cards) {
        this.cards = cards;
    }

    public static Player init() {
        return new Player(Cards.empty());
    }

    public static Player of(Cards cards) {
        return new Player(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }

    public int getResult() {
        Set<Integer> coordinates = cards.getCoordinateSums();
        return coordinates.stream()
                .filter(coordinate -> coordinate <= SUM_LIMIT)
                .mapToInt(i -> i)
                .max()
                .orElseThrow(() -> new IllegalStateException("카드가 존재하지 않는 플레이어입니다."));
    }
}
