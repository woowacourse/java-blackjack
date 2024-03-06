package blackjack.model;

import static blackjack.model.Score.ACE;
import static blackjack.model.Score.EIGHT;
import static blackjack.model.Score.FIVE;
import static blackjack.model.Score.FOUR;
import static blackjack.model.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름과 초기 카드들을 받아서 플레이어 그룹을 생성한다.")
    void createPlayers() {
        List<String> names = List.of("리브", "몰리");
        List<Cards> cards = List.of(
                new Cards(List.of(new Card(CLOVER, ACE), new Card(CLOVER, FIVE))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT)))
        );
        assertThatCode(() -> Players.from(names, cards));
    }

    @Test
    @DisplayName("플레이어의 이름이 중복되는 경우 예외를 던진다.")
    void createPlayersByDuplicatedName() {
        List<String> names = List.of("몰리", "몰리");
        List<Cards> cards = List.of(
                new Cards(List.of(new Card(CLOVER, ACE), new Card(CLOVER, FIVE))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT)))
        );
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.from(names, cards))
                .withMessage("중복되는 이름을 입력할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("InvalidNames")
    void createPlayersByOutBound(List<String> names) {
        List<Cards> cards = List.of(
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT))),
                new Cards(List.of(new Card(CLOVER, FOUR), new Card(CLOVER, EIGHT)))
        );

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.from(names, cards))
                .withMessage("참여할 인원의 수는 최소 1명 최대 10명이어야 합니다.");
    }

    private static Stream<Arguments> InvalidNames() {
        return Stream.of(
                Arguments.arguments(List.of()),
                Arguments.arguments(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"))
        );
    }
}
