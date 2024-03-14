package model;

public enum BlackJackState {
    STAND,
    BUST,
    BLACKJACK,
    ;

    public static BlackJackState createBlackJackState(int score, int cardsCount) {
        if(score == 21 && cardsCount == 2) {
            return BLACKJACK;
        }
        if(score > 21) {
            return BUST;
        }
        return STAND;
    }
}
