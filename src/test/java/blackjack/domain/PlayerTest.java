package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.gamers.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어가 보유한 카드 숫자의 합이 21이하이면 true를 리턴한다.")
    @Test
    void checkIsEqualOrLessThanMaxValue() {
        final Card card1 = Card.of(Rank.ACE, Suit.DIAMOND);
        final Card card2 = Card.of(Rank.TEN, Suit.CLOVER);
        final Player player = Player.from("pobi");
        player.draw(card1);
        player.draw(card2);

        boolean actual = player.canDraw();

        assertThat(actual).isEqualTo(true);
    }

    @DisplayName("플레이어가 보유한 카드 숫자의 합이 21초과이면 false를 리턴한다.")
    @Test
    void checkIsBiggerThanMaxValue() {
        final Card card1 = Card.of(Rank.TEN, Suit.DIAMOND);
        final Card card2 = Card.of(Rank.TEN, Suit.CLOVER);
        final Card card3 = Card.of(Rank.TWO, Suit.CLOVER);
        final Player player = Player.from("pobi");
        player.draw(card1);
        player.draw(card2);
        player.draw(card3);

        boolean actual = player.canDraw();

        assertThat(actual).isEqualTo(false);
    }
}
