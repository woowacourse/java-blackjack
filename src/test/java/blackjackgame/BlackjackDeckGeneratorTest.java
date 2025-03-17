package blackjackgame;

import static org.assertj.core.api.Assertions.assertThatIterable;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.CardValue;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import domain.strategy.BlackjackDeckGenerateStrategy;
import domain.strategy.BlackjackDrawStrategy;
import domain.strategy.DeckGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class BlackjackDeckGeneratorTest {

    @Test
    void 블랙잭_카드는_52장만_만들어진다() {
        List<Suit> suits = Arrays.stream(Suit.values()).toList();
        Set<TrumpCard> trumpCards = new HashSet<>();
        for (Suit suit : suits) {
            List<CardValue> cardValues = CardValue.cardValues();
            Set<TrumpCard> trumpCardsPerSuit = cardValues.stream().map(cardValue -> new TrumpCard(suit, cardValue))
                    .collect(Collectors.toSet());
            trumpCards.addAll(trumpCardsPerSuit);
        }

        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new BlackjackDeckGenerateStrategy());

        List<TrumpCard> generatedCards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            generatedCards.add(deck.drawCard());
        }
        assertThatIterable(generatedCards).containsExactlyInAnyOrderElementsOf(trumpCards);
    }
}
