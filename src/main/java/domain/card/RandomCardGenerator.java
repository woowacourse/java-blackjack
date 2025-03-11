package domain.card;

import java.util.*;

public class RandomCardGenerator implements CardGenerator {
    List<Card> cardTypes;

    public RandomCardGenerator() {
        cardTypes = generateRandomCardDeck();
    }

    private List<Card> generateRandomCardDeck() {
        List<Card> randomCardDeck = new ArrayList<>();
        Arrays.stream(CardType.values()).forEach(card -> selectCardScore(randomCardDeck, card));
        Collections.shuffle(randomCardDeck);
        return randomCardDeck;
    }

    private void selectCardScore(List<? super Card> randomCardDeck, CardType type) {
        Arrays.stream(CardScore.values()).forEach(cardScore -> randomCardDeck.add(new Card(type, cardScore)));
    }

    @Override
    public Card peekRandomCard() {
        try{
            return cardTypes.removeFirst();
        } catch (NoSuchElementException e){
            throw new IllegalStateException("[ERROR] 카드를 모두 소진하였습니다.");
        }
    }
}
