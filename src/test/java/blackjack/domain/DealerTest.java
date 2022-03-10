package blackjack.domain;

import static blackjack.domain.CardsTestDataGenerator.*;
import static blackjack.domain.Denomination.*;
import static blackjack.domain.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class DealerTest {

    @DisplayName("카드 리스트가 주어지면 정상적으로 생성된다.")
    @Test
    void 딜러_생성_정상() {
        assertDoesNotThrow(() -> new Dealer(generateCards()));
    }

    @DisplayName("딜러의 점수가 16점 이하인 경우 카드를 받을 수 있다.")
    @Test
    void 카드_발급_가능() {
        List<Card> cards = generateTotalScoreNotMoreThan16Cards();
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.isDrawable()).isTrue();
    }

    @DisplayName("딜러의 점수가 17점을 초과한 경우 카드를 받을 수 없다.")
    @Test
    void 카드_발급_불가능() {
        List<Card> cards = generateTotalScoreGraterThan17Cards();
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.isDrawable()).isFalse();
    }

    @DisplayName("카드를 받아서 합칠 수 있다.")
    @Test
    void 카드_합침() {
        Dealer dealer = new Dealer(generateCards());
        Card card = Card.of(FIVE, SPADE);

        dealer.combine(card);

        assertThat(dealer.getCards().size()).isEqualTo(3);
    }

    @DisplayName("Cards가 주어지면 점수를 계산하면 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(CardsArgumentsProvider.class)
    void 딜러_카드_점수_계산(Cards cards, int totalScore) {
        Dealer dealer = new Dealer(cards.getValue());

        assertThat(dealer.getTotalScore()).isEqualTo(totalScore);
    }
}
