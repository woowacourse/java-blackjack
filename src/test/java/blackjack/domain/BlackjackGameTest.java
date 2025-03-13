//package blackjack.domain;
//
//
//import static blackjack.fixture.TestFixture.provideEmptyCards;
//import static blackjack.fixture.TestFixture.provideOver16Cards;
//import static blackjack.fixture.TestFixture.provideOver21Cards;
//import static blackjack.fixture.TestFixture.provideParticipants;
//import static blackjack.fixture.TestFixture.providePlayers;
//import static blackjack.fixture.TestFixture.provideThreePlayersWithCards;
//import static blackjack.fixture.TestFixture.provideTwoPlayersWithCards;
//import static blackjack.fixture.TestFixture.provideUnder16Cards;
//import static blackjack.fixture.TestFixture.provideUnder21Cards;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertAll;
//
//import blackjack.domain.card.Card;
//import blackjack.domain.card.Deck;
//import blackjack.domain.card.CardScore;
//import blackjack.domain.card.Hand;
//import blackjack.domain.card.Shape;
//import blackjack.domain.participant.Dealer;
//import blackjack.domain.participant.Gamer;
//import blackjack.domain.participant.Participants;
//import blackjack.domain.participant.Players;
//import blackjack.domain.shuffle.ShuffleCardGenerator;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Stream;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//
//class BlackjackGameTest {
//
//    @DisplayName("플레이어 및 딜러에게 카드를 두 장씩 나눠준다.")
//    @Test
//    void spreadTwoCardsToDealerAndPlayers() {
//        // given
//        final BlackjackGame blackjackGame = new BlackjackGame(
//                new Deck(new ShuffleCardGenerator()),
//                provideParticipants());
//        // when
//        blackjackGame.spreadInitialCards();
//
//        // then
//        Assertions.assertAll(
//                () -> assertThat(blackjackGame.showDealerCard().getValue().getHand()).hasSize(2),
//                () -> assertThat(blackjackGame.showPlayersCards()).hasSize(2)
//        );
//    }
//
//    @DisplayName("플레이어가 카드를 받을 수 있는지 물어본다.")
//    @Test
//    void canPlayerMoreCardToPlayer() {
//        // given
//        final BlackjackGame blackjackGame = new BlackjackGame(new Deck(new ShuffleCardGenerator()),
//                new Participants(new Dealer(provideEmptyCards()),
//                        new Players(provideTwoPlayersWithCards(provideUnder21Cards(), provideOver21Cards()))));
//
//        // when & then
//        assertAll(
//                () -> assertThat(blackjackGame.canPlayerHit(0)).isTrue(),
//                () -> assertThat(blackjackGame.canPlayerHit(1)).isFalse()
//        );
//    }
//
//    @DisplayName("한 장의 카드를 플레이어에게 준다.")
//    @Test
//    void spreadOneCardToPlayer() {
//        // given
//        Players players = providePlayers();
//        Participants participants = new Participants(new Dealer(provideEmptyCards()), players);
//        final BlackjackGame blackjackGame = new BlackjackGame(new Deck(new ShuffleCardGenerator()),
//                participants);
//        Gamer gamer = players.getPlayer(0);
//
//        // when
//        blackjackGame.spreadOneCardToPlayer(gamer);
//
//        // then
//        assertThat(gamer.showAllCards().getHand()).hasSize(1);
//    }
//
//    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인한다.")
//    @ParameterizedTest
//    @MethodSource
//    void canDealerHit(final Hand hand, final boolean expected) {
//        // given
//        final BlackjackGame blackjackGame = new BlackjackGame(new Deck(new ShuffleCardGenerator()),
//                new Participants(new Dealer(hand),
//                        new Players(provideTwoPlayersWithCards(provideEmptyCards(), provideEmptyCards()))));
//
//        // when & then
//        assertThat(blackjackGame.canDealerHit()).isEqualTo(expected);
//    }
//
//    private static Stream<Arguments> canDealerHit() {
//        return Stream.of(
//                Arguments.of(provideUnder16Cards(), true),
//                Arguments.of(provideOver16Cards(), false)
//        );
//    }
//
//    @DisplayName("플레이어가 카드를 더 받을 수 있는지 확인한다.")
//    @ParameterizedTest
//    @MethodSource
//    void canPlayerHit(final Hand hand, final boolean expected) {
//        // given
//        final BlackjackGame blackjackGame = new BlackjackGame(new Deck(new ShuffleCardGenerator()),
//                new Participants(new Dealer(provideEmptyCards()),
//                        new Players(provideTwoPlayersWithCards(hand, provideEmptyCards()))));
//
//        // when & then
//        assertThat(blackjackGame.canPlayerHit(0)).isEqualTo(expected);
//    }
//
//    private static Stream<Arguments> canPlayerHit() {
//        return Stream.of(
//                Arguments.of(provideUnder21Cards(), true),
//                Arguments.of(provideOver21Cards(), false)
//        );
//    }
//
//    @DisplayName("카드의 합을 계산한다.")
//    @Test
//    void calculateScore() {
//        // given
//        final Dealer dealer = new Dealer(provideUnder16Cards());
//        final BlackjackGame blackjackGame = new BlackjackGame(new Deck(new ShuffleCardGenerator()),
//                new Participants(dealer,
//                        new Players(provideTwoPlayersWithCards(provideUnder21Cards(), provideOver21Cards()))));
//
//        // when & then
//        assertAll(
//                () -> assertThat(blackjackGame.calculateScore(dealer)).isEqualTo(6),
//                () -> assertThat(blackjackGame.calculateScore(blackjackGame.getPlayer(0))).isEqualTo(5),
//                () -> assertThat(blackjackGame.calculateScore(blackjackGame.getPlayer(1))).isEqualTo(30)
//        );
//    }
//
//    @DisplayName("딜러의 승,패 결과를 계산한다")
//    @Test
//    void makeDealerWinningResult() {
//        // given
//        final Dealer dealer = new Dealer(new Hand(List.of(new Card(Shape.SPADE, CardScore.J),
//                new Card(Shape.SPADE, CardScore.EIGHT))));
//        final Hand player1Hand = new Hand(List.of(new Card(Shape.SPADE, CardScore.NINE),
//                new Card(Shape.SPADE, CardScore.TEN)));
//        final Hand player2Hand = new Hand(List.of(new Card(Shape.SPADE, CardScore.TWO),
//                new Card(Shape.SPADE, CardScore.J)));
//        final Hand player3Hand = new Hand(List.of(new Card(Shape.SPADE, CardScore.EIGHT),
//                new Card(Shape.SPADE, CardScore.K)));
//        final BlackjackGame blackjackGame = new BlackjackGame(new Deck(new ShuffleCardGenerator()),
//                new Participants(dealer,
//                        new Players(provideThreePlayersWithCards(player1Hand, player2Hand, player3Hand))));
//
//        final DealerWinningResult expected = new DealerWinningResult(Map.of(
//                "엠제이", ResultStatus.LOSE, "밍트", ResultStatus.WIN, "포비", ResultStatus.PUSH
//        ));
//
//        // when
//        DealerWinningResult dealerWinningResult = blackjackGame.makeDealerWinningResult();
//
//        // then
//        assertThat(dealerWinningResult).isEqualTo(expected);
//    }
//
//    @DisplayName("한 장의 카드를 딜러에게 준다.")
//    @Test
//    void spreadOneCardToDealer() {
//        // given
//        Participants participants = provideParticipants();
//        final BlackjackGame blackjackGame = new BlackjackGame(new Deck(new ShuffleCardGenerator()),
//                participants);
//
//        // when
//        blackjackGame.spreadOneCardToDealer();
//
//        // then
//        assertThat(participants.getDealer().showAllCards().getHand()).hasSize(1);
//    }
//}
