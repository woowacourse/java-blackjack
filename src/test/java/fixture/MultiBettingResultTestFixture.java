package fixture;

import java.util.List;
import java.util.stream.Stream;
import model.BettingMoney;
import model.Card;
import model.CardShape;
import model.CardValue;
import org.junit.jupiter.params.provider.Arguments;

public class MultiBettingResultTestFixture {

    public static Stream<Arguments> 여러_플레이어_혼합_정산_정보제공() {
        return Stream.of(

                // 1. 딜러 일반 점수 기준
                // 플레이어 블랙잭 / 일반 승리 / 패배 / 무승부가 모두 섞인 케이스
                Arguments.of(
                        List.of(
                                new PlayerScenario("pobi",
                                        List.of(card(CardValue.ACE), card(CardValue.KING)),
                                        new BettingMoney(100),
                                        50),
                                new PlayerScenario("moving",
                                        List.of(card(CardValue.TEN), card(CardValue.NINE)),
                                        new BettingMoney(300),
                                        300),
                                new PlayerScenario("doodoom",
                                        List.of(card(CardValue.TEN), card(CardValue.SEVEN)),
                                        new BettingMoney(500),
                                        -500),
                                new PlayerScenario("jason",
                                        List.of(card(CardValue.TEN), card(CardValue.EIGHT)),
                                        new BettingMoney(700),
                                        0)
                        ),
                        List.of(card(CardValue.TEN), card(CardValue.EIGHT)),
                        150
                ),

                // 2. 딜러가 블랙잭인 경우
                // 일반 플레이어는 모두 패배하고, 블랙잭인 플레이어(pobi) 무승부 처리되는지 확인
                Arguments.of(
                        List.of(
                                new PlayerScenario("pobi",
                                        List.of(card(CardValue.ACE), card(CardValue.KING)),
                                        new BettingMoney(100),
                                        0),
                                new PlayerScenario("moving",
                                        List.of(card(CardValue.TEN), card(CardValue.NINE)),
                                        new BettingMoney(300),
                                        -300),
                                new PlayerScenario("doodoom",
                                        List.of(card(CardValue.TEN), card(CardValue.EIGHT)),
                                        new BettingMoney(500),
                                        -500)
                        ),
                        List.of(card(CardValue.ACE), card(CardValue.QUEEN)),
                        800
                ),

                // 3. 딜러가 버스트한 경우
                // 버스트하지 않은 플레이어(pobi, moving)는 모두 승리하고, 이미 버스트한 플레이어들(doodoom, jason)은 패배하는지 확인
                Arguments.of(
                        List.of(
                                new PlayerScenario("pobi",
                                        List.of(card(CardValue.TEN), card(CardValue.SIX)),
                                        new BettingMoney(100),
                                        100),
                                new PlayerScenario("moving",
                                        List.of(card(CardValue.ACE), card(CardValue.TWO)),
                                        new BettingMoney(300),
                                        300),
                                new PlayerScenario("doodoom",
                                        List.of(card(CardValue.TEN), card(CardValue.EIGHT), card(CardValue.FOUR)),
                                        new BettingMoney(500),
                                        -500),
                                new PlayerScenario("jason",
                                        List.of(card(CardValue.TEN), card(CardValue.NINE), card(CardValue.FIVE)),
                                        new BettingMoney(700),
                                        -700)
                        ),
                        List.of(card(CardValue.TEN), card(CardValue.SIX), card(CardValue.SEVEN)),
                        800
                ),

                // 4. 딜러 일반 승리(20) 기준
                // 플레이어가 모두 패배하지 않고, 패배 / 무승부 / 블랙잭 승리하는 케이스
                Arguments.of(
                        List.of(
                                new PlayerScenario("pobi",
                                        List.of(card(CardValue.TEN), card(CardValue.NINE)),
                                        new BettingMoney(100),
                                        -100),
                                new PlayerScenario("moving",
                                        List.of(card(CardValue.TEN), card(CardValue.QUEEN)),
                                        new BettingMoney(300),
                                        0),
                                new PlayerScenario("doodoom",
                                        List.of(card(CardValue.ACE), card(CardValue.KING)),
                                        new BettingMoney(500),
                                        250)
                        ),
                        List.of(card(CardValue.KING), card(CardValue.QUEEN)),
                        -150
                ),

                // 5. 딜러가 낮은 점수(17)이고 플레이어 여러 명이 일반 승리하는 경우
                // moving은 패배, 이 외의 플레이어 모두 승리했을 때의 케이스
                Arguments.of(
                        List.of(
                                new PlayerScenario("pobi",
                                        List.of(card(CardValue.TEN), card(CardValue.EIGHT)),
                                        new BettingMoney(100),
                                        100),
                                new PlayerScenario("doodoom",
                                        List.of(card(CardValue.TEN), card(CardValue.NINE)),
                                        new BettingMoney(300),
                                        300),
                                new PlayerScenario("moving",
                                        List.of(card(CardValue.TEN), card(CardValue.SIX)),
                                        new BettingMoney(500),
                                        -500),
                                new PlayerScenario("jason",
                                        List.of(card(CardValue.NINE), card(CardValue.NINE)),
                                        new BettingMoney(700),
                                        700)
                        ),
                        List.of(card(CardValue.TEN), card(CardValue.SEVEN)),
                        -600
                )
        );
    }

    private static Card card(CardValue value) {
        return new Card(CardShape.SPADE, value);
    }

    public record PlayerScenario(
            String name,
            List<Card> cards,
            BettingMoney bettingMoney,
            long profit
    ) {
    }
}