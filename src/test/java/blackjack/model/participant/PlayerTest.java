package blackjack.model.participant;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Score.THREE;
import static blackjack.model.deck.Score.TWO;
import static blackjack.model.deck.Shape.CLOVER;
import static blackjack.model.deck.Shape.DIA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.model.deck.Card;
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
        Player player = Player.of(name, hand);
        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("플레이어 생성시 카드 2장을 지급받지 않으면 예외를 던진다.")
    void createCardsLowerSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Player.of("몰리", new Hand(List.of(new Card(CLOVER, ACE)))))
                .withMessage("카드는 두 장 이상이어야 합니다.");
    }

    @Test
    @DisplayName("참여할 사람은 카드를 받는다.")
    void createPlayerWithCards() {
        Hand hand = new Hand(List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE)));
        Player player = Player.of("몰리", hand);
        player.receiveCard(new Card(CLOVER, TWO));

        assertThat(player.openCards())
                .isEqualTo(List.of(new Card(CLOVER, ACE), new Card(CLOVER, THREE), new Card(CLOVER, TWO)));
    }

    @Test
    @DisplayName("자신이 카드를 추가로 더 받을 수 있는지 확인한다.")
    void checkNotBust() {
        Hand hand = new Hand(List.of(new Card(DIA, TEN), new Card(CLOVER, TEN)));
        Player player = Player.of("몰리", hand);

        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("자신이 가지고 있는 카드의 개수를 반환한다.")
    void announceCardCount() {
        Hand hand = new Hand(List.of(new Card(CLOVER, ACE), new Card(CLOVER, TEN)));
        Player player = Player.of("몰리", hand);

        assertThat(player.announceCardCount()).isEqualTo(2);
    }
}
