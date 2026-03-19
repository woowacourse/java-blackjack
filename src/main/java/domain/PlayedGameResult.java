package domain;

import domain.gameplaying.Card;
import java.util.List;

public record PlayedGameResult(NameAndCards nameAndCards, int scoreSum) {

    private static final int BLACK_JACK_SCORE = 21;
    private static final int INITIAL_CARD_COUNT = 2;

    public static PlayedGameResult from(String name, List<Card> cards, int scoreSum) {
        NameAndCards infos = new NameAndCards(name, cards);
        return new PlayedGameResult(infos, scoreSum);
    }

    public String name() {
        return nameAndCards.name();
    }

    public List<Card> cards() {
        return nameAndCards.cards();
    }

    public int cardCount() {
        return this.cards().size();
    }

    public boolean isBlackJack() {
        return scoreSum == BLACK_JACK_SCORE && this.cardCount() == INITIAL_CARD_COUNT;
    }

    public boolean isBusted() {
        return scoreSum > BLACK_JACK_SCORE;
    }

    private record NameAndCards(String name, List<Card> cards) {
    }
}
