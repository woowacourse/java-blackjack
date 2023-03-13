package domain;

import static domain.card.Suit.HEART;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.Assistant.addCards;

import domain.card.Card;
import domain.user.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "처음 2장의 카드를 받았을 때 점수가 21점이 되면 딜러의 상태는 BLACKJACK가 된다.")
    @CsvSource({"K,A,BLACKJACK", "A,J,BLACKJACK"})
    void test_updateStatus_blackjack(String letter1, String letter2, Status status) {
        addCards(dealer, letter1, letter2);

        assertThat(dealer.status()).isEqualTo(status);
    }

    @ParameterizedTest(name = "점수가 21점 초과이면 딜러의 상태는 BUST 된다.")
    @CsvSource({"K,J,5,BUST", "9,J,7,BUST"})
    void test_updateStatus_bust(String letter1, String letter2, String letter3, Status status) {
        addCards(dealer, letter1, letter2, letter3);

        assertThat(dealer.status()).isEqualTo(status);
    }

    @ParameterizedTest(name = "처음 2장의 카드를 받았을 때 점수가 16점 초과이면 딜러의 상태는 STAY 된다.")
    @CsvSource({"K,J,STAY", "J,7,STAY"})
    void test_updateStatus_stay(String letter1, String letter2, Status status) {
        addCards(dealer, letter1, letter2);

        assertThat(dealer.status()).isEqualTo(status);
    }
}