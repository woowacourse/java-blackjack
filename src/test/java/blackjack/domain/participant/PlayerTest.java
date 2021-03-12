package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.card.CardValue;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.domain.state.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private List<Card> defaultInitialCards;
    private List<Card> blackJackCards;

    @BeforeEach
    void init() {
        defaultInitialCards = new ArrayList<>();
        defaultInitialCards.add(new Card(CardType.DIAMOND, CardValue.TEN));
        defaultInitialCards.add(new Card(CardType.DIAMOND, CardValue.SEVEN));

        blackJackCards = new ArrayList<>();
        blackJackCards.add(new Card(CardType.CLOVER, CardValue.TEN));
        blackJackCards.add(new Card(CardType.CLOVER, CardValue.ACE));
    }

    @Test
    @DisplayName("카드를 받는다.")
    void test_receive_card() {
        State state = new Hit(defaultInitialCards);
        Participant player = new Player("pobi", state);
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
        State state = new Hit(defaultInitialCards);
        Participant player = new Player("suri", state);
        assertThat(player.getStatus()).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("플레이어가 BLACK JACK 상태를 가지는 경우 테스트")
    void test_state_blackjack_dealer() {
        State state = new Hit(blackJackCards);
        Player player = new Player("suri", state);
        assertThat(player.getStatus()).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("플레이어가 BUST 상태를 가지는 경우 테스트")
    void test_state_bust_dealer() {
        State state = new Hit(defaultInitialCards);
        Player player = new Player("suri", state);
        player.handOutCard(new Card(CardType.SPADE, CardValue.TEN));
        assertThat(player.getStatus()).isInstanceOf(Bust.class);
    }


    @Test
    @DisplayName("플레이어가 STAY 상태를 가지는 경우 테스트")
    void test_state_stay_dealer() {
        State state = new Hit(defaultInitialCards);
        Participant dealer = new Dealer(state);
        dealer.stay();
        assertThat(dealer.getStatus()).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("플레이어가 블랙잭 이고 딜러가 블랙잭이 아닌 경우 1.5배의 상금을 받는다.")
    void calculateWinPrize_player_blackjack_dealer_not() {
        State blackJackState = new Hit(blackJackCards);
        State hitState = new Hit(defaultInitialCards);
        int money = 10000;
        Dealer dealer = new Dealer(hitState);
        Player player = new Player("suri", blackJackState);
        player.betting(money);
        assertThat(player.calculateWinPrize(dealer.getStatus())).isEqualTo((int) (money * 1.5));
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 블랙잭인 경우 무승부")
    void calculateWinPrize_player_dealer_blackjack() {
        State blackJackState = new Hit(blackJackCards);
        int money = 10000;
        Dealer dealer = new Dealer(blackJackState);
        Player player = new Player("suri", blackJackState);
        player.betting(money);

        assertThat(player.calculateWinPrize(dealer.getStatus())).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아니고 딜러가 블랙잭인 경우 플레이어 패배")
    void calculateWinPrize_player_not_dealer_blackjack() {
        State blackJackState = new Hit(blackJackCards);
        State hitState = new Hit(defaultInitialCards);
        int money = 10000;
        Dealer dealer = new Dealer(blackJackState);
        Player player = new Player("suri", hitState);
        player.betting(money);
        player.stay();

        assertThat(player.calculateWinPrize(dealer.getStatus())).isEqualTo(money * -1);
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 블랙잭이 아닐 때 점수가 높은 쪽이 승리")
    void calculateWinPrize_player_dealer_not_blackjack() {
        State dealerState = new Hit(new ArrayList<>(defaultInitialCards));
        State playerState = new Hit(new ArrayList<>(defaultInitialCards));
        int money = 10000;
        Dealer dealer = new Dealer(dealerState);
        dealer.stay();
        Player player = new Player("suri", playerState);
        player.betting(money);
        player.handOutCard(new Card(CardType.DIAMOND, CardValue.TWO));
        player.stay();
        assertThat(player.calculateWinPrize(dealer.getStatus())).isEqualTo(money);
    }
}
