package blackjack.domain;

import java.util.Set;

public class Card {

    private final CardSuit suit; //카드 무늬:다이아몬드, 하트, 스페이드, 클로버
    private final CardRank rank; //카드 숫자:1~9, J, Q, K, A

    public Card(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Set<Integer> checkScore() {
        return rank.getScore();
    }

    @Override
    public String toString() {
        return rank.getName() + suit.getName();
    }
}
