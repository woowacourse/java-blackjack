package domain;

import java.util.Set;

public class Participant {

    private static final int SUM_LIMIT = 21;
    protected final Cards cards;

    public Participant(Cards cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }

    public int getResult() {
        Set<Integer> coordinates = cards.getCoordinateSums();
        if (isBurst()) {
            return getMinSum(coordinates);
        }
        return getMaxSum(coordinates);
    }

    public boolean isBurst() {
        Set<Integer> coordinates = cards.getCoordinateSums();
        return coordinates.stream().noneMatch(coordinate -> coordinate <= SUM_LIMIT);
    }

    public GameResult calculateGameResult(Participant otherParticipant) {
        if (getResult() > otherParticipant.getResult()) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private int getMaxSum(Set<Integer> coordinates) {
        return coordinates.stream()
                .filter(coordinate -> coordinate <= SUM_LIMIT)
                .mapToInt(i -> i)
                .max()
                .orElseThrow(() -> new IllegalStateException("카드가 존재하지 않는 플레이어입니다."));
    }

    private int getMinSum(Set<Integer> coordinates) {
        return coordinates.stream()
                .mapToInt(i -> i)
                .min()
                .orElseThrow(() -> new IllegalStateException("카드가 존재하지 않는 플레이어입니다."));
    }
}
