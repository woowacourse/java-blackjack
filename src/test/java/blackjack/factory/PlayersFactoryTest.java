package blackjack.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersFactoryTest {

    @DisplayName("이름 리스트와 돈 리스트 크기가 다르면 예외를 발생한다.")
    @Test
    void test1() {
        List<String> names = List.of("꾹이", "히로");
        List<Integer> moneys = List.of(1);

        assertThatThrownBy(() -> PlayersFactory.generate(names, moneys))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Players를 반환한다.")
    @Test
    void test2() {
        List<String> names = List.of("꾹이", "히로");
        List<Integer> moneys = List.of(1, 2);

        Players generate = PlayersFactory.generate(names, moneys);

        assertThat(generate.getPlayers()).hasSize(2);
    }

    @DisplayName("리스트 순서대로 값을 저장해야 한다.")
    @Test
    void test3() {
        List<String> names = List.of("꾹이", "히로");
        List<Integer> moneys = List.of(1, 2);

        Players generate = PlayersFactory.generate(names, moneys);

        assertThat(generate.getPlayers()
                .get(0)
                .getName()).isEqualTo("꾹이");
    }
}