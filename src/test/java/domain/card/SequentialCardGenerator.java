package domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.strategy.CardGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SequentialCardGenerator implements CardGenerator {

    @Override
    public List<Card> generate() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            cards.addAll(allCardsWithSameSymbol(symbol));
        }
        return cards;
    }

    private List<Card> allCardsWithSameSymbol(Symbol symbol) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Card(rank, symbol))
                .collect(Collectors.toList());
    }
}
