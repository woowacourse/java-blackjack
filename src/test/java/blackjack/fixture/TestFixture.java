package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Suit;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.card.generator.ShuffleDeckGenerator;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.Player;
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
                List.of(new Player(provideEmptyCards(), "엠제이", 10_000), new Player(provideEmptyCards(), "밍트", 20_000)));
    }

    public static Player providePlayer(final String nickname, final int bettingAmount) {
        return new Player(provideEmptyCards(), nickname, bettingAmount);
    }

    public static Hand provideUnder16Cards() {
        return new Hand(List.of(new Card(Suit.SPADE, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.FOUR)));
    }

    public static Hand provideBlackjack() {
        return new Hand(List.of(new Card(Suit.SPADE, Denomination.TEN),
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
        return new Hand(List.of(new Card(Suit.CLOB, Denomination.A), new Card(Suit.CLOB, Denomination.SIX)));
    }
}
