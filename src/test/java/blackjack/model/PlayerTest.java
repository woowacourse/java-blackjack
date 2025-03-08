package blackjack.model;

import static blackjack.TestFixtures.NO_HIT_STRATEGY;
import static blackjack.TestFixtures.createHitDecisionStrategy;
import static blackjack.model.card.CardFixtures.SPADE_ACE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_SIX_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TWO_CARD;
import static blackjack.model.card.CardFixtures.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.card.CardValue;
import blackjack.model.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("플레이어 테스트")
class PlayerTest {

    @DisplayName("이름을 가진다.")
    @Test
    void createPlayerTest() {
        // given
        Name name = new Name("pobi");

        // when, then
        assertThatCode(() -> new Player(name, NO_HIT_STRATEGY))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 받을 수 있다.")
    @Test
    void receiveHandTest() {
        // given
        Player player = new Player(new Name("pobi"), NO_HIT_STRATEGY);

        // when
        player.receiveHand(SPADE_ACE_CARD);

        // then
        assertThat(player.getHand())
                .contains(SPADE_ACE_CARD);

    }

    @DisplayName("가진 패의 총합을 계산한다.")
    @Test
    void calculateHandTotalTest() {
        // given
        Player player = new Player(new Name("pobi"), NO_HIT_STRATEGY);

        // when
        player.receiveHand(SPADE_TEN_CARD);
        player.receiveHand(SPADE_SIX_CARD);

        // then
        assertThat(player.getTotal())
                .isEqualTo(16);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 이하인 경우 ACE를 11로 간주한다.")
    @Test
    void calculateHandTotalWithAceTest() {
        // given
        Player player = new Player(new Name("pobi"), NO_HIT_STRATEGY);

        // when
        player.receiveHand(SPADE_ACE_CARD);
        player.receiveHand(SPADE_TEN_CARD);

        // then
        assertThat(player.getTotal())
                .isEqualTo(21);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 초과인 경우 ACE를 1로 간주한다.")
    @Test
    void calculateHandTotalWithAceTestOver11() {
        // given
        Player player = new Player(new Name("pobi"), NO_HIT_STRATEGY);

        // when
        player.receiveHand(SPADE_ACE_CARD);
        player.receiveHand(SPADE_TWO_CARD);
        player.receiveHand(SPADE_TEN_CARD);

        // then
        assertThat(player.getTotal())
                .isEqualTo(13);
    }

    @DisplayName("패가 2장만 있고, 합이 21이면 블랙잭이다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, ACE, true",
            "TEN, TEN, false",
    })
    void isBlackjackTest(CardValue value1, CardValue value2, boolean expected) {
        // given
        Player player = new Player(new Name("pobi"), NO_HIT_STRATEGY);
        player.receiveHand(createCard(Suit.SPADES, value1));
        player.receiveHand(createCard(Suit.SPADES, value2));

        // when
        boolean isBlackjack = player.isBlackjack();

        // then
        assertThat(isBlackjack)
                .isSameAs(expected);
    }

    @DisplayName("21이 초과하면 버스트이다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, TEN, TEN, true",
            "TWO, TWO, ACE, false",
    })
    void isBustTest(CardValue value1, CardValue value2, CardValue value3, boolean expected) {
        // given
        Player player = new Player(new Name("pobi"), NO_HIT_STRATEGY);
        player.receiveHand(createCard(Suit.SPADES, value1));
        player.receiveHand(createCard(Suit.SPADES, value2));
        player.receiveHand(createCard(Suit.SPADES, value3));

        // when
        boolean isBust = player.isBust();

        // then
        assertThat(isBust)
                .isSameAs(expected);
    }

    @DisplayName("플레이어가 히트 여부를 결정한다.")
    @Test
    void shouldHitFalseTest() {
        // given
        Player player = new Player(new Name("pobi"), NO_HIT_STRATEGY);

        // then
        boolean shouldHit = player.shouldHit();

        // when
        assertThat(shouldHit)
                .isFalse();
    }

    @DisplayName("플레이어가 히트 여부를 결정한다.")
    @Test
    void shouldHitTrueTest() {
        // given
        Player player = new Player(new Name("pobi"), createHitDecisionStrategy(List.of(true)));

        // then
        boolean shouldHit = player.shouldHit();

        // when
        assertThat(shouldHit)
                .isTrue();
    }
}
