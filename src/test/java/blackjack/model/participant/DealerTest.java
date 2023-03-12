package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.state.DealerDrawState;
import blackjack.model.state.DealerInitialState;
import blackjack.model.state.PlayerInitialState;
import blackjack.model.state.StandState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.model.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DealerTest {

    @Test
    @DisplayName("게임을 시작하면 딜러는 두 장의 카드를 지급받는다.")
    void player_initial_state_draw() {
        //given
        Card card1 = CLUB_FIVE;
        Card card2 = CLUB_EIGHT;
        List<Card> cards = List.of(card1, card2);
        Dealer dealer = new Dealer(new PlayerInitialState(new Hand()));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        dealer.play(cardDeck);

        //then
        assertThat(dealer.getHand()).containsExactly(card2, card1);
    }

    @Test
    @DisplayName("딜러는 점수가 16 이하면 카드를 뽑을 수 있다.")
    void dealer_can_draw_under_16() {
        //given
        List<Card> cards = List.of(CLUB_FIVE, CLUB_THREE, CLUB_EIGHT);
        Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        dealer.play(cardDeck);

        //then
        assertThatCode(() -> dealer.play(cardDeck)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러는 점수가 16 초과이면 카드를 더 뽑지 못한다.")
    void dealer_can_not_draw_over_16() {
        //given
        List<Card> cards = List.of(HEART_EIGHT, HEART_FIVE);
        Dealer dealer = new Dealer(new DealerDrawState(new Hand(new ArrayList<>(List.of(CLUB_EIGHT, CLUB_FIVE)))));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        dealer.play(cardDeck);

        //then
        assertThatThrownBy(() -> dealer.play(cardDeck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 더 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("딜러는 점수가 16 초과이면 스탠드 상태로 전환된다.")
    void dealer_transit_to_stand_over_16() {
        //given
        List<Card> cards = List.of(CLUB_EIGHT, HEART_FIVE, CLUB_FIVE, HEART_EIGHT);
        Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        dealer.play(cardDeck);
        dealer.play(cardDeck);

        //then
        assertThat(dealer.isStand()).isTrue();
    }

    @Test
    @DisplayName("딜러는 점수가 21 초과이면 버스트 상태로 전환된다.")
    void dealer_transit_to_bust_over_21() {
        //given
        List<Card> cards = List.of(HEART_TEN, HEART_FIVE, HEART_EIGHT);
        Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        dealer.play(cardDeck);
        dealer.play(cardDeck);

        //then
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("딜러의 처음 두 카드의 합이 21이면 블랙잭이다.")
    void player_when_blackjack_can_not_draw() {
        //given
        List<Card> cards = List.of(HEART_ACE, HEART_TEN);
        Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        dealer.play(cardDeck);

        //then
        assertThat(dealer.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 수익 결과가 주어지면 딜러의 수익을 알 수 있다.")
    void calculate_dealer_profit() {
        //given
        List<Integer> playerProfits = List.of(10000, -20000, 50000);
        Dealer dealer = new Dealer(new StandState(new Hand(List.of(CLUB_EIGHT, HEART_TEN))));

        // when
        int dealerProfit = dealer.getProfit(playerProfits);

        //then
        assertThat(dealerProfit).isEqualTo(-40000);
    }

    @Test
    @DisplayName("딜러는 처음에 카드 첫 한장만을 공개한다.")
    void dealer_show_single_card() {
        //given
        List<Card> cards = List.of(HEART_ACE, HEART_TEN);
        CardDeck cardDeck = new CardDeck(cards);
        Dealer dealer = new Dealer(new DealerInitialState(new Hand()));

        // when
        dealer.play(cardDeck);
        Card card = dealer.getFirstCard();

        //then
        assertThat(card).isEqualTo(HEART_TEN);
    }
}
