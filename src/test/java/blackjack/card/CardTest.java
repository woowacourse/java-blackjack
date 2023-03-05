package blackjack.card;

import static card.Rank.ACE;
import static card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;

class CardTest {
    @Test
    @DisplayName("카드를 생성한다.")
    void create() {
        assertThatCode(() -> new Card(ACE, HEART))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성된 카드의 이름을 가져온다.")
    void getName() {
        Card card = new Card(ACE, HEART);

        assertThat(card.getName()).isEqualTo("A하트");
    }

    @Test
    @DisplayName("카드의 점수를 가져온다.")
    void getScore() {
        Card card = new Card(ACE, HEART);
        int score = card.getScore();
        assertThat(score).isEqualTo(1);
    }

    @Test
    @DisplayName("카드가 A인 경우 true를 반환한다")
    void isAce() {
        Card card = new Card(ACE, HEART);

        assertThat(card.isAce()).isTrue();
    }

    @Test
    @DisplayName("숫자와 모양이 같으면 같은 카드다.")
    void equals() {
        Card card1 = new Card(ACE, HEART);
        Card card2 = new Card(ACE, HEART);
        assertThat(card1).isEqualTo(card2);
    }
}
