package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CardHandTest {

    private CardHand cardHand;

    @BeforeEach
    void setUp() {
        cardHand = new CardHand();
    }

    @Test
    void 손패에_카드를_추가할_수_있다() {
        cardHand.takeCards(Card.DIAMOND_2);

        List<Card> cards = cardHand.getCards();
        assertThat(cards).contains(Card.DIAMOND_2);
    }

    @ParameterizedTest
    @CsvSource({
        "SPADE_A,SPADE_10",
        "DIAMOND_A,HEART_J",
        "CLOVER_Q,SPADE_A",
        "CLOVER_10,HEART_A"
    })
    void 카드_두_장으로_21점이면_블랙잭이다(Card card1, Card card2) {
        cardHand.takeCards(card1, card2);
        assertThat(cardHand.isBlackJack()).isTrue();
    }

    @Test
    void 점수의_합이_21점을_넘으면_버스트이다() {
        cardHand.takeCards(Card.DIAMOND_10, Card.DIAMOND_J, Card.DIAMOND_2);
        assertThat(cardHand.isBust()).isTrue();
    }

    @Test
    void 카드의_점수의_합을_구할수있다() {
        cardHand.takeCards(Card.CLOVER_J, Card.HEART_5);

        var score = cardHand.calculateScore();

        assertThat(score).isEqualTo(15);
    }

    @Test
    void A가있을때_나머지숫자의_합이_11이상이면_1을_선택한다() {
        cardHand.takeCards(Card.HEART_A, Card.CLOVER_A);

        var score = cardHand.calculateScore();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void A가있을때_나머지숫자의_합이_10이하이면_11을_선택한다() {
        cardHand.takeCards(Card.HEART_A, Card.CLOVER_J);

        var score = cardHand.calculateScore();

        assertThat(score).isEqualTo(21);
    }
}
