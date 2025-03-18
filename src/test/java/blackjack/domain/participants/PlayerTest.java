package blackjack.domain.participants;

import static blackjack.fixture.CardFixture.DIAMOND_ACE;
import static blackjack.fixture.CardFixture.DIAMOND_EIGHT;
import static blackjack.fixture.CardFixture.DIAMOND_TWO;
import static blackjack.fixture.CardFixture.HEART_THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 카드_합이_21이_넘는_경우_카드를_더_받지_못한다() {
        //given
        Cards pobiCards = new Cards(DIAMOND_ACE, DIAMOND_ACE, DIAMOND_EIGHT, DIAMOND_EIGHT, DIAMOND_ACE,
                DIAMOND_ACE, DIAMOND_TWO);
        Player player = new Player("pobi", pobiCards, 10000);

        Card card = new Card(Suit.HEART, Rank.TWO);

        //when & then
        assertThatThrownBy(() -> player.additionalTake(card))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
    }

    @Test
    void 플레이어는_추가로_카드를_받을_수_있다() {
        //given
        Cards pobiCards = new Cards(DIAMOND_ACE, DIAMOND_TWO);
        Player player = new Player("pobi", pobiCards, 10000);

        Card card = new Card(Suit.HEART, Rank.THREE);

        //when
        player.additionalTake(card);

        //then
        assertThat(player.getCards()).isEqualTo(new Cards(DIAMOND_ACE, DIAMOND_TWO, HEART_THREE));
    }
}
