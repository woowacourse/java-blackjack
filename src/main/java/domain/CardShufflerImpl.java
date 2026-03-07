package domain;

import infra.CardShuffler;

public class CardShufflerImpl implements CardShuffler {
    @Override
    public int shuffleCardDeck(int deckSize) {
        return (int) (Math.random() * deckSize);
    }
}
