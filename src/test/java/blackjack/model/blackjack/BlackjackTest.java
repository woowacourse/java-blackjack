package blackjack.model.blackjack;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.DIAMOND;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.player.Dealer;
import blackjack.model.player.Name;
import org.junit.jupiter.api.Test;

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

        Record dealerRecord = records.recordByName(Dealer.dealerName());
        assertRecord(dealerRecord, 1, 0, 0);

        Record pobiRecord = records.recordByName(pobi);
        assertRecord(pobiRecord, 0, 0, 1);
    }

    @Test
    void blackjackWithTwoPlayer() {
        Name pobi = new Name("pobi");
        Name crong = new Name("crong");
        Blackjack blackjack = new Blackjack(
            new CardDispenserStub(JACK, QUEEN, TWO, THREE, ACE, JACK), pobi, crong);
        Records records = blackjack.records();

        Record dealerRecord = records.recordByName(Dealer.dealerName());
        assertRecord(dealerRecord, 1, 0, 1);

        Record pobiRecord = records.recordByName(pobi);
        assertRecord(pobiRecord, 0, 0, 1);

        Record crongRecord = records.recordByName(crong);
        assertRecord(crongRecord, 1, 0, 0);
    }

    @Test
    void blackjackWithSameNamePlayers() {
        Name pobi1 = new Name("pobi");
        Name pobi2 = new Name("pobi");

        Blackjack blackjack = new Blackjack(
            new CardDispenserStub(JACK, QUEEN, TWO, THREE, ACE, JACK), pobi1, pobi2);
        Records records = blackjack.records();

        Record dealerRecord = records.recordByName(Dealer.dealerName());
        assertRecord(dealerRecord, 1, 0, 1);

        Record pobiRecord = records.recordByName(pobi1);
        assertRecord(pobiRecord, 0, 0, 1);

        Record crongRecord = records.recordByName(pobi2);
        assertRecord(crongRecord, 1, 0, 0);
    }

    @Test
    void blackjackWithDealerTakeCard() {
        Name pobi = new Name("pobi");
        Blackjack blackjack = new Blackjack(new CardDispenserStub(TWO, THREE, JACK, FOUR, TEN),
            pobi);
        blackjack.dealerTakeCard();
        Records records = blackjack.records();

        Record dealerRecord = records.recordByName(Dealer.dealerName());
        assertRecord(dealerRecord, 1, 0, 0);

        Record playerRecord = records.recordByName(pobi);
        assertRecord(playerRecord, 0, 0, 1);
    }

    @Test
    void blackjackWithPlayerTakeCard() {
        Name pobi = new Name("pobi");
        Blackjack blackjack = new Blackjack(new CardDispenserStub(JACK, FOUR, THREE, TWO, TEN),
            pobi);
        blackjack.playerTakeCard(pobi);
        Records records = blackjack.records();

        Record dealerRecord = records.recordByName(Dealer.dealerName());
        assertRecord(dealerRecord, 0, 0, 1);

        Record playerRecord = records.recordByName(pobi);
        assertRecord(playerRecord, 1, 0, 0);
    }

    private static void assertRecord(Record record, int win, int draw, int loss) {
        assertThat(record.countBy(Result.WIN))
            .withFailMessage(failMessage(Result.WIN, win, record.countBy(Result.WIN)))
            .isEqualTo(win);
        assertThat(record.countBy(Result.LOSS))
            .withFailMessage(failMessage(Result.LOSS, loss, record.countBy(Result.LOSS)))
            .isEqualTo(loss);
        assertThat(record.countBy(Result.DRAW))
            .withFailMessage(failMessage(Result.DRAW, draw, record.countBy(Result.DRAW)))
            .isEqualTo(draw);

    }

    private static String failMessage(Result result, int actual, int expect) {
        return String.format("%s : Actual[%d] Expect[%d]", result, actual, expect);
    }

}
