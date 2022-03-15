package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러의 이름은 딜러이다")
    void createDealer() {
        Dealer dealer = new Dealer();

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("딜러가 카드를 더 받아야할지 체크한다.")
    void checkDealerHit() {
        Dealer dealer = new Dealer();

        dealer.receiveInitCards(List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.HEART, Denomination.SIX)));

        assertThat(dealer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 받지 않아도 될지 체크한다.")
    void checkDealerNotHit() {
        Dealer dealer = new Dealer();

        dealer.receiveInitCards(List.of(new Card(Suit.DIAMOND, Denomination.KING),
            new Card(Suit.HEART, Denomination.SEVEN)));

        assertThat(dealer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("딜러는 맨 처음 자신의 첫번째 카드를 오픈한다.")
    void getFirstCard() {
        Dealer dealer = new Dealer();

        dealer.receiveInitCards(List.of(new Card(Suit.DIAMOND, Denomination.KING),
            new Card(Suit.HEART, Denomination.SEVEN)));

        Card card = dealer.getFirstCard();

        String cardInfo = card.getDenominationName() + card.getSuitName();
        String answer = "K다이아몬드";

        assertThat(cardInfo).isEqualTo(answer);
    }
}
