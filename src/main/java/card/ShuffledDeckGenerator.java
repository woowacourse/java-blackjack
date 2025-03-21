package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffledDeckGenerator implements DeckGenerator {

    @Override
    public List<Card> generateDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (NormalRank rank : NormalRank.values()) {
                deck.add(new Card(suit, rank));
            }
            deck.add(new Card(suit, AceRank.SOFT_ACE));
        }
        Collections.shuffle(deck);
        return deck;
    }
}
