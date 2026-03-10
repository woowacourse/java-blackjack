package infra;

import domain.CardShuffler;

public class CardShufflerImpl implements CardShuffler {
    @Override
    public int getRandomCardIndex(int deckSize) {
        return (int) (Math.random() * deckSize);
    }
}
