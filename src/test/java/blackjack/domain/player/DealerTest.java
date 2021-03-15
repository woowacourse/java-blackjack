package blackjack.domain.player;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    Dealer dealer;

    @BeforeEach
    void beforeEach() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("카드 초기화 테스트")
    void testInitializeCards() {
        //given
        Deck deck = new Deck();

        //when
        dealer.initializeCards(deck);

        //then
        assertThat(dealer.cards().countCards()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러 수익률 계산 테스트")
    void testCalculateProfit() {
        //given
        Gambler gambler = new Gambler("pobi", new BettingMoney(10000));

        //when
        dealer.calculateProfit(gambler);

        //then
        assertThat(dealer.money()).isEqualTo(new BettingMoney(-10000));
    }

    @Test
    @DisplayName("이름을 통해 객체 구분 테스트")
    void testIsSameName() {
        //given
        Gambler gambler1 = new Gambler("딜러", new BettingMoney(10000));
        Gambler gambler2 = new Gambler("jason", new BettingMoney(20000));

        //when
        boolean test1 = dealer.isSameName(gambler1);
        boolean test2 = dealer.isSameName(gambler2);

        //then
        assertThat(test1).isTrue();
        assertThat(test2).isFalse();
    }
}
