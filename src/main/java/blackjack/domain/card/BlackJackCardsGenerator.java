package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackCardsGenerator implements CardsGenerator {

    @Override
    public List<Card> generate() {
        List<Card> cards = Arrays.stream(Pattern.values())
                .map(this::createCardsBy)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return cards;
    }

    private List<Card> createCardsBy(Pattern pattern) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(pattern, denomination))
                .collect(Collectors.toList());
    }
}
