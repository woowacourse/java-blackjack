package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {
    @Test
    @DisplayName("게이머는 자신의 패를 반환할 수 있다.")
    void getHand() {
        Gamer gamer = new Gamer();
        List<Card> cards = gamer.getCards();
        Assertions.assertThat(cards).isNotNull();
    }

    @Test
    @DisplayName("게이머는 카드를 한 장 가져갈 수 있다.")
    void takeCard() {
        Gamer gamer = new Gamer();
        Card card = new Card(CardType.SPADE, CardNumber.ACE);
        gamer.takeCard(card);
        Assertions.assertThat(gamer.getCards()).isEqualTo(List.of(card));
    }
}
