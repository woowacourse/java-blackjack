package domain.gameplaying;

import domain.gameplaying.strategy.InfiniteDeck;
import java.util.List;
import java.util.stream.Stream;

public class TestFixtures {

    static Stream<Hand> emptyHands() {
        return Stream.of(
                Hand.with(new InfiniteDeck()),
                Hand.with(() -> new Card(CardRank.ACE, CardMark.SPADE)),
                Hand.with(() -> new Card(CardRank.ACE, CardMark.SPADE)),
                Hand.with(() -> new Card(CardRank.QUEEN, CardMark.HEART))
        );
    }

    static Stream<Hand> bustedHands() {
        return Stream.of(
                new Hand(new InfiniteDeck(),
                        List.of(new Card(CardRank.QUEEN, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE))),

                new Hand(new InfiniteDeck(),
                        List.of(new Card(CardRank.ACE, CardMark.SPADE),
                                new Card(CardRank.ACE, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE)))
        );
    }
}
