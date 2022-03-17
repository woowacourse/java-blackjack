package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
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

    @DisplayName("에이스가 3장일때 점수가 최적으로 계산되는지 테스트")
    @Test
    void scoreTest() {
        Cards cards = Cards.generateCards();
        cards.addCard(Card.generateCard(BlackjackCardType.CLOVER_A));
        cards.addCard(Card.generateCard(BlackjackCardType.DIAMOND_A));
        cards.addCard(Card.generateCard(BlackjackCardType.SPADE_A));
        assertThat(cards.score()).isEqualTo(13);
    }

    @DisplayName("에이스가 포함되어 있을때 점수가 최적으로 계산되는지 테스트")
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

    @DisplayName("논리적으로 같으면 equals가 true로 나오는지 테스트")
    @Test
    void equalsTest() {
        Cards cards1 = Cards.generateCardsAndFill(List.of(Card.generateCard(BlackjackCardType.CLOVER_A)));
        Cards cards2 = Cards.generateCardsAndFill(List.of(Card.generateCard(BlackjackCardType.CLOVER_A)));

        assertThat(cards1.equals(cards2)).isTrue();
    }

    @DisplayName("논리적으로 같은 객체 hashcode 같은지 확인하는 테스트")
    @Test
    void hashCodeTest() {
        Cards cards1 = Cards.generateCardsAndFill(List.of(Card.generateCard(BlackjackCardType.CLOVER_A)));
        Cards cards2 = Cards.generateCardsAndFill(List.of(Card.generateCard(BlackjackCardType.CLOVER_A)));

        assertThat(cards1.hashCode()).isEqualTo(cards2.hashCode());
    }
}
