package blackjack.fixture;

import static blackjack.domain.random.CardRandomGenerator.CARDS;

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

    public static Participants provideParticipants() {
        return new Participants(new Dealer(new ArrayList<>()), providePlayers());
    }

    public static List<Card> provideCards(final int count) {
        return CARDS.subList(0, count);
    }

    public static Players providePlayers() {
        return new Players(List.of(new Player("엠제이", new ArrayList<>()), new Player("밍트", new ArrayList<>())));
    }

    public static List<Player> providePlayersWithCards(final List<Card> cards1, final List<Card> cards2) {
        return List.of(new Player("엠제이", cards1), new Player("밍트", cards2));
    }
}
