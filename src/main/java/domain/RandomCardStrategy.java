package domain;

public class RandomCardStrategy implements CardShuffleStrategy {

    @Override
    public void shuffle(Cards cards) {
        cards.shuffle();
    }
}
