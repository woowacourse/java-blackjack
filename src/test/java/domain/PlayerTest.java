package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static domain.Face.SPADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"DIAMOND,8,SPADE,K,18", "DIAMOND,10,SPADE,5,15"})
    void a(Face face1, String letter1, Face face2, String letter2, int score) {
        var player = new Player("조이", List.of(
                new Card(face1, letter1),
                new Card(face2, letter2)));

        assertThat(player.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "A는 무조건 플레이어에게 유리하게 계산한다")
    @CsvSource({"DIAMOND,J,SPADE,10,21", "DIAMOND,7,SPADE,3,21", "DIAMOND,A,SPADE,A,13"})
    void b(Face face1, String letter1, Face face2, String letter2, int score) {
        var player = new Player("조이", List.of(
                new Card(face1, letter1),
                new Card(face2, letter2),
                new Card(SPADE, "A")));

        assertThat(player.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "점수가 21을 초과하는지 확인한다")
    @CsvSource({"DIAMOND,8,SPADE,K,true", "DIAMOND,10,SPADE,5,false"})
    void c(Face face1, String letter1, Face face2, String letter2, boolean isBusted) {
        var player = new Player("조이", List.of(
                new Card(face1, letter1),
                new Card(face2, letter2),
                new Card(SPADE, "5")));

        assertThat(player.isBusted()).isEqualTo(isBusted);
    }

    @DisplayName("카드를 받는다.")
    @Test
    void d() {
        List<Card> cards = new ArrayList<>(List.of(new Card(SPADE, "2")));
        var player = new Player("조이", cards);
        player.addCard(new Card(SPADE, "K"));

        assertThat(player.getScore()).isEqualTo(12);
    }

}