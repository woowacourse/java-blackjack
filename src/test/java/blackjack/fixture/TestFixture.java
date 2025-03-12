package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.shuffle.CardGenerator;
import java.util.ArrayList;
import java.util.List;

public class TestFixture {

    public static class TestCardGeneratorGenerator implements CardGenerator {

        private int callingCount = 0;

        @Override
        public Card pickCard() {
            callingCount += 1;
            if (callingCount == 0 || callingCount == 1) {
                return new Card(Shape.DIAMOND, Denomination.TWO);
            }
            return new Card(Shape.DIAMOND, Denomination.THREE);
        }
    }

    public static Participants provideParticipants() {
        return new Participants(new Dealer(provideEmptyCards()), providePlayers());
    }

    public static Hand provideCards(final int count) {
        return new Hand(CardGenerator.DECK.subList(0, count));
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
        return new Hand(List.of(new Card(Shape.SPADE, Denomination.K),
                new Card(Shape.SPADE, Denomination.Q), new Card(Shape.SPADE, Denomination.J)));
    }

    public static Hand provideUnder21Cards() {
        return new Hand(List.of(new Card(Shape.SPADE, Denomination.TWO),
                new Card(Shape.SPADE, Denomination.THREE)));
    }

    public static Hand provideUnder16Cards() {
        return new Hand(List.of(new Card(Shape.SPADE, Denomination.TWO),
                new Card(Shape.SPADE, Denomination.FOUR)));
    }

    public static Hand provideOver16Cards() {
        return new Hand(List.of(new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.HEART, Denomination.TEN)));
    }

    public static Hand provideSmallerAceCards() {
        return new Hand(List.of(new Card(Shape.SPADE, Denomination.EIGHT),
                new Card(Shape.HEART, Denomination.NINE),
                new Card(Shape.HEART, Denomination.A)
        ));
    }

    public static Hand provideBiggerAceCards() {
        return new Hand(List.of(new Card(Shape.SPADE, Denomination.K),
                new Card(Shape.HEART, Denomination.A)
        ));
    }

    public static Hand provideBiggerAndSmallerAceCards() {
        return new Hand(List.of(new Card(Shape.SPADE, Denomination.A),
                new Card(Shape.HEART, Denomination.A),
                new Card(Shape.HEART, Denomination.TWO),
                new Card(Shape.HEART, Denomination.THREE)
        ));
    }

    public static Hand provideEmptyCards() {
        return new Hand(new ArrayList<>());
    }
}
