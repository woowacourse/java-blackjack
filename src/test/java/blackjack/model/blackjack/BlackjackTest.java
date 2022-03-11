package blackjack.model.blackjack;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.DIAMOND;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
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
        Blackjack blackjack = new Blackjack(new CardDispenserStub(ACE, JACK, TWO, THREE), "pobi");
        Records records = blackjack.records();

        Record dealerRecord = records.recordByName("딜러");
        assertThat(dealerRecord.countBy(Result.WIN)).isEqualTo(1);
        assertThat(dealerRecord.countBy(Result.DRAW)).isEqualTo(0);
        assertThat(dealerRecord.countBy(Result.LOSS)).isEqualTo(0);

        Record playerRecord = records.recordByName("pobi");
        assertThat(playerRecord.countBy(Result.WIN)).isEqualTo(0);
        assertThat(playerRecord.countBy(Result.DRAW)).isEqualTo(0);
        assertThat(playerRecord.countBy(Result.LOSS)).isEqualTo(1);
    }

    @Test
    void blackjackWithTwoPlayer() {
        Blackjack blackjack = new Blackjack(new CardDispenserStub(JACK, QUEEN, TWO, THREE, ACE, JACK), "pobi", "crong");
        Records records = blackjack.records();

        Record dealerRecord = records.recordByName("딜러");
        assertThat(dealerRecord.countBy(Result.WIN)).isEqualTo(1);
        assertThat(dealerRecord.countBy(Result.DRAW)).isEqualTo(0);
        assertThat(dealerRecord.countBy(Result.LOSS)).isEqualTo(1);

        Record pobiRecord = records.recordByName("pobi");
        assertThat(pobiRecord.countBy(Result.WIN)).isEqualTo(0);
        assertThat(pobiRecord.countBy(Result.DRAW)).isEqualTo(0);
        assertThat(pobiRecord.countBy(Result.LOSS)).isEqualTo(1);

        Record crongRecord = records.recordByName("crong");
        assertThat(crongRecord.countBy(Result.WIN)).isEqualTo(1);
        assertThat(crongRecord.countBy(Result.DRAW)).isEqualTo(0);
        assertThat(crongRecord.countBy(Result.LOSS)).isEqualTo(0);
    }

}
