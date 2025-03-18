package blackjack.domain.card;

import static blackjack.fixture.CardFixture.DIAMOND_ACE;
import static blackjack.fixture.CardFixture.DIAMOND_EIGHT;
import static blackjack.fixture.CardFixture.DIAMOND_KING;
import static blackjack.fixture.CardFixture.DIAMOND_NINE;
import static blackjack.fixture.CardFixture.DIAMOND_ONE;
import static blackjack.fixture.CardFixture.DIAMOND_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void 카드_합이_21을_초과하지_않는_경우가_존재한다면_21보다_작은_수_중_최대값을_반환한다() {
        //given
        Cards cards = new Cards(
                DIAMOND_ACE,
                DIAMOND_ACE,
                DIAMOND_EIGHT,
                DIAMOND_EIGHT,
                DIAMOND_ACE,
                DIAMOND_ACE,
                DIAMOND_ACE
        );

        //when
        int scoreResult = cards.calculateScore().value();

        //then
        assertThat(scoreResult).isEqualTo(21);
    }

    @Test
    void 카드_합이_21을_초과하는_경우만_있다면_21과_가장_가까운_수를_반환한다() {
        //given
        Cards cards = new Cards(
                DIAMOND_ACE,
                DIAMOND_ACE,
                DIAMOND_EIGHT,
                DIAMOND_EIGHT,
                DIAMOND_ACE,
                DIAMOND_ACE,
                DIAMOND_ACE,
                DIAMOND_ACE
        );

        //when
        int scoreResult = cards.calculateScore().value();

        //then
        assertThat(scoreResult).isEqualTo(22);
    }

    @Test
    void 플레이어는_자신이_가진_카드로_21에_최대한_가깝게_점수를_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                DIAMOND_ACE,
                DIAMOND_EIGHT,
                DIAMOND_ACE
        );

        //when
        int maxScore = cards.calculateScore().value();

        //then
        assertThat(maxScore).isEqualTo(20);
    }

    @Test
    void 플레이어의_카드에_A가_포함되어_있을_때_21과_가장_가까운_점수를_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                DIAMOND_ACE,
                DIAMOND_KING
        );

        //when
        int minScore = cards.calculateScore().value();

        //then
        assertThat(minScore).isEqualTo(21);
    }

    @Test
    void 카드가_블랙잭임을_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                DIAMOND_ACE,
                DIAMOND_KING
        );

        //when & then
        assertThat(cards.isBlackjack()).isTrue();
    }

    @Test
    void 카드가_블랙잭이_아님을_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                DIAMOND_TEN,
                DIAMOND_KING,
                DIAMOND_ONE
        );

        //when & then
        assertThat(cards.isBlackjack()).isFalse();
    }

    @Test
    void 카드_합이_21이_넘으면_더_이상_카드를_받을_수_없다() {
        //given
        Cards cards = new Cards(
                DIAMOND_TEN,
                DIAMOND_KING,
                DIAMOND_NINE
        );

        //when & then
        assertThatThrownBy(() -> cards.additionalTake(
                new Card(Suit.CLUB, Rank.FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
    }
}
