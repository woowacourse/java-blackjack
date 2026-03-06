package view;

import domain.Rank;

public enum RankDisplayName {
    ACE(Rank.ACE,"A"),
    TWO(Rank.TWO,"2"),
    THREE(Rank.THREE,"3"),
    FOUR(Rank.FOUR,"4"),
    FIVE(Rank.FIVE,"5"),
    SIX(Rank.SIX,"6"),
    SEVEN(Rank.SEVEN,"7"),
    EIGHT(Rank.EIGHT,"8"),
    NINE(Rank.NINE,"9"),
    TEN(Rank.TEN,"10"),
    JACK(Rank.JACK,"J"),
    QUEEN(Rank.QUEEN,"Q"),
    KING(Rank.KING,"K")
    ;
    private final Rank rank;
    private final String message;

    RankDisplayName(Rank rank, String message) {
        this.rank = rank;
        this.message = message;
    }

    public static String convertToMessage(Rank rank){
        for (RankDisplayName value : values()) {
            if(value.rank.equals(rank))
                return value.message;
        }
        throw new IllegalArgumentException();
    }
}
