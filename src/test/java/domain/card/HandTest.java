package domain.card;

import static domain.card.Number.TWO;
import static domain.card.Shape.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    @DisplayName("핸드 카드 추가 테스트")
    void addCardTest() {
        //given
        Hand hand = new Hand(new ArrayList<>());
        Card card = new Card(DIAMOND, TWO);

        //when
        hand.addCard(card);

        //then
        assertThat(hand.getHand().getFirst()).isEqualTo(card);
    }
}
