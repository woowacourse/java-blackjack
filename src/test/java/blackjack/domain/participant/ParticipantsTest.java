//package blackjack.domain.participant;
//
//import static blackjack.fixture.TestFixture.provideCards;
//import static blackjack.fixture.TestFixture.provideOver16Cards;
//import static blackjack.fixture.TestFixture.provideOver21Cards;
//import static blackjack.fixture.TestFixture.provideParticipants;
//import static blackjack.fixture.TestFixture.provideTwoPlayersWithCards;
//import static blackjack.fixture.TestFixture.provideUnder21Cards;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertAll;
//
//import blackjack.domain.card.Hand;
//import java.util.List;
//import java.util.stream.Stream;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//
//class ParticipantsTest {
//
//    @DisplayName("모든 참가자에게 카드 두장씩 분배한다.")
//    @Test
//    void test() {
//        // given
//        Participants participants = provideParticipants();
//        Hand hand = provideCards(6);
//        final Hand totalHand = provideCards(6);
//        final Hand dealerHand = totalHand.getPartialCards(0, 2);
//        final Hand playerCard1 = totalHand.getPartialCards(2, 4);
//        final Hand playerCard2 = totalHand.getPartialCards(4, 6);
//
//        // when
//        participants.spreadAllTwoCards(hand);
//
//        // then
//        assertAll(
//                () -> assertThat(participants.getDealer()).isEqualTo(new Dealer(dealerHand)),
//                () -> assertThat(participants.getPlayers()).isEqualTo(
//                        new Players(provideTwoPlayersWithCards(playerCard1, playerCard2)))
//        );
//    }
//
//    @DisplayName("초기에 나눠주는 카드 수를 계산한다.")
//    @Test
//    void calculateParticipantsSize() {
//        // given
//        Participants participants = provideParticipants();
//
//        // when & then
//        assertThat(participants.getInitialTotalCardsSize()).isEqualTo(6);
//    }
//
//    @DisplayName("딜러가 카드를 더 얻을 수 있으면 true를 반환한다.")
//    @ParameterizedTest
//    @MethodSource
//    void canDealerHit(final Dealer dealer, final boolean expected) {
//        // given
//        final Hand firstHand = provideUnder21Cards();
//        final Hand secondHand = provideOver21Cards();
//        final List<Player> players = provideTwoPlayersWithCards(firstHand, secondHand);
//        Participants participants = new Participants(dealer, new Players(players));
//
//        // when & then
//        assertThat(participants.canDealerHit()).isEqualTo(expected);
//    }
//
//    private static Stream<Arguments> canDealerHit() {
//        return Stream.of(
//                Arguments.of(Dealer.createEmpty(), true),
//                Arguments.of(new Dealer(provideOver16Cards()), false)
//        );
//    }
//
//    @DisplayName("플레이어가 카드를 더 얻을 수 있으면 true를 반환한다.")
//    @ParameterizedTest
//    @MethodSource
//    void canPlayerHit(final Hand hand, final boolean expected) {
//        // given
//        final Players players = new Players(List.of(new Player("밍트", hand)));
//
//        Participants participants = new Participants(Dealer.createEmpty(), players);
//
//        // when & then
//        assertThat(participants.canPlayerHit(0)).isEqualTo(expected);
//    }
//
//    private static Stream<Arguments> canPlayerHit() {
//        return Stream.of(
//                Arguments.of(provideUnder21Cards(), true),
//                Arguments.of(provideOver21Cards(), false)
//        );
//    }
//}
