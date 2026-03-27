package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    void 카드의_점수를_반환한다() {
        Card ace = new Card(Suit.DIAMOND, Rank.ACE);
        Card ten = new Card(Suit.DIAMOND, Rank.TEN);
        Card four = new Card(Suit.DIAMOND, Rank.FOUR);

        Assertions.assertEquals(ace.getRankValue(), 1);
        Assertions.assertEquals(ten.getRankValue(), 10);
        Assertions.assertEquals(four.getRankValue(), 4);
    }

    @Test
    void 에이스_여부를_확인한다() {
        Card ace = new Card(Suit.DIAMOND, Rank.ACE);
        Card king = new Card(Suit.DIAMOND, Rank.K);

        assertThat(ace.isAce());
        assertThat(king.isAce());
    }

    @Test
    void 카드가_에이스인_경우_1을_반환한다() {
        Card card = new Card(Suit.DIAMOND, Rank.ACE);

        Assertions.assertEquals(card.getOneIfAce(), 1);
    }

    @Test
    void 카드가_에이스가_아닌_경우_0을_반환한다() {
        Card card = new Card(Suit.DIAMOND, Rank.FOUR);

        Assertions.assertEquals(card.getOneIfAce(), 0);
    }

    @Test
    void 문양과_숫자가_같으면_같은_카드로_간주한다() {
        Card card1 = new Card(Suit.DIAMOND, Rank.ACE);
        Card card2 = new Card(Suit.DIAMOND, Rank.ACE);

        Assertions.assertEquals(card1, card2);
        Assertions.assertEquals(card1.hashCode(), card2.hashCode());
    }
}
