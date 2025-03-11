package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.Players;
import blackjack.factory.SingDeckGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackInitManagerTest {

    @DisplayName("덱을 설정한다.")
    @Test
    void test1() {
        // given
        BlackjackInitManager blackJackInitManager = new BlackjackInitManager(new SingDeckGenerator());

        // when & then
        assertThatCode(blackJackInitManager::generateDeck)
                .doesNotThrowAnyException();
    }

    @DisplayName("이름들을 입력받아서 저장한다.")
    @Test
    void test2() {
        // given
        List<String> names = List.of("꾹이", "히로", "비타");
        BlackjackInitManager blackJackInitManager = new BlackjackInitManager(new SingDeckGenerator());

        // when
        Players players = blackJackInitManager.generatePlayers(names);

        // the
        assertThat(players.getPlayers()).hasSize(3);
    }

    @DisplayName("딜러를 반환한다.")
    @Test
    void test3() {
        // given
        BlackjackInitManager blackJackInitManager = new BlackjackInitManager(new SingDeckGenerator());

        // when & then
        assertThatCode(blackJackInitManager::generateDealer)
                .doesNotThrowAnyException();
    }
}
