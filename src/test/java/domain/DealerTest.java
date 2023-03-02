package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"diamond,8,heart,K,18", "diamond,10,spade,5,15"})
    void a(String face1, String letter1, String face2, String letter2, int score) {
        var dealer = new Dealer(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2)));

        assertThat(dealer.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "A는 무조건 딜러에게 유리하게 계산한다")
    @CsvSource({"diamond,J,heart,10,21", "diamond,7,spade,3,21", "diamond,A,spade,A,13"})
    void b(String face1, String letter1, String face2, String letter2, int score) {
        var dealer = new Dealer(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2),
                new Card("spade", "A")));

        assertThat(dealer.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "점수가 21을 초과하는지 확인한다")
    @CsvSource({"diamond,8,heart,K,true", "diamond,10,spade,5,false"})
    void c(String face1, String letter1, String face2, String letter2, boolean isBusted) {
        var dealer = new Dealer(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2),
                new Card("spade", "5")));

        assertThat(dealer.isBusted()).isEqualTo(isBusted);
    }

    @DisplayName("카드를 받는다.")
    @Test
    void d() {
        List<Card> cards = List.of(new Card("heart", "2"));
        var dealer = new Dealer(cards);
        dealer.addCard(new Card("heart", "K"));

        assertThat(dealer.getScore()).isEqualTo(12);
    }

    @ParameterizedTest(name = "점수가 16을 초과하는지 확인한다")
    @CsvSource({"diamond,8,heart,9,true", "diamond,10,spade,6,false"})
    void test_over_16(String face1, String letter1, String face2, String letter2, boolean isOverSixteen) {
        var dealer = new Dealer(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2)));

        assertThat(dealer.isOverSixteen()).isEqualTo(isOverSixteen);
    }

    @Test
    @DisplayName("점수가 16 이하이면 카드 1장을 뽑는다")
    void test_draw_16() {
        var dealer = new Dealer(List.of(
                new Card("heart", "10"),
                new Card("heart", "6")));
        dealer.drawCardIfNecessary(new Deck());

        assertThat(dealer.getScore()).isGreaterThan(16);
    }

    @Test
    @DisplayName("점수가 16을 초과하면 카드를 뽑지 않는다")
    void test_not_draw_16() {
        var dealer = new Dealer(List.of(
                new Card("heart", "10"),
                new Card("heart", "7")));
        dealer.drawCardIfNecessary(new Deck());

        assertThat(dealer.getScore()).isEqualTo(17);
    }

}