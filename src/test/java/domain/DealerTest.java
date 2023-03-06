package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static domain.Face.HEART;
import static domain.Face.SPADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"DIAMOND,8,HEART,K,18", "DIAMOND,10,DIAMOND,5,15"})
    void a(Face face1, String letter1, Face face2, String letter2, int score) {
        var dealer = new Dealer(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2)));

        assertThat(dealer.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "A는 무조건 딜러에게 유리하게 계산한다")
    @CsvSource({"DIAMOND,J,HEART,10,21", "DIAMOND,7,DIAMOND,3,21", "DIAMOND,A,DIAMOND,A,13"})
    void b(Face face1, String letter1, Face face2, String letter2, int score) {
        var dealer = new Dealer(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2),
                new Card(SPADE, "A")));

        assertThat(dealer.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "점수가 21을 초과하는지 확인한다")
    @CsvSource({"DIAMOND,8,HEART,K,true", "DIAMOND,10,DIAMOND,5,false"})
    void c(Face face1, String letter1, Face face2, String letter2, boolean isBusted) {
        var dealer = new Dealer(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2),
                new Card(SPADE, "5")));

        assertThat(dealer.isBusted()).isEqualTo(isBusted);
    }

    @DisplayName("카드를 받는다.")
    @Test
    void d() {
        List<Card> cards = List.of(new Card(HEART, "2"));
        var dealer = new Dealer(cards);
        dealer.addCard(new Card(HEART, "K"));

        assertThat(dealer.getScore()).isEqualTo(12);
    }

    @ParameterizedTest(name = "점수가 16 초과인지 확인하여 hit이 가능한지 판단한다")
    @CsvSource({"DIAMOND,8,HEART,9,false", "DIAMOND,10,DIAMOND,6,true"})
    void test_over_16(Face face1, String letter1, Face face2, String letter2, boolean isOverSixteen) {
        var dealer = new Dealer(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2)));

        assertThat(dealer.canHit()).isEqualTo(isOverSixteen);
    }

}