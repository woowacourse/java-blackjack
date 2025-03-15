package blackjack.model.participant;

import static blackjack.model.card.CardFixtures.SPADE_ACE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어 테스트")
class PlayerTest {

    @DisplayName("이름을 가진다.")
    @Test
    void createPlayerTest() {
        // given
        String pobiName = "포비";

        // when, then
        assertThatCode(() -> new Player(pobiName))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 받을 수 있다.")
    @Test
    void receiveHandTest() {
        // given
        Player player = new Player("포비");

        // when
        player.receiveCard(SPADE_ACE_CARD);

        // then
        assertThat(player.getHandCards())
                .contains(SPADE_ACE_CARD);

    }

    @DisplayName("가진 패의 총합이 21 이상일 때 카드를 받는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenReceiveCardAfterHandExceeds21() {
        // given
        Player player = new Player("포비");

        // when
        player.receiveCard(SPADE_ACE_CARD);
        player.receiveCard(SPADE_TEN_CARD);

        // then
        assertThatCode(() -> player.receiveCard(SPADE_TEN_CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭이라 카드를 더 받을 수 없습니다.");
    }

    @DisplayName("가진 패의 총합이 21 이상인 경우 히트할 수 없다. (끝난다.)")
    @Test
    void isFinishedTest() {
        // given
        Player player = new Player("포비");
        player.receiveCard(SPADE_ACE_CARD);
        player.receiveCard(SPADE_TEN_CARD);

        // then
        boolean isFinished = player.isFinished();

        // when
        assertThat(isFinished)
                .isTrue();
    }

    @DisplayName("플레이어의 이름을 반환한다.")
    @Test
    void getNameTest() {
        // given
        String pobiName = "포비";
        Player player = new Player(pobiName);

        // when
        String name = player.getName();

        // then
        assertThat(name)
                .isEqualTo(pobiName);
    }

    @DisplayName("플레이어의 패를 반환한다.")
    @Test
    void getHandCardsTest() {
        // given
        Player player = new Player("포비");
        player.receiveCard(SPADE_ACE_CARD);

        // when
        int size = player.getHandCards().size();

        // then
        assertThat(size)
                .isEqualTo(1);
    }

    @DisplayName("플레이어의 패의 총합을 반환한다.")
    @Test
    void getTotalTest() {
        // given
        Player player = new Player("포비");
        player.receiveCard(SPADE_ACE_CARD);

        // when
        int total = player.getTotal();

        // then
        assertThat(total)
                .isEqualTo(11);
    }
}
