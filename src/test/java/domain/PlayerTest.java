package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class PlayerTest {

    @DisplayName("이름과, 손 패를 가지고 있다.")
    @Test
    void createSuccess() {
        String name = "wiib";
        List<Card> cards = List.of(new Card(Letter.J, Mark.CLOVER), new Card(Letter.FIVE, Mark.SPADE));
        Hand hand = new Hand(cards);

        assertThatCode(() -> new Player(name, hand))
                .doesNotThrowAnyException();
    }
}
