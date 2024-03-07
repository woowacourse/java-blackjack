package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {

//    TODO: "private final Name TEST_NAME = new Name("test")" fixture를 써야 하나?

    @Test
    @DisplayName("게이머는 자신의 패를 반환할 수 있다.")
    void getHand() {
        Gamer gamer = new Gamer(new Name("test"));
        List<Card> cards = gamer.getCards();
        Assertions.assertThat(cards).isNotNull();
    }

    @Test
    @DisplayName("게이머는 카드를 한 장 가져갈 수 있다.")
    void takeCard() {
        Gamer gamer = new Gamer(new Name("test"));
        Card card = new Card(CardType.SPADE, CardNumber.ACE);
        gamer.takeCard(card);
        Assertions.assertThat(gamer.getCards()).isEqualTo(List.of(card));
    }

    @Test
    @DisplayName("게이머의 이름이 일치하는지 확인 할 수 있다.")
    void createName() {
        Gamer gamer = new Gamer(new Name("test"));
        Assertions.assertThat(gamer.isName("test")).isTrue();
    }
}
