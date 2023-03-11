package domain;

import static domain.fixture.PlayerFixture.빙봉;
import static domain.fixture.PlayerFixture.우가;
import static domain.fixture.PlayerFixture.하마드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/05
 */
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayersTest {

    @Test
    void 플레이어스는_빈_리스트_초기화시_예외를_던진다() {
        assertThatThrownBy(() -> new Players(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭은 최소 한명이상 가능합니다.");
    }

    @Test
    void 플레이어스의_사이즈를_반환한다() {
        Players players = new Players(List.of(우가, 빙봉, 하마드));

        assertThat(players.size()).isEqualTo(3);
    }

    @Test
    void 플레이어스의_해당인덱스_플레이어는_버스트가_아닌지_확인한다() {
        Players players = new Players(List.of(우가, 빙봉, 하마드));

        assertThat(players.isNotBurst(0)).isTrue();
    }

    @Test
    void 플레이어스의_해당인덱스_플레이어는_카드합을_반환한다() {
        Players players = new Players(List.of(우가, 빙봉, 하마드));

        assertAll(
                () -> assertThat(players.getCardsSum(0)).isEqualTo(13),
                () -> assertThat(players.getCardsSum(1)).isEqualTo(21),
                () -> assertThat(players.getCardsSum(2)).isEqualTo(12)
        );
    }
}
