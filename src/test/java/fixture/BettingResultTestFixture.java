package fixture;

import java.util.List;
import java.util.stream.Stream;
import model.Card;
import model.CardShape;
import model.CardValue;
import org.junit.jupiter.params.provider.Arguments;

public class BettingResultTestFixture {

    public static Stream<Arguments> 플레이어의_상황별_베팅금액_정보제공() {
        return Stream.of(
                // 1. 플레이어만 블랙잭 (딜러는 블랙잭이 아닌 그 외의 상황 모두)
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.ACE)),
                        List.of(card(CardValue.SEVEN), card(CardValue.TEN)),
                        1000, 500, -500
                ),
                // 2. 플레이어와 딜러 둘 다 블랙잭
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.ACE)),
                        List.of(card(CardValue.TEN), card(CardValue.ACE)),
                        1000, 0, 0
                ),
                // 3. 플레이어 버스트(점수 합 21 초과, 이때 딜러의 카드는 상관이 없다.)
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.NINE), card(CardValue.EIGHT)),
                        List.of(card(CardValue.SEVEN), card(CardValue.ACE)),
                        1000, -1000, 1000
                ),
                // 4. 플레이어 딜러 모두 21점을 넘지 않고, 딜러가 플레이어보다 점수가 높은 경우
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.NINE)),
                        List.of(card(CardValue.JACK), card(CardValue.QUEEN)),
                        1000, -1000, 1000
                ),
                // 5. 플레이어 딜러 모두 21점을 넘지 않고, 플레이어가 딜러보다 점수가 높은 경우
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.NINE)),
                        List.of(card(CardValue.QUEEN), card(CardValue.EIGHT)),
                        1000, 1000, -1000
                ),
                // 6. 플레이어가 카드를 한 장 더 받아서 21이 된 경우 -> 일반 승리 판정
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.NINE), card(CardValue.TWO)),
                        List.of(card(CardValue.QUEEN), card(CardValue.EIGHT)),
                        1000, 1000, -1000
                ),
                // 7. 플레이어가 카드를 한 장 더 받아서 21이 되었지만, 딜러가 블랙잭인 경우
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.NINE), card(CardValue.TWO)),
                        List.of(card(CardValue.QUEEN), card(CardValue.ACE)),
                        1000, -1000, 1000
                ),
                // 8. 플레이어가 카드를 한 장 더 받아서 21이 되었고, 딜러도 카드를 더 뽑은 상황에서 카드 합이 21인 경우
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.NINE), card(CardValue.TWO)),
                        List.of(card(CardValue.EIGHT), card(CardValue.THREE), card(CardValue.QUEEN)),
                        1000, 0, 0
                )
        );
    }

    private static Card card(CardValue value) {
        return new Card(CardShape.SPADE, value);
    }
}
