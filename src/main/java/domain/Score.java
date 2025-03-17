package domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum Score {
    BLACKJACK("블랙잭", (total, cardCount) -> total == 21 && cardCount == 2, 1),
    BUST("버스트", (total, cardCount) -> total > 21, 23),

    TWENTY_ONE("21", (total, cardCount) -> total == 21, 2),
    TWENTY("20", (total, cardCount) -> total == 20, 3),
    NINETEEN("19", (total, cardCount) -> total == 19, 4),
    EIGHTEEN("18", (total, cardCount) -> total == 18, 5),
    SEVENTEEN("17", (total, cardCount) -> total == 17, 6),
    SIXTEEN("16", (total, cardCount) -> total == 16, 7),
    FIFTEEN("15", (total, cardCount) -> total == 15, 8),
    FOURTEEN("14", (total, cardCount) -> total == 14, 9),
    THIRTEEN("13", (total, cardCount) -> total == 13, 10),
    TWELVE("12", (total, cardCount) -> total == 12, 11),
    ELEVEN("11", (total, cardCount) -> total == 11, 12),
    TEN("10", (total, cardCount) -> total == 10, 13),
    NINE("9", (total, cardCount) -> total == 9, 14),
    EIGHT("8", (total, cardCount) -> total == 8, 15),
    SEVEN("7", (total, cardCount) -> total == 7, 16),
    SIX("6", (total, cardCount) -> total == 6, 17),
    FIVE("5", (total, cardCount) -> total == 5, 18),
    FOUR("4", (total, cardCount) -> total == 4, 19),
    THREE("3", (total, cardCount) -> total == 3, 20),
    TWO("2", (total, cardCount) -> total == 2, 21),
    ONE("1", (total, cardCount) -> total == 1, 22);

    private final String title;
    private final BiPredicate<Integer, Integer> condition;
    private final int rank;

    Score(String title, BiPredicate<Integer, Integer> condition, int rank) {
        this.title = title;
        this.condition = condition;
        this.rank = rank;
    }

    public static Score from(List<TrumpCard> cards) {
        int totalScore = calculateTotalScore(cards);
        int cardCount = cards.size();

        return Arrays.stream(Score.values())
                .filter(score -> score.condition.test(totalScore, cardCount))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 점수입니다."));
    }

    private static int calculateTotalScore(List<TrumpCard> cards) {
        int aceCount = (int) cards.stream().filter(c -> c.getRank() == Rank.ACE).count();
        int totalScore = cards.stream().mapToInt(c -> c.getRank().getValue()).sum();

        return calculateAceBonus(totalScore, aceCount);
    }

    private static int calculateAceBonus(int totalScore, int aceCount) {
        while (aceCount-- > 0 && totalScore + 10 <= 21) {
            totalScore += 10;
        }
        return totalScore;
    }

    public boolean isLowerThan(Score o) {
        return this.rank > o.rank;
    }

    public boolean isHigherThan(Score o) {
        return this.rank < o.rank;
    }

    public String getTitle() {
        return title;
    }
}
