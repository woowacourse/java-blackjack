package infra;

import domain.card.CardShuffler;

public class FakeCardShuffler implements CardShuffler {
    @Override
    public int getRandomCardIndex(int deckSize) {
        return 0;
    }
}
