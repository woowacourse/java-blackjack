package domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum Score {
    BLACKJACK("블랙잭", (total, cardCount) -> total == 21 && cardCount == 2),
    BUST("버스트", (total, cardCount) -> total > 21),

    ONE("1", (total, cardCount) -> total == 1),
    TWO("2", (total, cardCount) -> total == 2),
    THREE("3", (total, cardCount) -> total == 3),
    FOUR("4", (total, cardCount) -> total == 4),
    FIVE("5", (total, cardCount) -> total == 5),
    SIX("6", (total, cardCount) -> total == 6),
    SEVEN("7", (total, cardCount) -> total == 7),
    EIGHT("8", (total, cardCount) -> total == 8),
    NINE("9", (total, cardCount) -> total == 9),
    TEN("10", (total, cardCount) -> total == 10),
    ELEVEN("11", (total, cardCount) -> total == 11),
    TWELVE("12", (total, cardCount) -> total == 12),
    THIRTEEN("13", (total, cardCount) -> total == 13),
    FOURTEEN("14", (total, cardCount) -> total == 14),
    FIFTEEN("15", (total, cardCount) -> total == 15),
    SIXTEEN("16", (total, cardCount) -> total == 16),
    SEVENTEEN("17", (total, cardCount) -> total == 17),
    EIGHTEEN("18", (total, cardCount) -> total == 18),
    NINETEEN("19", (total, cardCount) -> total == 19),
    TWENTY("20", (total, cardCount) -> total == 20),
    TWENTY_ONE("21", (total, cardCount) -> total == 21);

    private final String title;
    private final BiFunction<Integer, Integer, Boolean> condition;


    Score(String title, BiFunction<Integer, Integer, Boolean> condition) {
        this.title = title;
        this.condition = condition;
    }

    public static Score from(List<TrumpCard> cards) {
        int totalScore = calculateTotalScore(cards);
        int cardCount = cards.size();

        return Arrays.stream(Score.values())
                .filter(score -> score.condition.apply(totalScore, cardCount))
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
}
