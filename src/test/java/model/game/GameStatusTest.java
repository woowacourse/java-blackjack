package model.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.card.Rank;
import model.card.Suit;
import org.junit.jupiter.api.Test;

public class GameStatusTest {
    @Test
    void 카드가_2장_미만이면_추가_배분이_필요하다() {
        // given
        GameStatus needDealout = GameStatus.NEED_DEALOUT;
        Cards cards = Cards.from(List.of(Card.of(Suit.SPADE, Rank.ACE)));

        // when and then
        assertThat(needDealout.getNextStatus(cards)).isEqualTo(GameStatus.NEED_DEALOUT);
    }

    @Test
    void 처음_받은_카드_2장의_합이_21점이면_블랙잭이다() {
        GameStatus needDealout = GameStatus.NEED_DEALOUT;
        Cards cards = Cards.from(List.of(
                Card.of(Suit.SPADE, Rank.ACE),
                Card.of(Suit.SPADE, Rank.KING)
        ));

        // when and then
        assertThat(needDealout.getNextStatus(cards)).isEqualTo(GameStatus.BLACKJACK);
    }

    @Test
    void 처음_받은_카드_2장의_합이_21점이_아니면_카드를_더_받을_수_있다() {
        GameStatus needDealout = GameStatus.NEED_DEALOUT;
        Cards cards = Cards.from(List.of(
                Card.of(Suit.SPADE, Rank.ACE),
                Card.of(Suit.SPADE, Rank.NINE)
        ));

        // when and then
        assertThat(needDealout.getNextStatus(cards)).isEqualTo(GameStatus.CAN_HIT);
    }

    @Test
    void 카드의_합이_21점을_초과하면_버스트이다() {
        GameStatus needDealout = GameStatus.NEED_DEALOUT;
        Cards cards = Cards.from(List.of(
                Card.of(Suit.SPADE, Rank.KING),
                Card.of(Suit.SPADE, Rank.QUEEN),
                Card.of(Suit.SPADE, Rank.TWO)
        ));

        // when and then
        assertThat(needDealout.getNextStatus(cards)).isEqualTo(GameStatus.BUST);
    }

    @Test
    void 카드가_3장_이상이고_합이_21점이면_카드를_추가로_받을_수_없다() {
        GameStatus needDealout = GameStatus.NEED_DEALOUT;
        Cards cards = Cards.from(List.of(
                Card.of(Suit.SPADE, Rank.KING),
                Card.of(Suit.SPADE, Rank.QUEEN),
                Card.of(Suit.SPADE, Rank.ACE)
        ));

        // when and then
        assertThat(needDealout.getNextStatus(cards)).isEqualTo(GameStatus.STAY_21);
    }

    @Test
    void 카드가_3장_이상이더라도_합이_21점_미만이면_카드를_추가로_받을_수_있다() {
        GameStatus needDealout = GameStatus.NEED_DEALOUT;
        Cards cards = Cards.from(List.of(
                Card.of(Suit.SPADE, Rank.KING),
                Card.of(Suit.SPADE, Rank.NINE),
                Card.of(Suit.SPADE, Rank.ACE)
        ));

        // when and then
        assertThat(needDealout.getNextStatus(cards)).isEqualTo(GameStatus.CAN_HIT);
    }
}
