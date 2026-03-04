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
}
