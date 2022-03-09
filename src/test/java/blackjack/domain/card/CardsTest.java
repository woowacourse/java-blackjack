package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CardsTest {

    @Test
    @DisplayName("카드를 추가할 떄 null을 전달하면 예외를 발생한다.")
    void thrownExceptionWhenGivenNull() {
        Cards cards = new Cards();
        assertThatThrownBy(() -> cards.addCard(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 카드를 입력해주세요.");
    }

    @Test
    @DisplayName("카드를 추가하면 관리하는 카드 개수가 늘어난다.")
    void addCard() {
        Cards cards = new Cards();
        cards.addCard(new Card(Type.SPADE, Score.ACE));
        cards.addCard(new Card(Type.DIAMOND, Score.TWO));
        cards.addCard(new Card(Type.CLOVER, Score.THREE));

        assertThat(cards.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("cards의 점수 합을 구한다.")
    void sumCardScore() {
        Cards cards = new Cards();
        cards.addCard(new Card(Type.SPADE, Score.ACE));
        cards.addCard(new Card(Type.DIAMOND, Score.TWO));
        cards.addCard(new Card(Type.CLOVER, Score.THREE));

        assertThat(cards.calculateScore()).isEqualTo(6);
    }

}
