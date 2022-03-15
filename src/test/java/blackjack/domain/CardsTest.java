package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("카드 추가되는지 확인하는 테스트")
    @Test
    void addCardTest() {
        Cards cards = Cards.generateCards();
        int beforeScore = cards.score();
        cards.addCard(Card.generateCard(BlackjackCardType.SPADE_K));
        int afterScore = cards.score();
        assertThat(afterScore > beforeScore).isTrue();
    }

    @DisplayName("점수 잘 계산되는지 테스트, 에이스가 3장일때")
    @Test
    void scoreTest() {
        Cards cards = Cards.generateCards();
        cards.addCard(Card.generateCard(BlackjackCardType.CLOVER_A));
        cards.addCard(Card.generateCard(BlackjackCardType.DIAMOND_A));
        cards.addCard(Card.generateCard(BlackjackCardType.SPADE_A));
        assertThat(cards.score()).isEqualTo(13);
    }

    @DisplayName("점수 잘 계산되는지 테스트, 에이스가 포함되어 있을때")
    @Test
    void scoreTest2() {
        Cards cards = Cards.generateCards();
        cards.addCard(Card.generateCard(BlackjackCardType.CLOVER_A));
        cards.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        cards.addCard(Card.generateCard(BlackjackCardType.SPADE_8));
        assertThat(cards.score()).isEqualTo(19);
    }

    @DisplayName("점수가 21이상일때 버스트로 잘 나오는지 확인하는 테스트")
    @Test
    void isBurstTest() {
        Cards cards = Cards.generateCards();
        cards.addCard(Card.generateCard(BlackjackCardType.CLOVER_10));
        cards.addCard(Card.generateCard(BlackjackCardType.DIAMOND_10));
        cards.addCard(Card.generateCard(BlackjackCardType.SPADE_10));
        assertThat(cards.isBurst(cards.score())).isTrue();
    }
}
