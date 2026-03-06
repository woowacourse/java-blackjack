package fixture;

import java.util.List;
import java.util.stream.Stream;
import model.Card;
import model.CardShape;
import model.CardValue;
import org.junit.jupiter.params.provider.Arguments;

public class PlayerTestFixture {

    public static Stream<Arguments> 플레이어별_카드목록_및_점수_제공() {
        return Stream.of(
                // Ace 없이 숫자 카드로만 구성 (기본 합산)
                Arguments.of(
                        List.of(new Card(CardShape.HEART, CardValue.EIGHT),
                                new Card(CardShape.DIAMOND, CardValue.NINE)),
                        17
                ),
                // Ace를 11로 계산하여 정확히 21이 되는 경우 (Blackjack 혹은 21)
                Arguments.of(
                        List.of(new Card(CardShape.SPADE, CardValue.ACE),
                                new Card(CardShape.CLOVER, CardValue.KING)),
                        21
                ),
                // Ace를 11로 하면 21을 넘기 때문에 1로 계산해야 하는 경우 (11 + 2 + 9 = 22(X) -> 1 + 2 + 9 = 12(O))
                Arguments.of(
                        List.of(new Card(CardShape.SPADE, CardValue.ACE),
                                new Card(CardShape.CLOVER, CardValue.TWO),
                                new Card(CardShape.HEART, CardValue.NINE)),
                        12
                ),
                // Ace가 2장일 때, 하나만 11로 계산해야 21에 가장 가까운 경우 (11 + 1 + 8 = 20)
                Arguments.of(
                        List.of(new Card(CardShape.SPADE, CardValue.ACE),
                                new Card(CardShape.HEART, CardValue.ACE),
                                new Card(CardShape.DIAMOND, CardValue.EIGHT)),
                        20
                ),
                // Ace가 많아도 모두 1로 계산해야 버스트를 면하는 경우 (1+1+1+10 = 13)
                Arguments.of(
                        List.of(new Card(CardShape.SPADE, CardValue.ACE),
                                new Card(CardShape.HEART, CardValue.ACE),
                                new Card(CardShape.DIAMOND, CardValue.ACE),
                                new Card(CardShape.CLOVER, CardValue.KING)),
                        13
                )
        );
    }
}