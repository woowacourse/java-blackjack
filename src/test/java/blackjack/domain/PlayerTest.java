package blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PlayerTest {

    @Test
    void 플레이어는_이름으로_생성한다() {
        // given
        final String name = "dompoo";

        // expected
        Assertions.assertDoesNotThrow(() -> new Player(name));
    }
    
    @Test
    void 플레이어를_생성하면_이름을_확인할_수_있다() {
        // given
        final Player player = new Player("dompoo");
        
        // when
        final String result = player.getName();
        
        // then
        assertThat(result).isEqualTo("dompoo");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"돔", "돔푸돔푸돔푸돔푸돔"})
    void 플레이어의_이름이_2에서_8글자가_아니면_예외를_발생시킨다(String name) {
        // expected
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 2자 이상, 8자 이하여야 합니다.");
    }
    
    @Test
    void 플레이어들을_생성할_수_있다() {
        // given
        final List<String> playerNames = List.of("돔푸", "메이");
        
        // when
        List<Player> players = Player.createPlayers(playerNames);
        
        // expected
        assertThat(players).hasSize(2);
    }
    
    @Test
    void 중복된_플레이어가_존재하는_경우_예외를_발생시킨다() {
        // given
        final List<String> playerNames = List.of("돔푸", "메이", "돔푸");
        
        // expected
        assertThatThrownBy(() -> {Player.createPlayers(playerNames);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }
    
    @ParameterizedTest
    @MethodSource("providePlayers")
    void 플레이어_인원수는_1명부터_6명까지_입니다(List<String> playerNames) {
        // expected
        assertThatThrownBy(() -> Player.createPlayers(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 인원수는 1명부터 6명까지 입니다.");
    }
    
    private static Stream<Arguments> providePlayers() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of("메이", "돔푸", "리사", "포라", "밍곰", "멍구", "가이온"))
        );
    }
}
