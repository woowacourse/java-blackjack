package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomCardGenerator implements CardGenerator {
    List<Card> cardTypes;

    public RandomCardGenerator(){
        cardTypes = generateRandomCardDeck();
        Collections.shuffle(cardTypes);
    }

    private List<Card> generateRandomCardDeck(){
        List<Card> randomCardDeck = new ArrayList<>();
        Arrays.stream(CardType.values()).forEach(card -> selectCardScore(randomCardDeck,card));
        return randomCardDeck;
    }

    private void selectCardScore(List<? super Card> randomCardDeck, CardType type){
        Arrays.stream(CardScore.values()).forEach(cardScore -> randomCardDeck.add(new Card(type,cardScore)));
    }

    @Override
    public Card peekRandomCard() {
        return cardTypes.removeFirst();
    }
}
