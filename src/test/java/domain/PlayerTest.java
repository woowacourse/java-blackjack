package domain;

import static domain.Face.SPADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest extends AbstractTestFixture {

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"8,K,18", "10,5,15"})
    void a(String letter1, String letter2, int score) {
        var player = new Player("조이", createCards(letter1, letter2));

        assertThat(player.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "A는 무조건 플레이어에게 유리하게 계산한다")
    @CsvSource({"J,10,21", "7,3,21", "A,A,13"})
    void b(String letter1, String letter2, int score) {
        var player = new Player("조이", createCards(letter1, letter2, "A"));

        assertThat(player.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "점수가 21을 초과하는지 확인한다")
    @CsvSource({"8,K,true", "10,5,false"})
    void c(String letter1, String letter2, boolean isBusted) {
        var player = new Player("조이", createCards(letter1, letter2, "5"));

        assertThat(player.isBusted()).isEqualTo(isBusted);
    }

    @DisplayName("카드를 받는다.")
    @Test
    void d() {
        List<Card> cards = createCards("2");
        var player = new Player("조이", cards);
        player.addCard(new Card(SPADE, letterFrom("K")));

        assertThat(player.getScore()).isEqualTo(12);
    }

}