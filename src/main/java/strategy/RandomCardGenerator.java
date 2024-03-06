package strategy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomCardGenerator implements CardGenerator {

    private static final int INITIAL_CARD_AMOUNT = 52;

    @Override
    public List<Card> generate() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            cards.addAll(allCardsWithSameSymbol(symbol));
        }
        Collections.shuffle(cards);

        validateAmount(cards);
        return List.copyOf(cards);
    }

    private List<Card> allCardsWithSameSymbol(Symbol symbol) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Card(rank, symbol))
                .collect(Collectors.toList());
    }

    private void validateAmount(List<Card> cards) {
        if (cards.size() != INITIAL_CARD_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 덱에는 %d장의 카드가 있어야 합니다.", INITIAL_CARD_AMOUNT));
        }
    }
}
