package domain.gameplaying;

import domain.gameplaying.strategy.InfiniteRandomDrawStrategy;
import java.util.List;
import java.util.stream.Stream;

public class TestFixtures {

    static Stream<Hand> emptyHands() {
        return Stream.of(
                Hand.using(new InfiniteRandomDrawStrategy()),
                Hand.using(() -> new Card(CardRank.ACE, CardMark.SPADE)),
                Hand.using(() -> new Card(CardRank.ACE, CardMark.SPADE)),
                Hand.using(() -> new Card(CardRank.QUEEN, CardMark.HEART))
        );
    }

    static Stream<Hand> bustedHands() {
        return Stream.of(
                new Hand(new InfiniteRandomDrawStrategy(),
                        List.of(new Card(CardRank.QUEEN, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE))),

                new Hand(new InfiniteRandomDrawStrategy(),
                        List.of(new Card(CardRank.ACE, CardMark.SPADE),
                                new Card(CardRank.ACE, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE)))
        );
    }
}
