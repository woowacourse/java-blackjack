package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomCardStrategy implements CardStrategy {

    public Stack<Card> generateDeck() {
        Stack<Card> cards = Arrays.stream(CardNumber.values())
                .flatMap(RandomCardStrategy::getCardStream)
                .collect(Collectors.toCollection(Stack::new));

        Collections.shuffle(cards);
        return cards;
    }

    private static Stream<Card> getCardStream(CardNumber cardNumber) {
        return Arrays.stream(CardType.values())
                .map(cardType -> new Card(cardType, cardNumber));
    }
}
