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
}