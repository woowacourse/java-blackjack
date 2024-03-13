package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import fixture.CardFixture;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("플레이어는 21을 넘지 않을 경우 히트가 가능하다.")
    @Test
    void testCanHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();

        Player player = new Player(hand, new PlayerName("pobi"));

        // when
        boolean actual = player.canHit();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어는 21을 넘을 경우 히트가 불가능하다.")
    @Test
    void testCanNotHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(CardFixture.createAHeart());

        Player player = new Player(hand, new PlayerName("pobi"));

        // when
        boolean actual = player.canHit();

        // then
        assertThat(actual).isFalse();
    }
}
