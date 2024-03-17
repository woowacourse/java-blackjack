package domain.card;

import strategy.ShuffleStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardFactory {

    public Stack<Card> createCards(ShuffleStrategy shuffleStrategy, int packCount) {
        List<Card> cards = Stream.generate(this::createCardPack)
                .limit(packCount)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return shuffleStrategy.shuffle(cards);
    }

    private List<Card> createCardPack() {
        return Stream.of(Symbol.values())
                .flatMap(symbol -> Rank.getRanks().stream()
                        .map(rank -> Card.of(symbol, rank)))
                .toList();
    }
}
