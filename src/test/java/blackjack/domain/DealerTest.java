package blackjack.domain;

import blackjack.domain.CardMachine;
import blackjack.domain.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    private Card twoSpade;
    private Card threeSpade;
    private Card queenSpade;

    private CardMachine cardMachine = new CardMachine();
    private Dealer dealer;

    @BeforeEach
    void before() {
        dealer = new Dealer(cardMachine.giveInitCard());
        twoSpade = new Card(CardNumber.TWO, CardShape.SPADE);
        threeSpade = new Card(CardNumber.THREE, CardShape.SPADE);
        queenSpade = new Card(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("딜러의 카드 총 합이 16이하일 경우 True 를 반환하는지 확인한다.")
    @Test
    void isGiven_true() {
        List<Card> cards = new ArrayList<>(List.of(twoSpade, threeSpade));

        Dealer dealer = new Dealer(cards);

        final boolean given = dealer.isReceived();
        assertThat(given).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 17 이상일 경우 False 를 반환하는지 확인한다.")
    @Test
    void isGiven_false() {
        List<Card> cards = new ArrayList<>(List.of(twoSpade, threeSpade, queenSpade, threeSpade));

        Dealer dealer = new Dealer(cards);

        final boolean given = dealer.isReceived();
        assertThat(given).isFalse();
    }
}
