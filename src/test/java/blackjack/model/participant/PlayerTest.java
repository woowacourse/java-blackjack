package blackjack.model.participant;

import blackjack.model.BetAmount;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.state.DealerInitialState;
import blackjack.model.state.PlayerDrawState;
import blackjack.model.state.PlayerInitialState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class PlayerTest {

    @Nested
    @DisplayName("플레이어가 카드를 가져가는 play 메소드 테스트")
    class play {
        @Test
        @DisplayName("게임을 시작하면 플레이어는 두 장씩의 카드를 지급받는다.")
        void player_initial_state_draw() {
            //given
            Player player = new Player(new Name("도치"), new BetAmount(10000), new PlayerInitialState(new Hand()));
            Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
            Card card2 = Card.of(CardSuit.HEART, CardNumber.JACK);
            List<Card> cards = List.of(card1, card2);
            CardDeck cardDeck = new CardDeck(cards);

            // when
            player.play(cardDeck);

            //then
            assertThat(player.getHand()).containsExactly(card2, card1);
        }

        @Test
        @DisplayName("draw 상태의 플레이어는 버스트가 될 때까지 카드를 뽑을 수 있다.")
        void player_can_draw_until_bust() {
            //given
            Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
            Card card2 = Card.of(CardSuit.HEART, CardNumber.FIVE);
            Card card3 = Card.of(CardSuit.HEART, CardNumber.NINE);
            Card card4 = Card.of(CardSuit.HEART, CardNumber.EIGHT);
            List<Card> cards = List.of(card4, card3);
            Player player = new Player(new Name("도치"), new BetAmount(10000), new PlayerDrawState(new Hand(new ArrayList<>(List.of(card1, card2)))));
            CardDeck cardDeck = new CardDeck(cards);

            // when
            player.play(cardDeck);

            //then
            assertThatThrownBy(() -> player.play(cardDeck))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("버스트 상태에서는 카드를 더 뽑을 수 없습니다.");
        }

        @Test
        @DisplayName("blackjack 상태의 플레이어는 카드를 뽑을 수 없다.")
        void player_when_blackjack_can_not_draw() {
            //given
            Card card1 = Card.of(CardSuit.CLUB, CardNumber.JACK);
            Card card2 = Card.of(CardSuit.HEART, CardNumber.ACE);
            Card card3 = Card.of(CardSuit.HEART, CardNumber.KING);
            List<Card> cards = List.of(card1, card2, card3);
            Player player = new Player(new Name("도치"), new BetAmount(10000), new PlayerInitialState(new Hand()));
            CardDeck cardDeck = new CardDeck(cards);

            // when
            player.play(cardDeck);

            //then
            assertThatThrownBy(() -> player.play(cardDeck))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("블랙잭 상태에서는 카드를 더 뽑을 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("플레이어의 수익을 계산하는 getProfit 메소드 테스트")
    class getProfit {

        @Nested
        @DisplayName("플레이어가 버스트일 때")
        class player_bust {
            @Test
            @DisplayName("딜러가 버스트라면 플레이어는 베팅 금액을 잃는다")
            void player_lost_x1_when_dealer_bust() {
                //given
                Card card1 = Card.of(CardSuit.HEART, CardNumber.NINE);
                Card card2 = Card.of(CardSuit.CLUB, CardNumber.NINE);
                Card card3 = Card.of(CardSuit.CLUB, CardNumber.JACK);
                Card card4 = Card.of(CardSuit.HEART, CardNumber.QUEEN);
                Card card5 = Card.of(CardSuit.HEART, CardNumber.FIVE);
                Card card6 = Card.of(CardSuit.DIAMOND, CardNumber.KING);
                List<Card> cards = List.of(card1, card2, card3, card4, card5, card6);
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                CardDeck cardDeck = new CardDeck(cards);

                // when
                dealer.play(cardDeck);
                player.play(cardDeck);
                dealer.play(cardDeck);
                player.play(cardDeck);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(player.getProfit(dealer)).isEqualTo(-10000);
            }

            @Test
            @DisplayName("딜러가 스탠드라면 플레이어는 베팅 금액을 잃는다")
            void player_lost_x1_when_dealer_stand() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.NINE);
                Card card2 = Card.of(CardSuit.CLUB, CardNumber.JACK);
                Card card3 = Card.of(CardSuit.HEART, CardNumber.QUEEN);
                Card card4 = Card.of(CardSuit.HEART, CardNumber.SEVEN);
                Card card5 = Card.of(CardSuit.DIAMOND, CardNumber.KING);
                List<Card> cards = List.of(card1, card2, card3, card4, card5);
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                CardDeck cardDeck = new CardDeck(cards);

                // when
                dealer.play(cardDeck);
                player.play(cardDeck);
                player.play(cardDeck);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(player.getProfit(dealer)).isEqualTo(-10000);
            }

            @Test
            @DisplayName("딜러가 블랙잭이라면 플레이어는 베팅 금액을 잃는다")
            void player_lost_x1_when_dealer_blackjack() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.NINE);
                Card card2 = Card.of(CardSuit.CLUB, CardNumber.JACK);
                Card card3 = Card.of(CardSuit.HEART, CardNumber.QUEEN);
                Card card4 = Card.of(CardSuit.HEART, CardNumber.ACE);
                Card card5 = Card.of(CardSuit.DIAMOND, CardNumber.KING);
                List<Card> cards = List.of(card1, card2, card3, card4, card5);
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                CardDeck cardDeck = new CardDeck(cards);

                // when
                dealer.play(cardDeck);
                player.play(cardDeck);
                player.play(cardDeck);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isBlackjack()).isTrue();
                assertThat(player.getProfit(dealer)).isEqualTo(-10000);
            }
        }

        @Nested
        @DisplayName("플레이어가 블랙잭일 때")
        class player_blackjack {
            @Test
            @DisplayName("딜러가 버스트라면 플레이어는 베팅 금액의 1.5배를 얻는다")
            void player_get_x1_5_when_dealer_bust() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.NINE);
                Card card2 = Card.of(CardSuit.CLUB, CardNumber.ACE);
                Card card3 = Card.of(CardSuit.HEART, CardNumber.KING);
                Card card4 = Card.of(CardSuit.HEART, CardNumber.SIX);
                Card card5 = Card.of(CardSuit.DIAMOND, CardNumber.KING);
                List<Card> cards = List.of(card1, card2, card3, card4, card5);
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                CardDeck cardDeck = new CardDeck(cards);

                // when
                dealer.play(cardDeck);
                player.play(cardDeck);
                dealer.play(cardDeck);

                //then
                assertThat(player.isBlackjack()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(player.getProfit(dealer)).isEqualTo(15000);
            }

            //todo 테스트 코드 추가 작성: 딜러스탠드, 딜러블랙잭
        }

        @Nested
        @DisplayName("플레이어가 스탠드일 때")
        class player_stand {
            @Test
            @DisplayName("딜러가 버스트라면 플레이어는 베팅 금액의 1배를 얻는다")
            void player_get_x1_when_dealer_bust() {
                //given
                Card card1 = Card.of(CardSuit.CLUB, CardNumber.NINE);
                Card card2 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
                Card card3 = Card.of(CardSuit.HEART, CardNumber.KING);
                Card card4 = Card.of(CardSuit.HEART, CardNumber.SIX);
                Card card5 = Card.of(CardSuit.DIAMOND, CardNumber.KING);
                List<Card> cards = List.of(card1, card2, card3, card4, card5);
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                CardDeck cardDeck = new CardDeck(cards);

                // when
                dealer.play(cardDeck);
                player.play(cardDeck);
                player.changeToStand();
                dealer.play(cardDeck);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(player.getProfit(dealer)).isEqualTo(10000);
            }

            //todo 테스트 코드 추가 작성: 딜러버스트, 딜러블랙잭
        }
    }

}
