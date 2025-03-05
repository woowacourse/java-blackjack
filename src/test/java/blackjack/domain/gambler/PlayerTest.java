package blackjack.domain.gambler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("이름을 통해 플레이어를 생성한다.")
    @Test
    void createTest() {
        Player player = new Player("라젤");

        assertThat(player.getName()).isEqualTo("라젤");
    }

    @DisplayName("이름이 6글자 이상인 경우 예외를 던진다.")
    @Test
    void validateNameLengthIsOverSix() {
        assertThatThrownBy(() -> new Player("라젤라젤라젤"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 1글자 이하인 경우 예외를 던진다")
    @Test
    void validateNameLengthIsBelowOne() {
        assertThatThrownBy(() -> new Player("라"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름에 공백이 포함된 경우 예외를 던진다.")
    @Test
    void validateNameHasBlank() {
        assertThatThrownBy(() -> new Player("라 젤"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("패에 카드를 추가한다")
    @Test
    void addCardTest() {
        Player player = new Player("라젤");
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);

        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.calculateSum()).isEqualTo(18);
    }

    @DisplayName("카드의 합이 21이하인 경우 버스트가 아니다")
    @Test
    void isBustTest1() {
        Player player = new Player("라젤");
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);

        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBust()).isFalse();
    }

    @DisplayName("카드의 합이 21 초과인 경우 버스트다")
    @Test
    void isBustTest2() {
        Player player = new Player("라젤");
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);
        Card card3 = new Card(CardShape.HEART, CardType.EIGHT);

        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.isBust()).isTrue();
    }
}
