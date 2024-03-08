package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어가 보유한 카드 숫자의 합이 21이하이면 true를 리턴한다.")
    @Test
    void checkIsEqualOrLessThanMaxValue() {
        final Card card1 = new Card(Number.ACE, Suit.DIAMOND);
        final Card card2 = new Card(Number.TEN, Suit.CLOVER);
        final Player player = Player.from(new Name("pobi"));
        player.draw(card1);
        player.draw(card2);

        boolean actual = player.canDraw();

        assertThat(actual).isEqualTo(true);
    }

    @DisplayName("플레이어가 보유한 카드 숫자의 합이 21초과이면 false를 리턴한다.")
    @Test
    void checkIsBiggerThanMaxValue() {
        final Card card1 = new Card(Number.TEN, Suit.DIAMOND);
        final Card card2 = new Card(Number.TEN, Suit.CLOVER);
        final Card card3 = new Card(Number.TWO, Suit.CLOVER);
        final Player player = Player.from(new Name("pobi"));
        player.draw(card1);
        player.draw(card2);
        player.draw(card3);

        boolean actual = player.canDraw();

        assertThat(actual).isEqualTo(false);
    }
}
