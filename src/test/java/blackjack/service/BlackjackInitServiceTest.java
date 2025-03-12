package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.Players;
import blackjack.factory.SingDeckGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackInitServiceTest {

    @DisplayName("덱을 설정한다.")
    @Test
    void test1() {
        // given
        BlackjackInitService blackJackInitService = new BlackjackInitService(new SingDeckGenerator());

        // when & then
        assertThatCode(blackJackInitService::generateDeck)
                .doesNotThrowAnyException();
    }

    @DisplayName("이름들을 입력받아서 저장한다.")
    @Test
    void test2() {
        // given
        List<String> names = List.of("꾹이", "히로", "비타");
        BlackjackInitService blackJackInitService = new BlackjackInitService(new SingDeckGenerator());

        // when
        Players players = blackJackInitService.generatePlayers(names);

        // the
        assertThat(players.getPlayers()).hasSize(3);
    }

    @DisplayName("딜러를 반환한다.")
    @Test
    void test3() {
        // given
        BlackjackInitService blackJackInitService = new BlackjackInitService(new SingDeckGenerator());

        // when & then
        assertThatCode(blackJackInitService::generateDealer)
                .doesNotThrowAnyException();
    }
}
