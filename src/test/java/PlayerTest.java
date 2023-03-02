import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    @Test
    @DisplayName("점수를 계산한다.")
    void 점수_계산() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Player player = new Player("name", cardHand);
        assertThat(player.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("이름을 반환한다.")
    void 이름_반환() {
        String nameValue = "name";
        Player player = new Player(nameValue);
        assertThat(player.getNameValue()).isEqualTo(nameValue);
    }

    @Test
    @DisplayName("카드를 더 받을 수 있는 경우 true를 반환한다.")
    void 카드_추가_가능() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Player player = new Player("name", cardHand);
        assertThat(player.canAdd()).isTrue();
    }

    @Test
    @DisplayName("카드를 더 받을 수 없는 경우 false를 반환한다.")
    void 카드_추가_불가능() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.ACE));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Player player = new Player("name", cardHand);
        assertThat(player.canAdd()).isFalse();
    }

    @Test
    @DisplayName("카드를 저장한다.")
    void 카드_저장() {
        Player player = new Player("name");
        assertThat(player.calculateScore()).isEqualTo(0);
        player.addCard(new Card(Symbol.SPADE, CardNumber.TWO));
        assertThat(player.calculateScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 추가하는 것이 불가능할 때 추가하려는 경우 예외가 발생한다.")
    void 카드_저장_불가능시_예외() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.ACE));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Player player = new Player("name", cardHand);
        assertThatThrownBy(() -> player.addCard(new Card(Symbol.SPADE, CardNumber.ACE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드 추가가 불가능하여 실행되지 않았습니다.");
    }
}