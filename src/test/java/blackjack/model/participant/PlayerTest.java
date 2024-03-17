package blackjack.model.participant;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Score.THREE;
import static blackjack.model.deck.Score.TWO;
import static blackjack.model.deck.Shape.CLOVER;
import static blackjack.model.deck.Shape.HEART;
import static blackjack.model.deck.Shape.SPADE;
import static blackjack.model.fixture.HandFixture.BLACKJACK_HAND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.model.deck.Card;
import blackjack.model.fixture.HandFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PlayerTest {

    @Test
    @DisplayName("참여할 사람은 이름을 가진다.")
    void createPlayer() {
        String rawName = "리브";
        Player player = Player.of(rawName, BLACKJACK_HAND.getHand());
        assertThat(player.getName()).isEqualTo(new Name(rawName));
    }

    @Test
    @DisplayName("플레이어 생성시 카드 2장을 지급받지 않으면 예외를 던진다.")
    void createCardsLowerSize() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> Player.of("몰리", new Hand(List.of(Card.from(CLOVER, ACE))))).withMessage("카드는 두 장 이상이어야 합니다.");
    }

    @Test
    @DisplayName("참여할 사람은 카드를 받는다.")
    void createPlayerWithCards() {
        Hand hand = new Hand(List.of(Card.from(CLOVER, ACE), Card.from(CLOVER, THREE)));
        Player player = Player.of("몰리", hand);
        player.receiveCard(Card.from(CLOVER, TWO));

        assertThat(player.openCards()).isEqualTo(
                List.of(Card.from(CLOVER, ACE), Card.from(CLOVER, THREE), Card.from(CLOVER, TWO)));
    }

    @ParameterizedTest
    @DisplayName("자신이 카드를 추가로 더 받을 수 있는지 확인한다.")
    @EnumSource(names = {"OVER_16_UNDER_21_HAND", "UNDER_16_HAND"})
    void canHit(HandFixture handFixture) {
        Player player = Player.of("몰리", handFixture.getHand());

        assertThat(player.canHit()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("자신이 카드를 추가로 더 받을 수 없는지 확인한다.")
    @EnumSource(names = {"BLACKJACK_HAND", "BUST_HAND", "NOT_BLACKJACK_BUT_21_HAND"})
    void canNotHit(HandFixture handFixture) {
        Player player = Player.of("몰리", handFixture.getHand());

        assertThat(player.canHit()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 가진 카드의 합이 임계 값을 넘으면 bust이다.")
    void isBust() {
        Hand hand = new Hand(List.of(Card.from(CLOVER, TEN), Card.from(SPADE, TEN), Card.from(HEART, TEN)));
        Player player = Player.of("몰리", hand);
        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 가진 카드의 합이 21이고 카드의 수가 2장이면 블랙잭이다.")
    void isBlackJack() {
        Hand hand = new Hand(List.of(Card.from(CLOVER, ACE), Card.from(HEART, TEN)));
        Player player = Player.of("몰리", hand);
        assertThat(player.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("자신이 가지고 있는 카드의 개수를 반환한다.")
    void announceCardCount() {
        Hand hand = new Hand(List.of(Card.from(CLOVER, ACE), Card.from(CLOVER, TEN)));
        Player player = Player.of("몰리", hand);

        assertThat(player.getCardCount()).isEqualTo(2);
    }
}
