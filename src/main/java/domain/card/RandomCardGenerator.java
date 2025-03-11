package domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class RandomCardGenerator implements CardGenerator {
    List<Card> cardTypes;

    public RandomCardGenerator() {
        cardTypes = generateRandomCardDeck();
    }

    private List<Card> generateRandomCardDeck() {
        List<Card> cards = new ArrayList<>(Arrays.stream(CardType.values())
                .flatMap(cardType -> Arrays.stream(CardScore.values())
                        .map(cardScore -> new Card(cardType, cardScore)))
                .toList());
        Collections.shuffle(cards);
        return cards;
    }

    @Override
    public Card peekRandomCard() {
        try {
            return cardTypes.removeFirst();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("[ERROR] 카드를 모두 소진하였습니다.");
        }
    }
}
