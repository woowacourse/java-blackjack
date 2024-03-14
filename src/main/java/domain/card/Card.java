package domain.card;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Card {
    private final Symbol symbol;
    private final Rank rank;

    public Card(final Symbol symbol, final Rank rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    //TODO : static 제거
    public static List<Card> createCardPack() {
        return Stream.of(Symbol.values())
                .flatMap(symbol -> Rank.getRanks().stream()
                        .map(rank -> new Card(symbol, rank)))
                .toList();
    }

    public int getScore() {
        return rank.getScore();
    }


    public Symbol getSymbol() {
        return symbol;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return symbol == card.symbol && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, rank);
    }
}
