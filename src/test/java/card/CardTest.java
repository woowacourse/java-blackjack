package card;

import static card.CardNumber.ACE;
import static card.Pattern.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    @DisplayName("카드를 생성할 수 있다.")
    void create() {
        assertThatCode(() -> new Card(ACE, HEART))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성된 카드의 이름을 가져올 수 있다.")
    void getName() {
        Card card = new Card(ACE, HEART);

        assertThat(card.getName()).isEqualTo("A하트");
    }

    @Test
    @DisplayName("카드의 점수를 가져올 수 있다.")
    void getScore() {
        Card card = new Card(ACE, HEART);
        int score = card.getScore();
        assertThat(score).isEqualTo(1);
    }
}
