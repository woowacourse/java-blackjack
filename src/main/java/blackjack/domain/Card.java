package blackjack.domain;

public record Card(String rank, String shape) {

    public String getDisplayName() {
        return rank + shape;
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
        if (!(o instanceof Card(String rank1, String shape1))) {
            return false;
        }
        return rank.equals(rank1) &&
                shape.equals(shape1);
    }

}
