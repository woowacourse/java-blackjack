package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suits;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setPlayer() {
        player = new Player("kiara", new Cards());
    }

    @DisplayName("이름은 5글자 이하이다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "ash", "kiara", "woowa"})
    void validateNameLength(String name) {
        assertThatNoException().isThrownBy(() -> new Player(name, new Cards()));
    }

    @DisplayName("이름이 5글자 초과면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"hijava", "helloworld", "woowacourse"})
    void nameNotOver5(String name) {
        assertThatThrownBy(() -> new Player(name, new Cards()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("플레이어의 이름은 ")
            .hasMessageContaining("보다 길 수 없습니다.");
    }

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void nameNotEmpty(String name) {
        assertThatThrownBy(() -> new Player(name, new Cards()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 이름은 공백으로만 이루어질 수 없습니다.");
    }

    @DisplayName("카드 점수가 21보다 작으면 카드를 받을 수 있다")
    @Test
    void hit_WhenScoreUnder21() {
        player.hit(new Card(Denomination.JACK, Suits.HEART));
        player.hit(new Card(Denomination.QUEEN, Suits.HEART));
        assertThat(player.isHittable()).isTrue();
    }

    @DisplayName("카드 점수가 21 이상이면 카드를 받을 수 없다")
    @Test
    void stay_WhenScoreOver21() {
        player.hit(new Card(Denomination.JACK, Suits.HEART));
        player.hit(new Card(Denomination.FIVE, Suits.HEART));
        player.hit(new Card(Denomination.SIX, Suits.HEART));
        assertThat(player.isHittable()).isFalse();
    }
}
