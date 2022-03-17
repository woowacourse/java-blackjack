package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.bet.Result;
import blackjack.model.trumpcard.TrumpCard;
import blackjack.model.trumpcard.TrumpNumber;
import blackjack.model.trumpcard.TrumpSymbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    private Dealer dealer;
    private Entry entry;

    @BeforeEach
    void initializeDealer() {
        dealer = new Dealer();
        dealer.addCard(new TrumpCard(TrumpNumber.TEN, TrumpSymbol.CLOVER));

        entry = new Entry("포키");
        entry.addCard(new TrumpCard(TrumpNumber.TEN, TrumpSymbol.DIAMOND));
    }

    @DisplayName("딜러의 점수가 16점이면 딜러는 hit할 수 있다")
    @Test
    void canHit_true() {
        dealer.addCard(new TrumpCard(TrumpNumber.SIX, TrumpSymbol.HEART));

        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 점수가 17점이면 딜러는 hit할 수 없다")
    @Test
    void canHit_false() {
        dealer.addCard(new TrumpCard(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));

        assertThat(dealer.canHit()).isFalse();
    }

    @DisplayName("참가자가 bust이면 참가자는 패배한다")
    @Test
    void compareWith_entry_bust() {
        dealer.addCard(new TrumpCard(TrumpNumber.TEN, TrumpSymbol.CLOVER));
        dealer.addCard(new TrumpCard(TrumpNumber.TWO, TrumpSymbol.HEART));

        entry.addCard(new TrumpCard(TrumpNumber.TWO, TrumpSymbol.HEART));
        entry.addCard(new TrumpCard(TrumpNumber.TEN, TrumpSymbol.SPADE));

        assertThat(dealer.compareWith(entry)).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러만 bust이면 참가자가 승리한다")
    @Test
    void compareWith_dealer_bust() {
        dealer.addCard(new TrumpCard(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        dealer.addCard(new TrumpCard(TrumpNumber.THREE, TrumpSymbol.HEART));

        entry.addCard(new TrumpCard(TrumpNumber.TWO, TrumpSymbol.HEART));
        entry.addCard(new TrumpCard(TrumpNumber.ACE, TrumpSymbol.SPADE));

        assertThat(dealer.compareWith(entry)).isEqualTo(Result.WIN);
    }

    @DisplayName("둘다 카드가 2장이고 21점이면 무승부다")
    @Test
    void compareWith_both_blackjack() {
        dealer.addCard(new TrumpCard(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        entry.addCard(new TrumpCard(TrumpNumber.ACE, TrumpSymbol.SPADE));

        assertThat(dealer.compareWith(entry)).isEqualTo(Result.TIE);
    }

    @DisplayName("참가자만 카드가 2장이고 21점이면 참가자는 Blackjack이다")
    @Test
    void compareWith_entry_blackjack() {
        dealer.addCard(new TrumpCard(TrumpNumber.NINE, TrumpSymbol.CLOVER));

        entry.addCard(new TrumpCard(TrumpNumber.ACE, TrumpSymbol.SPADE));

        assertThat(dealer.compareWith(entry)).isEqualTo(Result.BLACKJACK);
    }

    @DisplayName("둘다 bust가 아니고 동점이면 무승부다")
    @Test
    void compareWith_same_score() {
        dealer.addCard(new TrumpCard(TrumpNumber.NINE, TrumpSymbol.CLOVER));

        entry.addCard(new TrumpCard(TrumpNumber.TWO, TrumpSymbol.HEART));
        entry.addCard(new TrumpCard(TrumpNumber.SEVEN, TrumpSymbol.SPADE));

        assertThat(dealer.compareWith(entry)).isEqualTo(Result.TIE);
    }

    @DisplayName("딜러가 19, 엔트리가 18이면 딜러가 승리한다")
    @Test
    void compareWith_dealer_19_entry_20() {
        dealer.addCard(new TrumpCard(TrumpNumber.NINE, TrumpSymbol.CLOVER));

        entry.addCard(new TrumpCard(TrumpNumber.TWO, TrumpSymbol.HEART));
        entry.addCard(new TrumpCard(TrumpNumber.SIX, TrumpSymbol.SPADE));

        assertThat(dealer.compareWith(entry)).isEqualTo(Result.LOSE);
    }
}
