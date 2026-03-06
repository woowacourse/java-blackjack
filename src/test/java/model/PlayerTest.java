package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @ParameterizedTest
    @MethodSource("fixture.PlayerTestFixture#플레이어별_카드목록_및_점수_제공")
    void 블랙잭_참여자는_자신의_카드_정보로_현재_점수를_계산할_수_있다(List<Card> cards, int expectedScore) {
        // given
        Player pobi = new Player("pobi");
        cards.forEach(pobi::addCard);

        // when
        int score = pobi.calculateTotalScore();

        // then
        assertThat(score).isEqualTo(expectedScore);
    }
}