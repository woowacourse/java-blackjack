package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        this.hand = new Hand(
            Collections.singletonList(Card.valueOf(Pattern.DIAMOND, Number.THREE))
        );
    }

    @Test
    @DisplayName("손패에 카드 추가 테스트")
    void testAddCardInHand() {
        this.hand.addCard(Card.valueOf(Pattern.DIAMOND, Number.TWO));
        assertThat(this.hand.getScoreToInt()).isEqualTo(5);
    }

    @Test
    @DisplayName("손패 점수 총합 테스트")
    void testHandsTotalScore() {
        this.hand.addCard(Card.valueOf(Pattern.CLOVER, Number.EIGHT));
        this.hand.addCard(Card.valueOf(Pattern.CLOVER, Number.NINE));
        this.hand.addCard(Card.valueOf(Pattern.CLOVER, Number.QUEEN));
        assertThat(this.hand.getScoreToInt()).isEqualTo(30);
    }

    @Test
    @DisplayName("SoftAce에서 HardAce로 자동변환 테스트")
    void testAceAutoChange() {
        this.hand.addCard(Card.valueOf(Pattern.DIAMOND, Number.ACE));
        this.hand.addCard(Card.valueOf(Pattern.CLOVER, Number.ACE));
        this.hand.addCard(Card.valueOf(Pattern.HEART, Number.ACE));
        assertThat(this.hand.getScoreToInt()).isEqualTo(16);
    }
}
