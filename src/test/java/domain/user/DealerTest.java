package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Card;
import domain.CardHand;
import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("점수를 계산한다.")
    void 점수_계산() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Suit.SPADE, Rank.TWO));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("이름을 반환한다.")
    void 이름_반환() {
        String nameValue = "딜러";
        Dealer dealer = new Dealer();
        assertThat(dealer.getNameValue()).isEqualTo(nameValue);
    }

    @Test
    @DisplayName("카드를 더 받을 수 있는 경우 true를 반환한다.")
    void 카드_추가_가능() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Suit.SPADE, Rank.TWO));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.canAdd()).isTrue();
    }

    @Test
    @DisplayName("카드를 더 받을 수 없는 경우 false를 반환한다.")
    void 카드_추가_불가능() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Suit.SPADE, Rank.SEVEN));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.canAdd()).isFalse();
    }

    @Test
    @DisplayName("카드를 저장한다.")
    void 카드_저장() {
        Dealer dealer = new Dealer();
        assertThat(dealer.calculateScore()).isEqualTo(0);
        dealer.addCard(new Card(Suit.SPADE, Rank.TWO));
        assertThat(dealer.calculateScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 추가하는 것이 불가능할 때 추가하려는 경우 예외가 발생한다.")
    void 카드_저장_불가능시_예외() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Suit.SPADE, Rank.ACE));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThatThrownBy(() -> dealer.addCard(new Card(Suit.SPADE, Rank.ACE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드 추가가 불가능하여 실행되지 않았습니다.");
    }

    @Test
    @DisplayName("Dealer가 가진 카드의 점수의 합이 블랙잭이면 true를 반환한다.")
    void 카드_블랙잭_인지_확인() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Suit.SPADE, Rank.ACE));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("Dealer가 가진 카드의 점수의 합이 블랙잭이 아니면 false를 반환한다.")
    void 카드_블랙잭_아닌지_확인() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("갖고 있는 카드의 점수가 블랙잭 점수를 초과하면 true를 반환한다.")
    void 블랙잭_초과() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        cardHand.add(new Card(Suit.CLOVER, Rank.TWO));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("갖고 있는 카드의 점수가 블랙잭 점수를 초과하지 않으면 false를 반환한다.")
    void 블랙잭_이하() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        cardHand.add(new Card(Suit.CLOVER, Rank.KING));
        cardHand.add(new Card(Suit.CLOVER, Rank.ACE));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.isBust()).isFalse();
    }
}