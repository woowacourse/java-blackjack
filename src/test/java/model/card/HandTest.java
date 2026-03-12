package model.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest {

    @ParameterizedTest
    @MethodSource("fixture.CardsTestFixture#카드_목록_별_점수_정보_제공")
    void 참가자에게_카드가_주어지면_기본_점수를_계산할_수_있다(List<Card> randomCards, int expectedScore) {
        // given
        Hand hand = new Hand();
        randomCards.forEach(hand::add);

        // when
        int score = hand.calculateScore();
        
        // then
        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    void 카드_목록에서_Ace_개수를_셀_수_있다() {
        // given
        Hand hand = new Hand();
        hand.add(new Card(CardShape.DIAMOND, CardValue.ACE));
        hand.add(new Card(CardShape.HEART, CardValue.ACE));

        // when
        int count = hand.countAce();

        // then
        assertThat(count).isEqualTo(2);
    }
}
