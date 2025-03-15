package blackjack.domain.gambler;

import static blackjack.domain.card.CardType.EIGHT;
import static blackjack.domain.card.CardType.TEN;
import static blackjack.domain.fixture.GamblerFixture.createDealer;
import static blackjack.domain.fixture.GamblerFixture.createDealerWithCards;
import static blackjack.domain.fixture.GamblerFixture.createPlayerWithCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DealerTest {
    @DisplayName("처음 카드를 받은 후 딜러는 하나의 카드만 오픈한다")
    @Test
    void getInitialCardsTest() {
        // given
        Dealer dealer = createDealer();
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);
        dealer.addCard(card1);
        dealer.addCard(card2);

        // when
        List<Card> result = dealer.getInitialCards();

        // then
        assertAll(
            () -> assertThat(result).hasSize(1),
            () -> assertThat(result.getFirst()).isEqualTo(card1)
        );
    }

    @DisplayName("주어진_Player_와의_점수_차이를_반환한다")
    @CsvSource(value = {"TEN:TEN:-2", "TEN:EIGHT:0", "TEN:SIX:2"}, delimiterString = ":")
    @ParameterizedTest
    void calculateScoreDifference(CardType firstType, CardType secondType, int expected) {
        // given
        Dealer dealer = createDealerWithCards(TEN, EIGHT);
        Player player = createPlayerWithCards(new Name("레오"), firstType, secondType);

        // when
        int result = dealer.calculateScoreDifference(player);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
