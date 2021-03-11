package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.card.CardValue;
import blackjack.domain.state.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

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
        Participant player = new Player("pobi",0, state);
        Card card = new Card(CardType.DIAMOND, CardValue.TEN);
        player.handOutCard(card);
        Assertions.assertThat(player.showCards().contains(card)).isTrue();
    }

//    @ParameterizedTest
//    @DisplayName("딜러가 카드를 한장을 더 뽑을 수 있는지 확인한다")
//    @CsvSource(value = {
//            "21:true", "22:false"
//    }, delimiter = ':')
//    void test_player_is_receive_card(int totalScore, boolean actual) {
//        State state = new Hit(defaultInitialCard);
//        //given
//        Participant player = new Player("pobi", 0, state);
//
//        //when
//        boolean isReceived = player.isReceiveCard();
//
//        //then
//        assertThat(isReceived).isEqualTo(actual);
//    }

    @Test
    @DisplayName("플레이어가 HIT 상태를 가지는 경우 테스트")
    void test_state_hit_player() {
        State state = new Hit(defaultInitialCard);
        Participant dealer = new Dealer(state);
        assertThat(dealer.getStatus()).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("플레이어가 BLACK JACK 상태를 가지는 경우 테스트")
    void test_state_blackjack_dealer() {
        ArrayList<Card> initialCard = new ArrayList<>();
        initialCard.add(new Card(CardType.DIAMOND, CardValue.TEN));
        initialCard.add(new Card(CardType.DIAMOND, CardValue.ACE));

        State state = new Hit(initialCard);
        Participant dealer = new Dealer(state);
        assertThat(dealer.getStatus()).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("플레이어가 BUST 상태를 가지는 경우 테스트")
    void test_state_bust_dealer() {
        State state = new Hit(defaultInitialCard);
        Participant dealer = new Dealer(state);
        dealer.handOutCard(new Card(CardType.SPADE, CardValue.TEN));
        assertThat(dealer.getStatus()).isInstanceOf(Bust.class);
    }


    @Test
    @DisplayName("플레이어가 STAY 상태를 가지는 경우 테스트")
    void test_state_stay_dealer() {
        State state = new Hit(defaultInitialCard);
        Participant dealer = new Dealer(state);
        dealer.stay();
        assertThat(dealer.getStatus()).isInstanceOf(Stay.class);
    }
}
