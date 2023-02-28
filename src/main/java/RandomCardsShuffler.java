import java.util.Collections;
import java.util.List;

public class RandomCardsShuffler implements CardsShuffler {
    @Override
    public List<Card> shuffleCards(final List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
