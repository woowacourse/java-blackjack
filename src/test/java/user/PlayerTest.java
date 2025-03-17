package user;

import card.Card;
import card.CardDeck;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import user.Player;

class PlayerTest {
    @DisplayName("일반 유저는 자신이 가진 모든 카드를 공개해야 한다")
    @Test
    void test() {
        // given
        String name = "수양";
        Player user = new Player(name);
        CardDeck cardDeck = new CardDeck();
        user.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());

        // when
        List<Card> cards = user.openInitialCard();

        // then
        Assertions.assertThat(cards).hasSize(2);
    }

    @DisplayName("일반 유저는 'dealer', '딜러' 이름을 사용할 수 없다")
    @ParameterizedTest
    @ValueSource(strings = {"dealer", "딜러"})
    void test2(String name) {
        Assertions.assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
    }
}