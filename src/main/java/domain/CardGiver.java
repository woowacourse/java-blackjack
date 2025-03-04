package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CardGiver {
    private static final int DEFAULT_CARD_GIVE_COUNT = 2;

    private final RandomGenerator<Card> randomGenerator;
    private final GivenCards givenCards;

    public CardGiver(RandomGenerator<Card> randomGenerator, GivenCards givenCards) {
        this.randomGenerator = randomGenerator;
        this.givenCards = givenCards;
    }

    public Cards giveDefault() {
        List<Card> cards = new ArrayList<>();
        while (cards.size() < DEFAULT_CARD_GIVE_COUNT) {
            Card randomCard = randomGenerator.generate();
            boolean isUnique = givenCards.addUnique(randomCard);
            // TODO : depth 2->1 줄이기
            if(isUnique) {
                cards.add(randomCard);
            }
        }
        return new Cards(cards);
    }
}
