package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest(name = "[{index}] name \"{0}\"")
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어 이름이 공백일 경우 예외를 발생시킨다.")
    void checkNameBlank(String name) {
        Assertions.assertThatThrownBy(() -> new Player(name) {
                    @Override
                    public boolean canAddCard() {
                        return false;
                    }
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @ParameterizedTest(name = "[{index}] name {0}")
    @ValueSource(strings = {"13!", "123@", "#asd", "$wqe", "qwer%$"})
    @DisplayName("플레이어 이름에 특수문자가 들어갈 경우 예외를 발생시킨다.")
    void checkNameSpecialCharacters(String name) {
        Assertions.assertThatThrownBy(() -> new Player(name) {
                    @Override
                    public boolean canAddCard() {
                        return false;
                    }
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름에는 특수문자가 들어갈 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아닐 때 확인")
    void isNotBlackJackScoreTest() {
        Player player = new Player("배카라") {
            @Override
            public boolean canAddCard() {
                return false;
            }
        };

        player.addCard(new Card(Denomination.ACE, Suit.HEART));
        player.addCard(new Card(Denomination.ACE, Suit.DIAMOND));

        assertThat(player.isBlackJack()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 블랙잭인지 확인")
    void isBlackJackTest() {
        Player player = new Player("배카라") {
            @Override
            public boolean canAddCard() {
                return false;
            }
        };

        player.addCard(new Card(Denomination.ACE, Suit.HEART));
        player.addCard(new Card(Denomination.TEN, Suit.DIAMOND));

        assertThat(player.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 블랙잭 카드 사이즈가 아닐 때 확인")
    void isNotBlackJackSizeTest() {
        Player player = new Player("배카라") {
            @Override
            public boolean canAddCard() {
                return false;
            }
        };

        player.addCard(new Card(Denomination.TEN, Suit.HEART));
        player.addCard(new Card(Denomination.THREE, Suit.HEART));
        player.addCard(new Card(Denomination.EIGHT, Suit.HEART));

        assertThat(player.isBlackJack()).isFalse();
    }
}
