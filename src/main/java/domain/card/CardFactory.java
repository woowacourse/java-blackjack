package domain.card;

import java.util.List;
import java.util.stream.Stream;

public class CardFactory {
    public static List<Card> createCardPack() {
        return Stream.of(Symbol.values())
                .flatMap(symbol -> Rank.getRanks().stream()
                        .map(rank -> Card.of(symbol, rank)))
                .toList();
    }
}
