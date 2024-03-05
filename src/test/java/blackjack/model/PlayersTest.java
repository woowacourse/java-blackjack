package blackjack.model;

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
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR))
        );
        assertThatCode(() -> Players.from(names, cards));
    }

    @ParameterizedTest
    @MethodSource("InvalidNames")
    void createPlayersByOutBound(List<String> names) {
        List<Cards> cards = List.of(
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR)),
                new Cards(List.of(Deck.CLOVER_ACE, Deck.CLOVER_FOUR))
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
