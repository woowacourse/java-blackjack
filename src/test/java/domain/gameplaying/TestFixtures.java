package domain.gameplaying;

import domain.gameplaying.strategy.RandomStrategy;
import java.util.List;
import java.util.stream.Stream;

public class TestFixtures {

    static Stream<Hand> emptyHands() {
        return Stream.of(
                Hand.based(new RandomStrategy()),
                Hand.based(() -> new Card(CardRank.ACE, CardMark.SPADE)),
                Hand.based(() -> new Card(CardRank.ACE, CardMark.SPADE)),
                Hand.based(() -> new Card(CardRank.QUEEN, CardMark.HEART))
        );
    }

    static Stream<Hand> bustedHands() {
        return Stream.of(
                new Hand(new RandomStrategy(),
                        List.of(new Card(CardRank.QUEEN, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE))),

                new Hand(new RandomStrategy(),
                        List.of(new Card(CardRank.ACE, CardMark.SPADE),
                                new Card(CardRank.ACE, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE),
                                new Card(CardRank.QUEEN, CardMark.SPADE)))
        );
    }
}
