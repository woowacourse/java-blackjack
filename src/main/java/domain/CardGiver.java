package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CardGiver {

    private final RandomGenerator<Card> randomGenerator;
    private final GivenCards givenCards;

    public CardGiver(RandomGenerator<Card> randomGenerator, GivenCards givenCards) {
        this.randomGenerator = randomGenerator;
        this.givenCards = givenCards;
    }

    public Cards giveDefault() {
        List<Card> cards = new ArrayList<>();
        IntStream.range(0, 2).forEach(
                index -> {
                    Card randomCard = randomGenerator.generate();
                    givenCards.add(randomCard);
                    cards.add(randomCard);
                }
        );
        return new Cards(cards);
    }
}
