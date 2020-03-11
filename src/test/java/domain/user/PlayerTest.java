package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.CardFactory;
import domain.card.Deck;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PlayerTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Player 생성 시 이름 인자의 null, empty 체크")
    void nullAndEmptyTest(String input) {
        assertThatThrownBy(() -> new Player(input)).
            isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("초기 2장의 카드 받기 테스트")
    void receiveFirstCards() {
        Deck deck = CardFactory.create();
        Player player = new Player("pobi");
        player.receiveFirstCards(deck);
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @ParameterizedTest
    @MethodSource("getIsSameNameTestCases")
    @DisplayName("")
    void isSameName(Player player, String name, boolean result) {
        assertThat(player.isSameName(name)).isEqualTo(result);
    }

    private static Stream<Arguments> getIsSameNameTestCases() {
        return Stream.of(
            Arguments.of(new Player("오렌지"), "오렌지", true),
            Arguments.of(new Player("오렌지"), "히히", false)
        );
    }
}
