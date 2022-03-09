package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class GamerTest {

    @Test
    @DisplayName("hit 메서드는 입력받은 카드를 카드뭉치에 저장한다.")
    void hit_test() {
        Gamer gamer = new Gamer(new Name("aki"));
        gamer.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        gamer.hit(new Card(CardNumber.FIVE, Type.SPADE));
        gamer.hit(new Card(CardNumber.TEN, Type.SPADE));
        List<Card> cards = gamer.getCards();

        assertThat(cards.get(0)).isEqualTo(new Card(CardNumber.EIGHT, Type.CLOVER));
        assertThat(cards.get(1)).isEqualTo(new Card(CardNumber.FIVE, Type.SPADE));
        assertThat(cards.get(2)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
    }

    @Test
    @DisplayName("Gamer 클래스가 가진 카드의 점수를 계산하여 반환한다.")
    void get_score() {
        Gamer gamer = new Gamer(new Name("aki"));
        gamer.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        gamer.hit(new Card(CardNumber.THREE, Type.SPADE));

        assertThat(gamer.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("Gamer 클래스가 가진 카드의 점수가 21 이상이면 더이상 hit할 수 없다.")
    void validate_range() {
        Gamer gamer = new Gamer(new Name("aki"));
        gamer.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        gamer.hit(new Card(CardNumber.FIVE, Type.SPADE));
        gamer.hit(new Card(CardNumber.TEN, Type.SPADE));

        assertThat(gamer.isValidRange()).isFalse();
    }
}
