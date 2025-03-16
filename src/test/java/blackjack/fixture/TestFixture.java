package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardScore;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Shape;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.card.generator.ShuffleDeckGenerator;
import blackjack.domain.participant.gamer.Player;
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
        return new Players(
                List.of(new Player(provideEmptyCards(), "엠제이", 10_000), new Player(provideEmptyCards(), "밍트", 20_000)));
    }

    public static Player providePlayer(final String nickname, final int bettingAmount) {
        return new Player(provideEmptyCards(), nickname, bettingAmount);
    }

    public static List<Player> provideTwoPlayersWithCards(final Hand hand1, final Hand hand2) {
        return List.of(new Player(hand1, "엠제이", 10_000), new Player(hand2, "밍트", 20_000));
    }

    public static List<Player> provideThreePlayersWithCards(final Hand hand1, final Hand hand2,
                                                            final Hand hand3) {
        return List.of(new Player(hand1, "엠제이", 10_000), new Player(hand2, "밍트", 20_000),
                new Player(hand3, "포비", 30_000));
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

    public static Hand provideBlackjack() {
        return new Hand(List.of(new Card(Shape.SPADE, CardScore.TEN),
                new Card(Shape.HEART, CardScore.A)));
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
