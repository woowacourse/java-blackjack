package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.card.CardValue;
import blackjack.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private List<Card> defaultInitialCard;

    @BeforeEach
    void init() {
        defaultInitialCard = new ArrayList<>();
        defaultInitialCard.add(new Card(CardType.DIAMOND, CardValue.TEN));
        defaultInitialCard.add(new Card(CardType.DIAMOND, CardValue.TWO));
    }

    @Test
    @DisplayName("카드를 받는다.")
    void test_receive_card() {
        State state = new Hit(defaultInitialCard);
        Participant dealer = new Dealer(state);
        Card card = new Card(CardType.DIAMOND, CardValue.THREE);
        dealer.handOutCard(card);
        assertThat(dealer.showCards().contains(card)).isTrue();
    }

    @Test
    @DisplayName("딜러는 한장의 카드만 보여준다.")
    void test_dealer_show_card() {
        //given
        State state = new Hit(defaultInitialCard);
        Participant dealer = new Dealer(state);

        //when
        List<Card> cards = dealer.showInitCards();

        //then
        assertThat(cards).hasSize(1);
    }

    @Test
    @DisplayName("딜러가 HIT 상태를 가지는 경우 테스트")
    void test_state_hit_dealer() {
        State state = new Hit(defaultInitialCard);
        Participant dealer = new Dealer(state);
        assertThat(dealer.getStatus()).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("딜러가 BLACK JACK 상태를 가지는 경우 테스트")
    void test_state_blackjack_dealer() {
        ArrayList<Card> initialCard = new ArrayList<>();
        initialCard.add(new Card(CardType.DIAMOND, CardValue.TEN));
        initialCard.add(new Card(CardType.DIAMOND, CardValue.ACE));

        State state = new Hit(initialCard);
        Participant dealer = new Dealer(state);
        assertThat(dealer.getStatus()).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("딜러가 BUST 상태를 가지는 경우 테스트")
    void test_state_bust_dealer() {
        State state = new Hit(defaultInitialCard);
        Participant dealer = new Dealer(state);
        dealer.handOutCard(new Card(CardType.SPADE, CardValue.TEN));
        assertThat(dealer.getStatus()).isInstanceOf(Bust.class);
    }


    @Test
    @DisplayName("딜러가 STAY 상태를 가지는 경우 테스트")
    void test_state_stay_dealer() {
        State state = new Hit(defaultInitialCard);
        Participant dealer = new Dealer(state);
        dealer.stay();
        assertThat(dealer.getStatus()).isInstanceOf(Stay.class);
    }
}