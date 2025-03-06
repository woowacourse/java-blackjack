package blackjack.fixture;

import static blackjack.domain.random.CardRandomGenerator.CARDS;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardGenerator;
import java.util.ArrayList;
import java.util.List;

public class TestFixture {

    public static class TestCardGeneratorGenerator implements CardGenerator {
        private int callingCount = 0;

        @Override
        public Card pickRandomCard() {
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

    public static Cards provideCards(final int count) {
        return new Cards(CARDS.subList(0, count));
    }

    public static Players providePlayers() {
        return new Players(List.of(new Player("엠제이", provideEmptyCards()), new Player("밍트", provideEmptyCards())));
    }

    public static List<Player> providePlayersWithCards(final Cards... cards) {
        String name = "엠제이";
        final List<Player> players = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            players.add(new Player(name + i, cards[i]));
        }
        return players;
    }

    public static Cards provideOver21Cards() {
        return new Cards(List.of(new Card(Shape.SPADE, Denomination.K),
                new Card(Shape.SPADE, Denomination.Q), new Card(Shape.SPADE, Denomination.J)));
    }

    public static Cards provideUnder21Cards() {
        return new Cards(List.of(new Card(Shape.SPADE, Denomination.TWO),
                new Card(Shape.SPADE, Denomination.THREE)));
    }

    public static Cards provideUnder16Cards() {
        return new Cards(List.of(new Card(Shape.SPADE, Denomination.TWO),
                new Card(Shape.SPADE, Denomination.FOUR)));
    }

    public static Cards provideOver16Cards() {
        return new Cards(List.of(new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.HEART, Denomination.TEN)));
    }

    public static Cards provideSmallerAceCards() {
        return new Cards(List.of(new Card(Shape.SPADE, Denomination.EIGHT),
                new Card(Shape.HEART, Denomination.NINE),
                new Card(Shape.HEART, Denomination.A)
        ));
    }

    public static Cards provideBiggerAceCards() {
        return new Cards(List.of(new Card(Shape.SPADE, Denomination.K),
                new Card(Shape.HEART, Denomination.A)
        ));
    }

    public static Cards provideBiggerAndSmallerAceCards() {
        return new Cards(List.of(new Card(Shape.SPADE, Denomination.A),
                new Card(Shape.HEART, Denomination.A),
                new Card(Shape.HEART, Denomination.TWO),
                new Card(Shape.HEART, Denomination.THREE)
        ));
    }

    public static Cards provideEmptyCards() {
        return new Cards(new ArrayList<>());
    }
}
