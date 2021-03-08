package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("카드 합이 17이상인지 확인")
    void isStayTest() {
        dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.EIGHT, Shape.CLOVER));
        dealer.addCard(new Card(CardNumber.ACE, Shape.CLOVER));
        assertTrue(dealer.isStay());
    }

    @Test
    @DisplayName("딜러 최종 승패 확인")
    void dealerGameResultTest() {
        Map<String, String> playersGameResult = new HashMap<>();
        playersGameResult.put("bepoz", "승");
        playersGameResult.put("wannte", "승");
        assertThat(dealer.getGameResult(playersGameResult)).isEqualTo("2승 0패");
    }
}