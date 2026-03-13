package fixture;

import static model.card.CardValue.ACE;
import static model.card.CardValue.EIGHT;
import static model.card.CardValue.FIVE;
import static model.card.CardValue.JACK;
import static model.card.CardValue.KING;
import static model.card.CardValue.NINE;
import static model.card.CardValue.SEVEN;
import static model.card.CardValue.SIX;
import static model.card.CardValue.TEN;
import static model.card.CardValue.THREE;
import static model.card.CardValue.TWO;
import static model.judgement.ResultStatus.BLACKJACK;
import static model.judgement.ResultStatus.DRAW;
import static model.judgement.ResultStatus.LOSE;
import static model.judgement.ResultStatus.WIN;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.CardShape;
import model.card.CardValue;
import org.junit.jupiter.params.provider.Arguments;

public class PlayerResultTestFixture {

    public static Stream<Arguments> 플레이어의_상황별_승_패_정보제공() {
        return Stream.of(
                // 1. 플레이어 버스트 (딜러 점수 상관없이 패배)
                Arguments.of(
                        List.of(card(TEN), card(JACK), card(FIVE)), // 25점
                        List.of(card(TWO), card(THREE)), // 5점
                        LOSE
                ),
                // 2. 딜러 버스트, 플레이어는 낫 버스트 (승리)
                Arguments.of(
                        List.of(card(TEN), card(EIGHT)), // 18점
                        List.of(card(TEN), card(FIVE), card(SEVEN)), // 22점
                        WIN
                ),
                // 3. 둘 다 버스트가 아닐 때 점수 비교 (플레이어가 높은 경우)
                Arguments.of(
                        List.of(card(ACE), card(NINE)), // 20점
                        List.of(card(TEN), card(EIGHT)), // 18점
                        WIN
                ),
                // 4. 점수가 같은 경우 (무승부)
                Arguments.of(
                        List.of(card(TEN), card(SEVEN)), // 17점
                        List.of(card(EIGHT), card(NINE)), // 17점
                        DRAW
                ),
                // 5. 플레이어가 낮은 경우 (패배)
                Arguments.of(
                        List.of(card(FIVE), card(SEVEN)), // 12점
                        List.of(card(TEN), card(SIX)), // 16점
                        LOSE
                ),
                // 6. 플레이어와 딜러 모두 버스트 (패배)
                Arguments.of(
                        List.of(card(TEN), card(JACK), card(FIVE)), // 25점
                        List.of(card(TEN), card(TEN), card(THREE)), // 23점
                        LOSE
                ),
                // 7. 플레이어만 블랙잭인 경우
                Arguments.of(
                        List.of(card(ACE), card(KING)),             // 블랙잭
                        List.of(card(TEN), card(NINE)),             // 19점
                        BLACKJACK
                ),
                // 8. 플레이어와 딜러 모두 블랙잭인 경우
                Arguments.of(
                        List.of(card(ACE), card(KING)),             // 블랙잭
                        List.of(card(ACE), card(KING)),             // 블랙잭
                        DRAW
                ),
                // 9. 딜러만 블랙잭인 경우
                Arguments.of(
                        List.of(card(TEN), card(FIVE), card(SIX)),   // 21점 (3장)
                        List.of(card(ACE), card(KING)),             // 블랙잭
                        LOSE
                )
        );
    }

    private static Card card(CardValue value) {
        return new Card(CardShape.SPADE, value);
    }
}
