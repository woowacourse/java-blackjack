package blackjack.domain.participant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DealerTest {

    Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러는 52장의 카드가 있는 카드 덱을 가진다.")
    void testDealerTakeCardDeck() {
        for (int i = 0; i < 52; i++) {
            dealer.drawCard();
        }
        assertThatThrownBy(() -> {
            dealer.drawCard();
        }).isInstanceOf(NoSuchElementException.class).hasMessage("더이상 뽑을 카드가 없습니다.");
    }

    @Test
    @DisplayName("딜러는 총점수 17이상일시 카드 뽑기를 멈춘다.")
    void testStopDrawDealerWhenTotalScoreOverSeventeen() {
        for (int i = 0; i < 999999; i++) {
            while (!dealer.isOverLimitScore()) {
                dealer.receiveCard(dealer.drawCard());
            }
            assertThat(dealer.getTotalScore().isDealerStateStay()).isTrue();
        }
    }
}
