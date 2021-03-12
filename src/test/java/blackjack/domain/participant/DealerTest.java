package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DealerTest {

    Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        List<Card> cards = Collections.singletonList(new Card(Pattern.CLOVER, Number.TEN));
        dealer.receiveFirstHand(new ArrayList<>(cards));
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
        dealer.receiveCard(new Card(Pattern.DIAMOND, Number.JACK));
        for (int i = 0; i < 999999; i++) {
            while (!dealer.isOverLimitScore()) {
                dealer.receiveCard(dealer.drawCard());
            }
            assertThat(dealer.getTotalScore().isDealerStateStay()).isTrue();
        }
    }
}
