package blackjackgame.domain.blackjack;

import static blackjackgame.domain.blackjack.CardResult.BLACKJACK;
import static blackjackgame.domain.blackjack.CardResult.BUST;
import static blackjackgame.domain.blackjack.CardResult.NORMAL;
import static blackjackgame.domain.card.CardName.ACE;
import static blackjackgame.domain.card.CardName.JACK;
import static blackjackgame.domain.card.CardName.KING;
import static blackjackgame.domain.card.CardName.TWO;
import static blackjackgame.domain.card.CardType.SPADE;

import blackjackgame.domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardResultCalculatorTest {

    @Test
    @DisplayName("손패를 추가로 받지 않았으면서 손패의 합이 21이면 결과가 BLACKJACK이다.")
    void cardResultBlackjackTest() {
        HoldingCards holdingCards = HoldingCards.of(
                new Card(JACK, SPADE), new Card(ACE, SPADE)
        );
        CardResult cardResult = CardResultCalculator.calculate(holdingCards);

        Assertions.assertThat(cardResult).isEqualTo(BLACKJACK);
    }

    @Test
    @DisplayName("손패의 합이 21을 넘기면 결과가 BUST이다.")
    void cardResultBustTest() {
        HoldingCards holdingCards = HoldingCards.of(
                new Card(JACK, SPADE), new Card(KING, SPADE), new Card(TWO, SPADE)
        );
        CardResult cardResult = CardResultCalculator.calculate(holdingCards);

        Assertions.assertThat(cardResult).isEqualTo(BUST);
    }

    @Test
    @DisplayName("손패의 합이 21 이하면 결과가 NORMAL이다.")
    void cardResultNormalTest() {
        HoldingCards holdingCards = HoldingCards.of(
                new Card(JACK, SPADE), new Card(KING, SPADE)
        );
        CardResult cardResult = CardResultCalculator.calculate(holdingCards);

        Assertions.assertThat(cardResult).isEqualTo(NORMAL);
    }
}
