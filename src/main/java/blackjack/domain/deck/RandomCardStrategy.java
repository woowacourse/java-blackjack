package blackjack.domain.deck;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

public class RandomCardStrategy {

    // TODO: flatMap 내부를 메서드 분리 시도해보기
    public Stack<Card> generateDeck() {
        Stack<Card> cards = Arrays.stream(CardNumber.values())
            .flatMap(cardNumber ->
                Arrays.stream(CardType.values())
                    .map(cardType -> new Card(cardType, cardNumber))
            ).collect(Collectors.toCollection(Stack::new));

        Collections.shuffle(cards);
        return cards;
    }
}
