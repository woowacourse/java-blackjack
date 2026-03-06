package domain;

import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Pattern pattern;

    public Card(Rank rank, Pattern pattern){
        this.rank=rank;
        this.pattern=pattern;
    }

    public Integer getCardScore(){
        return rank.getValue();
    }

    public String getCardCode() {
        return rank.getCode();
    }

    public String getCardShape(){
        return pattern.getShape();
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && pattern == card.pattern;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, pattern);
    }
}
