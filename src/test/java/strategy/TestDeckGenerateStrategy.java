package strategy;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.TrumpCard;
import domain.strategy.DeckGenerateStrategy;
import domain.strategy.DrawStrategy;
import java.util.ArrayDeque;
import java.util.List;

public class TestDeckGenerateStrategy implements DeckGenerateStrategy {

    private final List<TrumpCard> testTrumpCards;

    public TestDeckGenerateStrategy(List<TrumpCard> trumpCards) {
        this.testTrumpCards = trumpCards;
    }

    @Override
    public BlackjackDeck generateDeck(DrawStrategy drawStrategy) {
        ArrayDeque<TrumpCard> generatedTrumpCards = new ArrayDeque<>(testTrumpCards);
        return new BlackjackDeck(generatedTrumpCards, drawStrategy);
    }
}
