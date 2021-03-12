package blackjack.domain.card;

public enum Denomination {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private static final int ACE_MAX_SCORE = 11;
    private static final int ACE_MIN_SCORE = 1;

    private final int score;
    private final String name;


    Denomination(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static int selectAceScore(int score) {
        if (score > Cards.BLACKJACK_SCORE - ACE_MAX_SCORE) {
            return ACE_MIN_SCORE;
        }
        return ACE_MAX_SCORE;
    }

    public int score(){
        return this.score;
    }

    public String getName() {
        return this.name;
    }
}
