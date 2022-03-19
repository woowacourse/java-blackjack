package blackjack.domain;

import blackjack.MockDeck;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DealerTest {

    @Nested
    @DisplayName("isDrawable은")
    class IsDrawable {

        @ParameterizedTest
        @CsvSource(value = {"SIX,true", "SEVEN,false"})
        @DisplayName("패의 합이 17이 넘는지 유무를 알려준다.")
        void returnFalse(CardNumber cardNumber, boolean expected) {
            Dealer dealer = new Dealer();
            MockDeck mockDeck = new MockDeck(
                    List.of(Card.of(CardPattern.CLOVER, CardNumber.TEN), Card.of(CardPattern.CLOVER, cardNumber)));
            dealer.drawCard(mockDeck.draw());
            dealer.drawCard(mockDeck.draw());

            Assertions.assertThat(dealer.isDrawable()).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("Compete는")
    class Compete {

        @ParameterizedTest
        @CsvSource(value = {"SIX,WIN", "FIVE,DRAW", "FOUR,LOSE"})
        @DisplayName("플레이어와 점수를 비교하여 승부 결과를 반환한다.")
        void returnResult(CardNumber cardNumber, Score expected) {
            Player player = new Player("player", 0);
            Dealer dealer = new Dealer();
            player.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.FIVE));
            dealer.drawCard(Card.of(CardPattern.DIAMOND, cardNumber));
            Assertions.assertThat(dealer.compete(player)).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource(value = {"ACE,LOSE", "THREE,WIN"})
        @DisplayName("딜러의 카드가 버스트일 때 승부 결과를 반환한다.")
        void returnResultWithDealerBust(CardNumber cardNumber, Score expected) {
            Player player = new Player("player", 0);
            Dealer dealer = new Dealer();
            // 딜러가 버스트
            dealer.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
            dealer.drawCard(Card.of(CardPattern.CLOVER, CardNumber.TEN));
            dealer.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));

            player.drawCard(Card.of(CardPattern.SPADE, CardNumber.TEN));
            player.drawCard(Card.of(CardPattern.SPADE, CardNumber.NINE));
            player.drawCard(Card.of(CardPattern.SPADE, cardNumber));

            Assertions.assertThat(dealer.compete(player)).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource(value = {"ACE,DRAW", "JACK,WIN"})
        @DisplayName("딜러가 블랙잭일 때 승부 결과를 반환한다.")
        void returnResultWithDealerBust2(CardNumber cardNumber, Score expected) {
            Player player = new Player("player", 0);
            Dealer dealer = new Dealer();
            dealer.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
            dealer.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.ACE));

            player.drawCard(Card.of(CardPattern.SPADE, CardNumber.TEN));
            player.drawCard(Card.of(CardPattern.SPADE, cardNumber));

            Assertions.assertThat(dealer.compete(player)).isEqualTo(expected);
        }
    }
}
