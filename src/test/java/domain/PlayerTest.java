package domain;

import static domain.card.Rank.ACE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.NINE;
import static domain.card.Rank.TEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardHand;
import domain.participant.Player;
import fixture.CardFixture;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("자기 점수를 계산한다.")
    void testCalculateScore() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TWO, HEART), CardFixture.of(THREE, DIAMOND), CardFixture.of(FOUR, CLOVER)));
        Player player = new Player("pobi", cardHand);
        // when
        int score = player.calculateScore();
        // then
        assertThat(score).isEqualTo(9);
    }

    @Test
    @DisplayName("자기가 버스트인지 판단한다.")
    void testIsBust() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(NINE, DIAMOND), CardFixture.of(TWO, CLOVER)));
        Player player = new Player("pobi", cardHand);
        // when & then
        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("자기가 블랙잭인지 판단한다.")
    void testIsBlackJack() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(ACE, DIAMOND)));
        Player player = new Player("pobi", cardHand);
        // when & then
        assertThat(player.isBlackJack()).isTrue();
    }
}
