package blackjack.domain.card;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CardPatternTest {
    @ParameterizedTest
    @EnumSource(CardPattern.class)
    void 카드패턴은_스페이드_하트_다이아몬드_클로바_4종류이다(
            CardPattern cardPattern
    ) {
        assertTrue(cardPattern == CardPattern.SPADE ||
                cardPattern == CardPattern.HEART ||
                cardPattern == CardPattern.DIAMOND ||
                cardPattern == CardPattern.CLUB);
    }

}
