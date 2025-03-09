package blackjack.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 생성 시점에 0장의 카드를 가진다")
    void initializedPlayerShouldHave0Card() {
        // given
        Player player = Player.from(Nickname.from("강산"));

        // when
        // then
        assertThat(player.getCards().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어는 히트를 결정할 수 있다")
    void canDecideToHit() {
        // given
        Player player = GameParticipantFixture.createPlayer("강산");

        // when
        // then
        assertThat(player.shouldHit()).isTrue(); // TODO 추후에 성공 예정
    }

}
