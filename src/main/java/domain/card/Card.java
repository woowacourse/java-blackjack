package domain.card;

import domain.Rank;
import domain.Suit;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isAce(){
        return rank.isAce();
    }

    public int getRankValue(){
        return rank.getScoreValue();
    }

    public int getOneIfAce(Card card) {
        if(card.isAce()){
            return 1;
        }
        return 0;
    }

    public int getRankValueIfNotAce(Card card) {
        if(!card.isAce()){
            return card.getRankValue();
        }
        return 0;
    }

    @Override
    public String toString() {
        return rank.getDisplayValue() + suit.getValue();
    }
}
