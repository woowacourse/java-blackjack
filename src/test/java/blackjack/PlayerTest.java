package blackjack;

import blackjack.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    private CardDeck cardDeck;
    private Player player;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck(new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.HEART, Type.TEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.DIAMOND, Type.JACK),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.CLOVER, Type.EIGHT)
                )
        ));
        player = new Player("pobi", 1);
    }

    @DisplayName("user 생성 시 빈 문자열일 경우 예외 발생 확인")
    @Test
    void emptyStringTest() {
        assertThatThrownBy(() -> {
            new Player("", 1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 공백일 수 없습니다.");
    }

    @DisplayName("유저의 이름을 반환하는지 확인")
    @Test
    void getNameTest() {
        User user = new Player("pobi", 1);
        assertThat(user.getName()).isEqualTo("pobi");
    }

    @DisplayName("유저의 초기 카드 오픈상태 확인")
    @Test
    void getInitialCardsTest() {
        player.receiveInitialCards(cardDeck);
        assertThat(player.getInitialCards()).containsExactly(
                new Card(Symbol.CLOVER, Type.EIGHT), new Card(Symbol.CLOVER, Type.ACE));
    }

    @DisplayName("배팅금액으로 0이나 음수를 입력했을때 예외발생 확인")
    @ParameterizedTest
    @ValueSource(ints = {-1000, 0})
    void negativeBettingMoneyTest(int bettingMoney) {
        assertThatThrownBy(() -> new Player("bossdog", bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액으로는 양수만 가능합니다.");
    }
}
