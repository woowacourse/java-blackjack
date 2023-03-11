package domain;

import domain.card.Card;
import domain.user.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static domain.card.Suit.HEART;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.Assistant.addCards;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"8,K,18", "10,5,15"})
    void test_calculateScore(String denomination1, String denomination2, int score) {
        addCards(dealer, denomination1, denomination2);

        assertThat(dealer.score()).isEqualTo(new Score(score));
    }

    @ParameterizedTest(name = "A는 무조건 딜러에게 유리하게 계산한다")
    @CsvSource({"J,10,21", "7,3,21", "A,A,13"})
    void test_calculateScoreByAce(String denomination1, String denomination2, int score) {
        addCards(dealer, denomination1, denomination2, "A");

        assertThat(dealer.score()).isEqualTo(new Score(score));
    }

    @ParameterizedTest(name = "점수가 21을 초과하는지 확인한다")
    @CsvSource({"8,K,BUST", "10,5,STAY"})
    void test_bust(String denomination1, String denomination2, Status status) {
        addCards(dealer, denomination1, denomination2, "5");

        assertThat(dealer.status()).isEqualTo(status);
    }

    @DisplayName("카드를 받는다.")
    @Test
    void test_addCard() {
        addCards(dealer, "2");
        dealer.addCard(new Card(HEART, "K"));

        assertThat(dealer.score()).isEqualTo(new Score(12));
    }

    @ParameterizedTest(name = "점수가 16 초과인지 확인하여 hit이 가능한지 판단한다")
    @CsvSource({"8,9,false", "10,6,true"})
    void test_canHit(String denomination1, String denomination2, boolean isOverSixteen) {
        addCards(dealer, denomination1, denomination2);

        assertThat(dealer.canHit()).isEqualTo(isOverSixteen);
    }



}