package blackjack.model;

import static blackjack.model.Score.ACE;
import static blackjack.model.Score.TEN;
import static blackjack.model.Score.THREE;
import static blackjack.model.Score.TWO;
import static blackjack.model.Shape.CLOVER;
import static blackjack.model.Shape.DIA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PlayerTest {

    @Test
    @DisplayName("참여할 사람은 이름을 가진다.")
    void createPlayer() {
        String name = "리브";
        Hand hand = new Hand(List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE)));
        Player player = new Player(name, hand);
        assertThat(player.getName()).isEqualTo(name);
    }

    @ParameterizedTest(name = "이름 입력 시 blank가 들어온 경우 예외를 발생한다.")
    @NullAndEmptySource
    void createPlayerByBlank(String name) {
        Hand hand = new Hand(List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE)));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player(name, hand))
                .withMessage("이름에는 공백을 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어 생성시 카드 2장을 지급받지 않으면 예외를 던진다.")
    void createCardsLowerSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player("몰리", new Hand(List.of(new Card(CLOVER, ACE)))))
                .withMessage("카드는 두 장 이상이어야 합니다.");
    }

    @Test
    @DisplayName("참여할 사람은 카드를 받는다.")
    void createPlayerWithCards() {
        Hand hand = new Hand(List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE)));
        Player player = new Player("몰리", hand);
        player.receiveCard(new Card(CLOVER, TWO));

        assertThat(player.openCards())
                .isEqualTo(List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE), new Card(CLOVER, TWO)));
    }

    @Test
    @DisplayName("자신이 카드를 추가로 더 받을 수 있는지 확인한다.")
    void checkNotBust() {
        Hand hand = new Hand(List.of(new Card(DIA, TEN), new Card(CLOVER, TEN)));
        Player player = new Player("몰리", hand);

        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("자신이 가지고 있는 카드의 개수를 반환한다.")
    void announceCardCount() {
        Hand hand = new Hand(List.of(new Card(CLOVER, ACE), new Card(CLOVER, TEN)));
        Player player = new Player("몰리", hand);

        assertThat(player.announceCardCount()).isEqualTo(2);
    }
}
