package blackjack.fixture;

import blackjack.domain.card.Card;
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

    public static Participants provideAttendees() {
        return new Participants(new Dealer(new ArrayList<>()), providePlayers());
    }

    public static List<Card> provideTwoCards() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.TWO),
                new Card(Shape.DIAMOND, Denomination.THREE)
        );
    }

    private static Players providePlayers() {
        return new Players(List.of(new Player("엠제이", new ArrayList<>()), new Player("밍트", new ArrayList<>())));
    }
}
