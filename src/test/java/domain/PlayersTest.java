package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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

    Player player1 = new Player(new Name("우가"),
            new Cards(List.of(new Card(Shape.HEART, CardInfo.A), new Card(Shape.HEART, CardInfo.TWO))));
    Player player2 = new Player(new Name("하마드"),
            new Cards(List.of(new Card(Shape.HEART, CardInfo.NINE), new Card(Shape.HEART, CardInfo.THREE))));
    Player player3 = new Player(new Name("빙봉"),
            new Cards(List.of(new Card(Shape.HEART, CardInfo.TEN), new Card(Shape.HEART, CardInfo.FOUR))));

    Players players;

    @BeforeEach
    void init() {
        players = new Players(List.of(player1, player2, player3));
    }

    @Test
    void 플레이어스는_빈_리스트_초기화시_예외를_던진다() {
        assertThatThrownBy(() -> new Players(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭은 최소 한명이상 가능합니다.");
    }

    @Test
    void 플레이어스의_사이즈를_반환한다() {
        assertThat(players.size()).isEqualTo(3);
    }

    @Test
    void 플레이어스의_해당인덱스_플레이어는_버스트가_아닌지_확인한다() {
        assertThat(players.isNotBurst(0)).isTrue();
    }

    @Test
    void 플레이어스의_해당인덱스_플레이어는_카드합을_반환한다() {
        assertAll(
                () -> assertThat(players.getCardsSum(0)).isEqualTo(13),
                () -> assertThat(players.getCardsSum(1)).isEqualTo(12),
                () -> assertThat(players.getCardsSum(2)).isEqualTo(14)
        );
    }
}
