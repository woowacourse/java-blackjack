package fixture;

import java.util.List;
import java.util.stream.Stream;
import model.Card;
import model.CardShape;
import model.CardValue;
import model.GameStatus;
import org.junit.jupiter.params.provider.Arguments;

public class PlayerResultTestFixture {

    public static Stream<Arguments> 플레이어의_상황별_승_패_정보제공() {
        return Stream.of(
                // 1. 플레이어 버스트 (딜러 점수 상관없이 패배)
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.JACK), card(CardValue.FIVE)), // 25점
                        List.of(card(CardValue.TWO), card(CardValue.THREE)), // 5점
                        GameStatus.LOSE
                ),
                // 2. 딜러 버스트, 플레이어는 낫 버스트 (승리)
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.EIGHT)), // 18점
                        List.of(card(CardValue.TEN), card(CardValue.FIVE), card(CardValue.SEVEN)), // 22점
                        GameStatus.WIN
                ),
                // 3. 둘 다 버스트가 아닐 때 점수 비교 (플레이어가 높은 경우)
                Arguments.of(
                        List.of(card(CardValue.ACE), card(CardValue.NINE)), // 20점
                        List.of(card(CardValue.TEN), card(CardValue.EIGHT)), // 18점
                        GameStatus.WIN
                ),
                // 4. 점수가 같은 경우 (무승부)
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.SEVEN)), // 17점
                        List.of(card(CardValue.EIGHT), card(CardValue.NINE)), // 17점
                        GameStatus.DRAW
                ),
                // 5. 플레이어가 낮은 경우 (패배)
                Arguments.of(
                        List.of(card(CardValue.FIVE), card(CardValue.SEVEN)), // 12점
                        List.of(card(CardValue.TEN), card(CardValue.SIX)), // 16점
                        GameStatus.LOSE
                )
        );
    }

    private static Card card(CardValue value) {
        return new Card(CardShape.SPADE, value);
    }
}
