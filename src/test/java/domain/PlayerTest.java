package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static domain.Suit.HEART;
import static domain.Suit.SPADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"DIAMOND,8,SPADE,K,18", "DIAMOND,10,SPADE,5,15"})
    void a(Suit suit1, String letter1, Suit suit2, String letter2, int score) {
        var player = new Player("조이", List.of(
                new Card(suit1, letter1),
                new Card(suit2, letter2)));

        assertThat(player.score()).isEqualTo(new Score(score));
    }

    @ParameterizedTest(name = "A는 무조건 플레이어에게 유리하게 계산한다")
    @CsvSource({"DIAMOND,J,SPADE,10,21", "DIAMOND,7,SPADE,3,21", "DIAMOND,A,SPADE,A,13"})
    void b(Suit suit1, String letter1, Suit suit2, String letter2, int score) {
        var player = new Player("조이", List.of(
                new Card(suit1, letter1),
                new Card(suit2, letter2),
                new Card(SPADE, "A")));

        assertThat(player.score()).isEqualTo(new Score(score));
    }

    @ParameterizedTest(name = "점수가 21을 초과하는지 확인한다")
    @CsvSource({"DIAMOND,8,SPADE,K,BUST", "DIAMOND,10,SPADE,5,PLAYING"})
    void c(Suit suit1, String letter1, Suit suit2, String letter2, Status status) {
        var player = new Player("조이");
        player.addCard(new Card(HEART, "5"));
        player.addCard(new Card(suit1, letter1));
        player.addCard(new Card(suit2, letter2));

        assertThat(player.status()).isEqualTo(status);
    }

    @DisplayName("카드를 받는다.")
    @Test
    void d() {
        List<Card> cards = new ArrayList<>(List.of(new Card(SPADE, "2")));
        var player = new Player("조이", cards);
        player.addCard(new Card(SPADE, "K"));

        assertThat(player.score()).isEqualTo(new Score(12));
    }

    @ParameterizedTest(name = "점수가 21 초과인지 확인하여 hit이 가능한지 판단한다")
    @CsvSource({"DIAMOND,10,HEART,A,false", "DIAMOND,10,DIAMOND,6,true"})
    void test_over_21(Suit suit1, String denomination1, Suit suit2, String denomination2, boolean canHit) {
        var player = new Player("조이", List.of(
                new Card(suit1, denomination1),
                new Card(suit2, denomination2)));

        assertThat(player.canHit()).isEqualTo(canHit);
    }

}