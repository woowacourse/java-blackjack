package blackjack.fixture;

import blackjack.blackjack.card.Card;
import blackjack.blackjack.card.Denomination;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.card.Suit;
import blackjack.blackjack.card.generator.DeckGenerator;
import blackjack.blackjack.card.generator.ShuffleDeckGenerator;
import blackjack.blackjack.participant.Player;
import blackjack.blackjack.participant.Players;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

public class TestFixture {

    public static Hand provideCards(final int count) {
        DeckGenerator deckGenerator = new ShuffleDeckGenerator();
        Deque<Card> cards = deckGenerator.makeDeck();
        return new Hand(IntStream.range(0, count)
                .mapToObj(i -> cards.pollFirst())
                .toList());
    }

    public static Players providePlayers() {
        return new Players(
                List.of(new Player(provideEmptyCards(), "엠제이", BigDecimal.valueOf(10_000)),
                        new Player(provideEmptyCards(), "밍트", BigDecimal.valueOf(20_000))));
    }

    public static Hand provideUnder16Cards() {
        return new Hand(List.of(new Card(Suit.SPADE, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.FOUR)));
    }

    public static Hand provideOver16Cards() {
        return new Hand(List.of(new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.K)));
    }

    public static Hand provideBlackjack() {
        return new Hand(List.of(new Card(Suit.SPADE, Denomination.Q),
                new Card(Suit.HEART, Denomination.A)));
    }

    public static Hand provideSmallerAceCards() {
        return new Hand(List.of(new Card(Suit.SPADE, Denomination.EIGHT),
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.HEART, Denomination.A)
        ));
    }

    public static Hand provideBiggerAceCards() {
        return new Hand(List.of(new Card(Suit.SPADE, Denomination.K),
                new Card(Suit.HEART, Denomination.A)
        ));
    }

    public static Hand provideBiggerAndSmallerAceCards() {
        return new Hand(List.of(new Card(Suit.SPADE, Denomination.A),
                new Card(Suit.HEART, Denomination.A),
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.THREE)
        ));
    }

    public static Hand provideEmptyCards() {
        return new Hand(new ArrayList<>());
    }

    public static Hand provide16Cards() {
        return new Hand(List.of(new Card(Suit.CLOB, Denomination.TEN), new Card(Suit.CLOB, Denomination.SIX)));
    }

    public static Hand provideBustCards() {
        return new Hand(List.of(new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.Q),
                new Card(Suit.SPADE, Denomination.J)
        ));
    }
}
