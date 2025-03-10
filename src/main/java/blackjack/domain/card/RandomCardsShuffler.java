package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RandomCardsShuffler implements CardsShuffler {
    @Override
    public void shuffleCards(Stack<Card> cards) {
        List<Card> shuffledCard = new ArrayList<>();
        while (!cards.isEmpty()) {
            shuffledCard.add(cards.pop());
        }
        Collections.shuffle(cards);
        Collections.reverse(shuffledCard);
        cards.addAll(shuffledCard);
    }
}
