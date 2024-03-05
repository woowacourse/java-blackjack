package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PlayerTest {

    @DisplayName("이름과, 손 패를 가지고 있다.")
    @Test
    void createSuccess() {
        Name name = new Name("wiib");
        List<Card> cards = List.of(new Card(Letter.J, Mark.CLOVER), new Card(Letter.FIVE, Mark.SPADE));
        Hand hand = new Hand(cards);

        assertThatCode(() -> new Player(name, hand))
                .doesNotThrowAnyException();
    }

    @DisplayName("손 패 값의 합을 반환한다.")
    @Test
    void getTotalScore() {
        Card card1 = new Card(Letter.SEVEN, Mark.CLOVER);
        Card card2 = new Card(Letter.K, Mark.SPADE);
        List<Card> cards = List.of(card1, card2);

        Hand hand = new Hand(cards);
        Name name = new Name("산초!!");
        Player player = new Player(name, hand);
        int actual = player.getTotalScore();
        int expected = card1.getValue() + card2.getValue();

        assertThat(actual)
                .isEqualTo(expected);

    }
}
