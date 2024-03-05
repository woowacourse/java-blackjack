package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {

    @DisplayName("게이머의 카드 목록에 카드를 추가한다.")
    @Test
    void addCard() {
        //given
        Card card = new Card(CardPattern.CLOVER, CardNumber.EIGHT);
        Gamer gamer = new Gamer();

        //when
        gamer.addCard(card);
        List<Card> gamerCards = gamer.getCards();

        //then
        assertThat(gamerCards).containsExactly(card);
    }
}
