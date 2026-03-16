package domain.player;

import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 카드 값의 합이 드로우 임계치 미만일 때 true를 정상적으로 반환함을 테스트")
    void 딜러_카드_합_드로우_임계치_미만_true_반환_테스트() {
        //given
        Dealer dealer = new Dealer();

        //when
        dealer.addCard(new Card("9", "하트"));

        //then
        Assertions.assertThat(dealer.isBelowDrawThreshold()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러 카드 값의 합이 드로우 임계치와 일치할 때 true를 정상적으로 반환함을 테스트")
    void 딜러_카드_합_드로우_임계치_일치_true_반환_테스트() {
        //given
        Dealer dealer = new Dealer();

        //when
        dealer.addCard(new Card("K", "하트"));
        dealer.addCard(new Card("6", "하트"));

        //then
        Assertions.assertThat(dealer.isBelowDrawThreshold()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러 카드 값의 합이 드로우 임계치 초과일 때 false를 정상적으로 반환함을 테스트")
    void 딜러_카드_합_드로우_임계치_초과_false_반환_테스트() {
        //given
        Dealer dealer = new Dealer();

        //when
        dealer.addCard(new Card("K", "하트"));
        dealer.addCard(new Card("J", "다이아몬드"));

        //then
        Assertions.assertThat(dealer.isBelowDrawThreshold()).isEqualTo(false);
    }

}
