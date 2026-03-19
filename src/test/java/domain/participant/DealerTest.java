package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 기본 이름은 딜러다.")
    @Test
    void dealerName() {
        Dealer dealer = new Dealer();
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @DisplayName("딜러는 점수가 16 이하일 때 카드를 뽑을 수 있다.")
    @Test
    void canDrawWhenScoreIs16OrLess() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(9));
        dealer.drawCard(new Card(5));
        assertThat(dealer.canDraw()).isTrue();
    }

    @DisplayName("딜러는 점수가 17 이상이면 카드를 뽑을 수 없다.")
    @Test
    void cannotDrawWhenScoreIs17OrMore() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(9));
        dealer.drawCard(new Card(6));

        assertThat(dealer.canDraw()).isFalse();
    }
}
