package blackJack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    private Set<Card> cards;
    private Score score;

    @BeforeEach
    void setUp() {
        cards = new HashSet<>();
        score = new Score();
    }

    @Test
    @DisplayName("카드에 Ace가 11로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceEleven() {
        cards.add(Card.from(Suit.CLOVER, Denomination.ACE));
        cards.add(Card.from(Suit.CLOVER, Denomination.JACK));

        assertThat(score.calculateFinalScore(cards)).isEqualTo(21);
    }

    @Test
    @DisplayName("카드에 Ace가 1로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceOne() {
        cards.add(Card.from(Suit.CLOVER, Denomination.ACE));
        cards.add(Card.from(Suit.CLOVER, Denomination.JACK));
        cards.add(Card.from(Suit.CLOVER, Denomination.EIGHT));

        assertThat(score.calculateFinalScore(cards)).isEqualTo(19);
    }

    @Test
    @DisplayName("카드에 Ace가 여러개인 경우 계산 테스트")
    void calculateScoreWithAceCountThree() {
        cards.add(Card.from(Suit.CLOVER, Denomination.ACE));
        cards.add(Card.from(Suit.HEART, Denomination.ACE));
        cards.add(Card.from(Suit.DIAMOND, Denomination.ACE));
        cards.add(Card.from(Suit.SPADE, Denomination.EIGHT));

        assertThat(score.calculateFinalScore(cards)).isEqualTo(21);
    }
}
