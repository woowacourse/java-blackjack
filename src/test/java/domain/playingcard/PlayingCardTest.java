package domain.playingcard;

import domain.playingcard.PlayingCard;
import domain.playingcard.PlayingCardShape;
import domain.playingcard.PlayingCardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.playingcard.PlayingCardShape.HEART;
import static domain.playingcard.PlayingCardValue.DEFAULT_ACE;
import static domain.playingcard.PlayingCardValue.FIVE;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayingCardTest {

    @DisplayName("카드의 모양과 숫자를 입력하면 인스턴스가 생성된다.")
    @Test
    void createPlayingCardTest() {
        // Given
        PlayingCardShape playingCardShape = HEART;
        PlayingCardValue playingCardValue = FIVE;

        // When
        PlayingCard playingCard = new PlayingCard(playingCardShape, playingCardValue);

        // Then
        assertThat(playingCard).isNotNull();
    }

    @DisplayName("카드의 숫자만큼 더해서 반환한다.")
    @MethodSource("addValueParameters")
    @ParameterizedTest(name = "[{index}] \"{0}\" => {1}")
    void addValueTest(PlayingCard playingCard, int inputNumber, int expect) {
        // When
        int result = playingCard.addValue(inputNumber);

        // Then
        assertThat(result).isEqualTo(expect);
    }

    private static Stream<Arguments> addValueParameters() {
        return Stream.of(
                Arguments.of(new PlayingCard(HEART, DEFAULT_ACE), 4, 15),
                Arguments.of(new PlayingCard(HEART, DEFAULT_ACE), 18, 19),
                Arguments.of(new PlayingCard(HEART, FIVE), 15, 20)
        );
    }
}
