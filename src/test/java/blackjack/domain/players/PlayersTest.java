package blackjack.domain.players;

import static blackjack.domain.Fixtures.ACE;
import static blackjack.domain.Fixtures.TEN;
import static blackjack.domain.Fixtures.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Fixtures;
import blackjack.domain.players.participant.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    private final Fixtures fx = new Fixtures();

    @Test
    @DisplayName("플레이어 모음 생성 기능 예외처리 테스트")
    public void createErrorTest() {
        assertThatThrownBy(() -> new Players(List.of(fx.POBI, fx.POBI)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어들 이름 반환 기능 테스트")
    public void getPlayerNamesTest() {
        // given
        Players players = new Players(List.of(fx.POBI, fx.JASON));
        // then
        assertThat(players.getNames())
                .contains("pobi", "jason");
    }

    @Test
    @DisplayName("페이아웃 반환 테스트")
    public void getPayoutsTest() {
        Fixtures fx = new Fixtures();
        // given
        Players players = new Players(List.of(fx.POBI));
        Dealer dealer = new Dealer(List.of(TWO, ACE));
        dealer.stay();

        // when
        fx.POBI.addCard(TEN);
        fx.POBI.addCard(ACE);

        // then
        assertThat(players.getPayouts(dealer))
                .containsEntry(fx.POBI, 15000);

    }
}
