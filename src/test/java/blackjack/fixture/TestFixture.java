package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardScore;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Shape;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.card.generator.ShuffleDeckGenerator;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
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
        return new Players(List.of(new Player("엠제이", provideEmptyCards()), new Player("밍트", provideEmptyCards())));
    }

    public static List<Player> provideTwoPlayersWithCards(final Hand hand1, final Hand hand2) {
        return List.of(new Player("엠제이", hand1), new Player("밍트", hand2));
    }

    public static List<Player> provideThreePlayersWithCards(final Hand hand1, final Hand hand2,
                                                            final Hand hand3) {
        return List.of(new Player("엠제이", hand1), new Player("밍트", hand2), new Player("포비", hand3));
    }

    public static Hand provideOver21Cards() {
        return new Hand(List.of(new Card(Shape.SPADE, CardScore.K),
                new Card(Shape.SPADE, CardScore.Q), new Card(Shape.SPADE, CardScore.J)));
    }

    public static Hand provideUnder21Cards() {
        return new Hand(List.of(new Card(Shape.SPADE, CardScore.TWO),
                new Card(Shape.SPADE, CardScore.THREE)));
    }

    public static Hand provideUnder16Cards() {
        return new Hand(List.of(new Card(Shape.SPADE, CardScore.TWO),
                new Card(Shape.SPADE, CardScore.FOUR)));
    }

    public static Hand provideOver16Cards() {
        return new Hand(List.of(new Card(Shape.SPADE, CardScore.TEN),
                new Card(Shape.HEART, CardScore.TEN)));
    }

    public static Hand provideSmallerAceCards() {
        return new Hand(List.of(new Card(Shape.SPADE, CardScore.EIGHT),
                new Card(Shape.HEART, CardScore.NINE),
                new Card(Shape.HEART, CardScore.A)
        ));
    }

    public static Hand provideBiggerAceCards() {
        return new Hand(List.of(new Card(Shape.SPADE, CardScore.K),
                new Card(Shape.HEART, CardScore.A)
        ));
    }

    public static Hand provideBiggerAndSmallerAceCards() {
        return new Hand(List.of(new Card(Shape.SPADE, CardScore.A),
                new Card(Shape.HEART, CardScore.A),
                new Card(Shape.HEART, CardScore.TWO),
                new Card(Shape.HEART, CardScore.THREE)
        ));
    }

    public static Hand provideEmptyCards() {
        return new Hand(new ArrayList<>());
    }
}
