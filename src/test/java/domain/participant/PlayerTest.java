package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private static final int ONE_HUNDRED_THOUSAND = 100_000;

    @Test
    @DisplayName("플레이어는 bust 아니면 draw 가능하다.")
    void bust_아니면_draw_가능() {
        // given
        final Player player = new Player(new Name("재키"), ONE_HUNDRED_THOUSAND);
        player.draw(new Card(CardSuit.SPADE, CardRank.KING));
        player.draw(new Card(CardSuit.SPADE, CardRank.EIGHT));

        // when
        final boolean result = player.isDrawable();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어가 bust라면 더 draw할 수 없다.")
    void bust면_draw_불가() {
        // given
        final Player player = new Player(new Name("재키"), ONE_HUNDRED_THOUSAND);
        player.draw(new Card(CardSuit.SPADE, CardRank.KING));
        player.draw(new Card(CardSuit.SPADE, CardRank.QUEEN));
        player.draw(new Card(CardSuit.SPADE, CardRank.JACK));

        // when
        final boolean result = player.isDrawable();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("플레이어가 블랙잭이면 draw할 수 없다.")
    void 블랙잭이면_draw_불가() {
        // given
        final Player player = new Player(new Name("재키"), ONE_HUNDRED_THOUSAND);
        player.draw(new Card(CardSuit.SPADE, CardRank.ACE));
        player.draw(new Card(CardSuit.HEART, CardRank.KING));

        // when
        final boolean result = player.isDrawable();

        // then
        assertThat(result).isFalse();
    }
}
