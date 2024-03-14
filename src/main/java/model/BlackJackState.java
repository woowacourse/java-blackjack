package model;

public enum BlackJackState {
    STAND,
    BUST,
    BLACKJACK,
    ;

    public static BlackJackState createBlackJackState(int cardsSum, int cardsCount) {
        if(cardsSum == 21 && cardsCount == 2) {
            return BLACKJACK;
        }
        if(cardsSum > 21) {
            return BUST;
        }
        return STAND;
    }
}
