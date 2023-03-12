package blackjack.model.result;

import blackjack.model.participant.BetAmount;
import blackjack.model.card.CardDeck;
import blackjack.model.participant.*;
import blackjack.model.state.DealerInitialState;
import blackjack.model.state.PlayerInitialState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.model.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultTest {

    @Nested
    @DisplayName("플레이어가 버스트일 때")
    class player_bust {

        @Nested
        @DisplayName("딜러가 버스트라면")
        class dealer_bust {
            @Test
            @DisplayName("플레이어가 패배한다")
            void player_lose() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(HEART_SIX, HEART_NINE, HEART_JACK, HEART_TEN, DIAMOND_SIX, CLUB_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, true);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

        @Nested
        @DisplayName("딜러가 블랙잭이라면")
        class dealer_blackjack {
            @Test
            @DisplayName("플레이어가 패배한다")
            void player_lose() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(HEART_FIVE, CLUB_JACK, HEART_TEN, HEART_ACE, HEART_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, true);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isBlackjack()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

        @Nested
        @DisplayName("딜러가 스탠드라면")
        class dealer_stand {
            @Test
            @DisplayName("플레이어가 패배한다")
            void player_lose() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(HEART_FIVE, CLUB_JACK, HEART_TEN, HEART_SEVEN, HEART_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, true);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBust()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

    }

    @Nested
    @DisplayName("플레이어가 블랙잭일 때")
    class player_blackjack {

        @Nested
        @DisplayName("딜러가 버스트라면")
        class dealer_bust {
            @Test
            @DisplayName("플레이어가 승리한다")
            void player_win() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(HEART_NINE, HEART_ACE, HEART_JACK, DIAMOND_SIX, CLUB_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBlackjack()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(result).isEqualTo(Result.WIN);
            }
        }

        @Nested
        @DisplayName("딜러가 블랙잭이라면")
        class dealer_blackjack {
            @Test
            @DisplayName("플레이어는 무승부다")
            void player_tie() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(CLUB_ACE, CLUB_JACK, HEART_ACE, HEART_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBlackjack()).isTrue();
                assertThat(dealer.isBlackjack()).isTrue();
                assertThat(result).isEqualTo(Result.TIE);
            }
        }

        @Nested
        @DisplayName("딜러가 스탠드라면")
        class dealer_stand {
            @Test
            @DisplayName("플레이어가 승리한다")
            void player_win() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(HEART_ACE, HEART_TEN, HEART_SEVEN, HEART_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isBlackjack()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.WIN);
            }
        }

    }

    @Nested
    @DisplayName("플레이어가 스탠드일 때")
    class player_stand {

        @Nested
        @DisplayName("딜러가 버스트라면")
        class dealer_bust {
            @Test
            @DisplayName("플레이어가 승리한다")
            void player_win() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(HEART_NINE, CLUB_NINE, CLUB_JACK, HEART_SIX, HEART_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                participants.hitOrStandByDealer(cardDeck);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isBust()).isTrue();
                assertThat(result).isEqualTo(Result.WIN);
            }
        }

        @Nested
        @DisplayName("딜러가 블랙잭이라면")
        class dealer_blackjack {
            @Test
            @DisplayName("플레이어는 패배한다")
            void player_lose() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(HEART_NINE, HEART_JACK, HEART_ACE, CLUB_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isBlackjack()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }
        }

        @Nested
        @DisplayName("딜러가 스탠드라면")
        class dealer_stand {

            @Test
            @DisplayName("플레이어의 총점이 딜러보다 높으면 플레이어가 승리한다.")
            void player_win() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(CLUB_NINE, CLUB_JACK, HEART_SEVEN, HEART_TEN));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.WIN);
            }

            @Test
            @DisplayName("플레이어의 총점과 딜러의 총점이 같으면 무승부다.")
            void player_tie() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(HEART_SEVEN, HEART_TEN, DIAMOND_SEVEN, HEART_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.TIE);
            }

            @Test
            @DisplayName("플레이어의 총점이 딜러의 총점보다 작으면 패배한다.")
            void player_lose() {
                //given
                CardDeck cardDeck = new CardDeck(List.of(HEART_SEVEN, HEART_TEN, HEART_NINE, HEART_JACK));
                Dealer dealer = new Dealer(new DealerInitialState(new Hand()));
                Player player = new Player(new Name("이리내"), new BetAmount(10000), new PlayerInitialState(new Hand()));
                Participants participants = new Participants(dealer, List.of(player));

                //when
                participants.distributeTwoCardsToEach(cardDeck);
                participants.hitOrStandByPlayer(cardDeck, player, false);
                Result result = Result.checkPlayerResult(player, dealer);

                //then
                assertThat(player.isStand()).isTrue();
                assertThat(dealer.isStand()).isTrue();
                assertThat(result).isEqualTo(Result.LOSE);
            }

        }

    }

}
