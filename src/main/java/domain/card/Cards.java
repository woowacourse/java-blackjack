package domain.card;

import domain.Score;

import java.util.*;
import java.util.stream.Collectors;

public class Cards {
    private final static int BLACKJACK_COUNT = 2;

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards empty() {
        return new Cards(new ArrayList<>());
    }

    public static Cards of(List<Card> cards) {
        return new Cards(cards);
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public List<Card> getValues() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBlackjack() {
        return getScore().isHit() && cards.size() == BLACKJACK_COUNT;
    }

    public Score getScore() {
        Set<Score> coordinates = getCoordinateScores();
        if (coordinates.stream().allMatch(Score::isBust)) {
            return getMinSum(coordinates);
        }
        Set<Score> filtered = coordinates.stream()
                .filter(score -> !score.isBust())
                .collect(Collectors.toSet());
        return getMaxSum(filtered);
    }

    private Set<Score> getCoordinateScores() {
        Set<Integer> coordinates = getCoordinateSumsByDfs(0, 0, new HashSet<>());
        return coordinates.stream().map(Score::new).collect(Collectors.toSet());
    }

    private Set<Integer> getCoordinateSumsByDfs(int index, int sum, Set<Integer> result) {
        if (index == cards.size()) {
            result.add(sum);
            return result;
        }
        Card card = cards.get(index);
        result = getCoordinateSumsByDfs(index + 1, sum + card.getNumberValue(), result);
        if (card.getCardNumber() == CardNumber.A) {
            result = getCoordinateSumsByDfs(index + 1, sum + 11, result);
        }
        return result;
    }

    private Score getMinSum(Set<Score> coordinates) {
        return coordinates.stream()
                .min(Comparator.comparing(Score::value))
                .orElseThrow(() -> new IllegalStateException("카드가 존재하지 않는 플레이어입니다."));
    }

    private Score getMaxSum(Set<Score> coordinates) {
        return coordinates.stream()
                .max(Comparator.comparing(Score::value))
                .orElseThrow(() -> new IllegalStateException("카드가 존재하지 않는 플레이어입니다."));
    }
}
