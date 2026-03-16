package domain.shuffle;

import domain.Cards;

public class RandomCardStrategy implements CardShuffleStrategy {

    @Override
    public void shuffle(Cards cards) {
        cards.shuffle();
    }
}
