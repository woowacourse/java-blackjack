package blackjack.domain;

import java.util.Objects;

public class Card {

    private final String rank;
    private final String shape;

    public Card(String rank, String shape) {
        this.rank = rank;
        this.shape = shape;
    }

    public int translateToScore() {
        if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
            return 10;
        }
        if (rank.equals("A")) {
            return 11;
        }

        return convertToNumber(rank);
    }

    public String getRank() {
        return rank;
    }

    private static int convertToNumber(String rank) {
        int score;
        try {
            score = Integer.parseInt(rank);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("J,Q,K 외의 문자열 입력은 불가능합니다.");
        }

        if (score > 10 || score < 2) {
            throw new IllegalArgumentException("2~10 사이의 숫자만 가능합니다.");
        }
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card card)) {
            return false;
        }
        return rank.equals(card.rank) &&
                shape.equals(card.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, shape);
    }

    public String getShape() {
        return shape;
    }
}
