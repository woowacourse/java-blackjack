package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"diamond,8,heart,K,18", "diamond,10,spade,5,15"})
    void a(String face1, String letter1, String face2, String letter2, int score) {
        var player = new Player(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2)));

        assertThat(player.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "A는 무조건 플레이어에게 유리하게 계산한다")
    @CsvSource({"diamond,J,heart,10,21", "diamond,7,spade,3,21", "diamond,A,spade,A,13"})
    void b(String face1, String letter1, String face2, String letter2, int score) {
        var player = new Player(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2),
                new Card("spade", "A")));

        assertThat(player.getScore()).isEqualTo(score);
    }

    @ParameterizedTest(name = "점수가 21을 초과하는지 확인한다")
    @CsvSource({"diamond,8,heart,K,true", "diamond,10,spade,5,false"})
    void c(String face1, String letter1, String face2, String letter2, boolean isBusted) {
        var player = new Player(List.of(
                new Card(face1, letter1),
                new Card(face2, letter2),
                new Card("spade", "5")));

        assertThat(player.isBusted()).isEqualTo(isBusted);
    }

}