package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.carddeck.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    Dealer dealer;
    CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        cardDeck = CardDeck.newShuffledDeck();
    }

    @Test
    @DisplayName("딜러는 총점수 17이상일시 카드 뽑기를 멈춘다.")
    void testStopDrawDealerWhenTotalScoreOverSeventeen() {
        for (int i = 0; i < 999999; i++) {
            while (!dealer.isOverLimitScore()) {
                dealer.addCard(cardDeck.draw());
            }
            assertThat(dealer.getScore()).isGreaterThanOrEqualTo(17);
        }
    }
}
