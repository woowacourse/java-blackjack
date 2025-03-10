package blackjack.domain;


import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.provideOver16Cards;
import static blackjack.fixture.TestFixture.provideOver21Cards;
import static blackjack.fixture.TestFixture.provideParticipants;
import static blackjack.fixture.TestFixture.provideThreePlayersWithCards;
import static blackjack.fixture.TestFixture.provideTwoPlayersWithCards;
import static blackjack.fixture.TestFixture.provideUnder16Cards;
import static blackjack.fixture.TestFixture.provideUnder21Cards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardManager;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackGameTest {

    @DisplayName("플레이어 및 딜러에게 카드를 두 장씩 나눠준다.")
    @Test
    void spreadTwoCardsToDealerAndPlayers() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                provideParticipants());

        // when & then
        assertThatCode(blackjackGame::spreadInitialCards)
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어가 카드를 받을 수 있는지 물어본다.")
    @Test
    void canPlayerMoreCardToPlayer() {
        // given
        final Participants participants = new Participants(new Dealer(provideEmptyCards()),
                new Players(provideTwoPlayersWithCards(provideUnder21Cards(), provideOver21Cards())));
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                participants);

        // when
        Player player1 = blackjackGame.getPlayer(0);
        Player player2 = blackjackGame.getPlayer(1);

        // then
        assertAll(
                () -> assertThat(blackjackGame.canPlayerMoreCard(player1)).isTrue(),
                () -> assertThat(blackjackGame.canPlayerMoreCard(player2)).isFalse()
        );
    }

    @DisplayName("한 장의 카드를 플레이어에게 준다.")
    @Test
    void spreadOneCardToPlayer() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                provideParticipants());

        // when
        final Player player = blackjackGame.getPlayer(0);
        blackjackGame.spreadOneCardToPlayer(player);

        // then
        assertThat(blackjackGame.getPlayer(0).showAllCard()).hasSize(1);
    }

    @DisplayName("한 장의 카드를 딜러에게 준다.")
    @Test
    void spreadOneCardToDealer() {
        // given

        final Participants participants = provideParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                participants);

        // when
        blackjackGame.spreadOneCardToDealer();

        // then
        assertThat(participants.getDealer().showAllCard()).hasSize(1);
    }

    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인한다.")
    @ParameterizedTest
    @MethodSource
    void canDealerMoreCard(final Cards cards, final boolean expected) {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(new Dealer(cards),
                        new Players(provideTwoPlayersWithCards(provideEmptyCards(), provideEmptyCards()))));

        // when & then
        assertThat(blackjackGame.canDealerMoreCard()).isEqualTo(expected);
    }

    private static Stream<Arguments> canDealerMoreCard() {
        return Stream.of(
                Arguments.of(provideUnder16Cards(), true),
                Arguments.of(provideOver16Cards(), false)
        );
    }

    @DisplayName("플레이어가 카드를 더 받을 수 있는지 확인한다.")
    @ParameterizedTest
    @MethodSource
    void canPlayerMoreCard(final Cards cards, final boolean expected) {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(new Dealer(provideEmptyCards()),
                        new Players(provideTwoPlayersWithCards(cards, provideEmptyCards()))));

        // when & then
        final Player player = blackjackGame.getPlayer(0);
        assertThat(blackjackGame.canPlayerMoreCard(player)).isEqualTo(expected);
    }

    private static Stream<Arguments> canPlayerMoreCard() {
        return Stream.of(
                Arguments.of(provideUnder21Cards(), true),
                Arguments.of(provideOver21Cards(), false)
        );
    }

    @DisplayName("딜러에 대한 플레이어의 승,패 결과를 계산한다")
    @Test
    void calculateWinningResult() {
        // given
        final Cards dealerCards = new Cards(List.of(new Card(Shape.SPADE, Denomination.J),
                new Card(Shape.SPADE, Denomination.EIGHT)));

        final Dealer dealer = new Dealer(dealerCards);
        final Cards player1Cards = new Cards(List.of(new Card(Shape.SPADE, Denomination.NINE),
                new Card(Shape.SPADE, Denomination.TEN)));
        final Cards player2Cards = new Cards(List.of(new Card(Shape.SPADE, Denomination.TWO),
                new Card(Shape.SPADE, Denomination.J)));
        final Cards player3Cards = new Cards(List.of(new Card(Shape.SPADE, Denomination.EIGHT),
                new Card(Shape.SPADE, Denomination.K)));
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(dealer,
                        new Players(provideThreePlayersWithCards(player1Cards, player2Cards, player3Cards))));

        // when & then
        final String mj = "엠제이";
        final String mint = "밍트";
        final String pobi = "포비";
        assertThat(blackjackGame.calculateWinningResult()).isEqualTo(
                Map.of(mj, ResultStatus.WIN, mint, ResultStatus.LOSE, pobi, ResultStatus.DRAW));
    }

    @DisplayName("플레이어들이 베팅한다.")
    @Test
    void betPlayers() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(new Dealer(provideEmptyCards()),
                        new Players(provideTwoPlayersWithCards(provideEmptyCards(), provideEmptyCards()))));

        // when
        final Player player1 = blackjackGame.getPlayer(0);

        // then
        assertThatCode(() -> player1.bet(10000)).doesNotThrowAnyException();
    }

    @DisplayName("카드 합에 맞는 베팅 결과를 계산한다.")
    @Test
    void calculateEanings() {
        // given
        final Cards dealerCards = new Cards(List.of(new Card(Shape.SPADE, Denomination.J),
                new Card(Shape.SPADE, Denomination.EIGHT)));
        final Dealer dealer = new Dealer(dealerCards); // 18
        final Cards player1Cards = new Cards(List.of(new Card(Shape.SPADE, Denomination.NINE),
                new Card(Shape.SPADE, Denomination.TEN))); // 19
        final Cards player2Cards = new Cards(List.of(new Card(Shape.SPADE, Denomination.TWO),
                new Card(Shape.SPADE, Denomination.J))); // 12
        final Cards player3Cards = new Cards(List.of(new Card(Shape.SPADE, Denomination.EIGHT),
                new Card(Shape.SPADE, Denomination.K))); // 18
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(dealer,
                        new Players(provideThreePlayersWithCards(player1Cards, player2Cards, player3Cards))));

        // when
        final Player player1 = blackjackGame.getPlayer(0);
        final Player player2 = blackjackGame.getPlayer(1);
        final Player player3 = blackjackGame.getPlayer(2);

        player1.bet(10000);
        player2.bet(20000);
        player3.bet(30000);

        blackjackGame.calculateWinningResult(false);

        // then
        assertAll(
                () -> assertThat(player1.getEarnedMoney()).isEqualTo(10000),
                () -> assertThat(player2.getEarnedMoney()).isEqualTo(-20000),
                () -> assertThat(player3.getEarnedMoney()).isEqualTo(-30000),
                () -> assertThat(dealer.getEarnedMoney()).isEqualTo(40000)
        );
    }

    @DisplayName("blackjack 경우의 베팅 결과를 계산한다.")
    @Test
    void calculateBlackjack() {
        // given
        final Cards blackjackCards = new Cards(List.of(new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.SPADE, Denomination.A))); // 21

        final Dealer dealer = new Dealer(provideOver16Cards()); // 20
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(dealer,
                        new Players(provideTwoPlayersWithCards(blackjackCards, provideUnder21Cards()))));

        // when
        final Player player1 = blackjackGame.getPlayer(0);
        final Player player2 = blackjackGame.getPlayer(1);

        player1.bet(10000);
        player2.bet(20000);

        blackjackGame.calculateWinningResult(false);

        // then
        assertAll(
                () -> assertThat(player1.getEarnedMoney()).isEqualTo(5000),
                () -> assertThat(player2.getEarnedMoney()).isEqualTo(-20000),
                () -> assertThat(dealer.getEarnedMoney()).isEqualTo(15000)
        );
    }

    @DisplayName("push 경우의 베팅 결과를 계산한다.")
    @Test
    void calculatePush() {
        // given
        final Cards blackjackCards = new Cards(List.of(new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.SPADE, Denomination.A))); // 21

        final Dealer dealer = new Dealer(blackjackCards);
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(dealer,
                        new Players(provideTwoPlayersWithCards(blackjackCards, provideUnder21Cards()))));

        // when
        final Player player1 = blackjackGame.getPlayer(0);
        final Player player2 = blackjackGame.getPlayer(1);

        player1.bet(10000);
        player2.bet(20000);

        blackjackGame.calculateWinningResult(true);

        // then
        assertAll(
                () -> assertThat(player1.getEarnedMoney()).isEqualTo(0),
                () -> assertThat(player2.getEarnedMoney()).isEqualTo(-20000),
                () -> assertThat(dealer.getEarnedMoney()).isEqualTo(20000)
        );
    }
}
