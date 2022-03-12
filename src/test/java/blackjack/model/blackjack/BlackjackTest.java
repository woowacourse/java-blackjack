package blackjack.model.blackjack;

import static blackjack.model.player.Name.dealerName;
import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.DIAMOND;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.cards.Score;
import blackjack.model.player.Name;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackjackTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card FOUR = new Card(Rank.FOUR, HEART);
    private static final Card FIVE = new Card(Rank.FIVE, CLOVER);
    private static final Card SIX = new Card(Rank.SIX, SPADE);
    private static final Card SEVEN = new Card(Rank.SEVEN, CLOVER);
    private static final Card EIGHT = new Card(Rank.EIGHT, DIAMOND);
    private static final Card NINE = new Card(Rank.NINE, DIAMOND);
    private static final Card TEN = new Card(Rank.TEN, DIAMOND);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Card KING = new Card(Rank.KING, CLOVER);

    static class CardDispenserStub implements CardDispenser {

        private Card[] cards;
        private int index = 0;

        public CardDispenserStub(Card... cards) {
            this.cards = cards;
        }

        @Override
        public Card issue() {
            if (cards.length == index) {
                throw new IllegalArgumentException("카드 부족!");
            }
            return cards[index++];
        }
    }

    @Test
    void blackjackWithOnePlayer() {
        Name pobi = new Name("pobi");
        Blackjack blackjack = new Blackjack(new CardDispenserStub(ACE, JACK, TWO, THREE), pobi);

        Records records = blackjack.records();

        assertThat(records.resultByName(pobi)).isEqualTo(Result.LOSS);
    }

    @Test
    void blackjackWithTwoPlayer() {
        Name pobi = new Name("pobi");
        Name crong = new Name("crong");
        Blackjack blackjack = new Blackjack(
            new CardDispenserStub(JACK, QUEEN, TWO, THREE, ACE, JACK), pobi, crong);

        Records records = blackjack.records();

        assertThat(records.resultByName(pobi)).isEqualTo(Result.LOSS);
        assertThat(records.resultByName(crong)).isEqualTo(Result.WIN);
    }

    @Test
    void blackjackWithSameNamePlayers() {
        Name pobi1 = new Name("pobi");
        Name pobi2 = new Name("pobi");
        Blackjack blackjack = new Blackjack(
            new CardDispenserStub(JACK, QUEEN, TWO, THREE, ACE, JACK), pobi1, pobi2);

        Records records = blackjack.records();

        assertThat(records.resultByName(pobi1)).isEqualTo(Result.LOSS);
        assertThat(records.resultByName(pobi2)).isEqualTo(Result.WIN);
    }

    @Test
    void blackjackWithDealerTakeCard() {
        Name pobi = new Name("pobi");
        Blackjack blackjack = new Blackjack(new CardDispenserStub(TWO, THREE, JACK, FOUR, TEN),
            pobi);
        blackjack.takeCardByName(dealerName());

        Records records = blackjack.records();

        assertThat(records.resultByName(pobi)).isEqualTo(Result.LOSS);
    }

    @Test
    void blackjackWithPlayerTakeCard() {
        Name pobi = new Name("pobi");
        Blackjack blackjack = new Blackjack(new CardDispenserStub(JACK, FOUR, THREE, TWO, TEN),
            pobi);
        blackjack.takeCardByName(pobi);

        Records records = blackjack.records();

        assertThat(records.resultByName(pobi)).isEqualTo(Result.WIN);
    }

    @ParameterizedTest
    @MethodSource("provideCardDispenserForDealer")
    void dealerIsHittable(CardDispenser dispenser, boolean expect) {
        Blackjack blackjack = new Blackjack(dispenser);
        assertThat(blackjack.isHittableByName(dealerName())).isEqualTo(expect);
    }

    private static Stream<Arguments> provideCardDispenserForDealer() {
        return Stream.of(
            Arguments.of(new CardDispenserStub(JACK, SEVEN), false),
            Arguments.of(new CardDispenserStub(JACK, SIX), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCardDispenserForPlayer")
    void playerIsHittable(CardDispenser cardDispenser, boolean expect) {
        Name pobi = new Name("pobi");
        Blackjack blackjack = new Blackjack(cardDispenser, pobi);
        assertThat(blackjack.isHittableByName(pobi)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideCardDispenserForPlayer() {
        return Stream.of(
            Arguments.of(new CardDispenserStub(ACE, TWO, JACK, JACK), true),
            Arguments.of(new CardDispenserStub(ACE, TWO, JACK, ACE), false)
        );
    }

    @Test
    void dealerTakenCards() {
        Blackjack blackjack = new Blackjack(new CardDispenserStub(ACE, JACK));
        assertThat(blackjack.ownCardsByName(dealerName())).hasSize(2);
        assertThat(blackjack.ownCardsByName(dealerName())).contains(ACE, JACK);
    }

    @Test
    void takeCardAndDealerTakenCards() {
        Blackjack blackjack = new Blackjack(new CardDispenserStub(ACE, FIVE, QUEEN));
        blackjack.takeCardByName(dealerName());
        assertThat(blackjack.ownCardsByName(dealerName())).hasSize(3);
        assertThat(blackjack.ownCardsByName(dealerName())).contains(ACE, FIVE, QUEEN);
    }

    @Test
    void dealerTakeCardFail() {
        Blackjack blackjack = new Blackjack(new CardDispenserStub(ACE, SIX, QUEEN));
        assertThatThrownBy(() -> blackjack.takeCardByName(dealerName()))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void dealerOpenCards() {
        Blackjack blackjack = new Blackjack(new CardDispenserStub(ACE, SIX));
        assertThat(blackjack.openedCardsByName(dealerName())).hasSize(1);
        assertThat(blackjack.openedCardsByName(dealerName())).contains(ACE);
    }

    @Test
    void playerOpenCards() {
        Name name = new Name("pobi");
        Blackjack blackjack = new Blackjack(new CardDispenserStub(ACE, SIX, ACE, SEVEN), name);
        assertThat(blackjack.openedCardsByName(name)).hasSize(2);
        assertThat(blackjack.openedCardsByName(name)).contains(ACE, SEVEN);
    }

    @Test
    void score() {
        Blackjack blackjack = new Blackjack(new CardDispenserStub(JACK, TWO, NINE));
        blackjack.takeCardByName(dealerName());
        assertThat(blackjack.scoreByName(dealerName())).isEqualTo(new Score(21));
    }

    @Test
    void invalidName() {
        Name name = new Name("pobi");
        Blackjack blackjack = new Blackjack(new CardDispenserStub(TWO, THREE, FOUR, FIVE, SIX));
        assertThatThrownBy(() -> blackjack.isHittableByName(name))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackjack.ownCardsByName(name))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackjack.openedCardsByName(name))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> blackjack.scoreByName(name))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
