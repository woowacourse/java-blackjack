package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("요청한 카드 저장 확인 테스트")
    void shouldSuccessTakeCard() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card("10다이아몬드", 10));
        dealer.drawCard(new Card("3다이아몬드", 3));
        assertThat(dealer.getHandCards().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 17 미만아면 true를 반환한다. (소프트 17 케이스는 제외)")
    void isCardValueUnder17() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card("A다이아몬드", 1));
        dealer.drawCard(new Card("6다이아몬드", 6));
        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 16 초과이면 false를 반환한다. (소프트 17 케이스는 제외)")
    void isCardValueOver16() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card("10다이아몬드", 10));
        dealer.drawCard(new Card("7다이아몬드", 7));
        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 21 초과이면 false를 반환한다. (소프트 17 케이스는 제외)")
    void isCardValueOver21() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card("10다이아몬드", 10));
        dealer.drawCard(new Card("9다이아몬드", 9));
        dealer.drawCard(new Card("3다이아몬드", 3));
        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("소프트 17의 경우에는 false를 반환한다")
    void isFalseWhenSoft17() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card("A다이아몬드", 1));
        dealer.drawCard(new Card("6다이아몬드", 6));
        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("가지고 있는 카드가 A 2장일 경우 true를 반환한다.")
    void isTrueWhenDoubleAce() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card("A다이아몬드", 1));
        dealer.drawCard(new Card("A하트", 1));
        assertThat(dealer.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("딜러는 Bust가 나지 않는 선에서 최선의 값을 선택한다.")
    void isDealerAlwaysChooseOptimalValue() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card("A다이아몬드", 1));
        dealer.drawCard(new Card("A하트", 1));
        dealer.drawCard(new Card("10다이아몬드", 10));
        assertThat(dealer.isDrawable()).isTrue();
    }
}
