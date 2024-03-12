package blackjack.domain.card;

public class Card {

    private final Rank rank;
    private final Symbol symbol;

    public Card(Rank rank, Symbol symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public int getScore() {
        return rank.getScore();
    }

    public Rank getRank() {
        return rank;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
