package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class AbstractGamerTest {

    @Test
    @DisplayName("hit 메서드는 입력받은 카드를 카드뭉치에 저장한다.")
    void hit_test() {
        AbstractGamer abstractGamer = new Player(new Name("aki"));
        abstractGamer.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        abstractGamer.hit(new Card(CardNumber.FIVE, Type.SPADE));
        abstractGamer.hit(new Card(CardNumber.TEN, Type.SPADE));
        List<Card> cards = abstractGamer.getCards().get();

        assertThat(cards.get(0)).isEqualTo(new Card(CardNumber.EIGHT, Type.CLOVER));
        assertThat(cards.get(1)).isEqualTo(new Card(CardNumber.FIVE, Type.SPADE));
        assertThat(cards.get(2)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
    }

    @Test
    @DisplayName("Gamer 클래스가 가진 카드의 점수를 계산하여 반환한다.")
    void get_score() {
        AbstractGamer abstractGamer = new Player(new Name("aki"));
        abstractGamer.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        abstractGamer.hit(new Card(CardNumber.THREE, Type.SPADE));

        assertThat(abstractGamer.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("Gamer 클래스가 가진 카드의 점수가 21 이상이면 더이상 hit할 수 없다.")
    void validate_range() {
        AbstractGamer abstractGamer = new Player(new Name("aki"));
        abstractGamer.hit(new Card(CardNumber.EIGHT, Type.CLOVER));
        abstractGamer.hit(new Card(CardNumber.FIVE, Type.SPADE));
        abstractGamer.hit(new Card(CardNumber.TEN, Type.SPADE));

        assertThat(abstractGamer.isValidRange()).isFalse();
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘지 않으면 Ace는 11로 계산한다.")
    void ace_calculate_11() {
        AbstractGamer abstractGamer = new Player(new Name("aki"));
        abstractGamer.hit(new Card(CardNumber.TEN, Type.CLOVER));
        abstractGamer.hit(new Card(CardNumber.ACE, Type.SPADE));

        assertThat(abstractGamer.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        AbstractGamer abstractGamer = new Player(new Name("aki"));
        abstractGamer.hit(new Card(CardNumber.TEN, Type.CLOVER));
        abstractGamer.hit(new Card(CardNumber.THREE, Type.HEART));
        abstractGamer.hit(new Card(CardNumber.ACE, Type.SPADE));

        assertThat(abstractGamer.getScore()).isEqualTo(14);
    }
}
