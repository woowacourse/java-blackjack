package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("뽑은 카드를 저장한다.")
    @Test
    void addCardTest() {
        // given
        Hand hand = new Hand();
        Card expectedCard = new Card(Symbol.HEART, Rank.ACE);

        // when
        hand.add(expectedCard);
        List<Card> result = hand.getCards();

        // then
        assertAll(
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.get(0)).isEqualTo(expectedCard)
        );
    }
}
