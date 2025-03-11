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
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardManager;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
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
        final BlackjackGame blackjackGame = new BlackjackGame(
                new CardManager(new CardRandomGenerator()),
                provideParticipants());
        // when
        blackjackGame.spreadInitialCards();

        // then
        Assertions.assertAll(
                () -> assertThat(blackjackGame.showDealerCard().getValue().getCards()).hasSize(2),
                () -> assertThat(blackjackGame.showPlayersCards()).hasSize(2)
        );
    }

    @DisplayName("플레이어가 카드를 받을 수 있는지 물어본다.")
    @Test
    void canPlayerMoreCardToPlayer() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(new Dealer(provideEmptyCards()),
                        new Players(provideTwoPlayersWithCards(provideUnder21Cards(), provideOver21Cards()))));

        // when & then
        assertAll(
                () -> assertThat(blackjackGame.canPlayerMoreCard(0)).isTrue(),
                () -> assertThat(blackjackGame.canPlayerMoreCard(1)).isFalse()
        );
    }

    @DisplayName("한 장의 카드를 플레이어에게 준다.")
    @Test
    void spreadOneCardToPlayer() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                provideParticipants());

        // when
        blackjackGame.spreadOneCardToPlayer(0);

        // then
        assertThat(blackjackGame.getPlayer(0).showAllCards().getCards()).hasSize(1);
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
        assertThat(blackjackGame.canPlayerMoreCard(0)).isEqualTo(expected);
    }

    private static Stream<Arguments> canPlayerMoreCard() {
        return Stream.of(
                Arguments.of(provideUnder21Cards(), true),
                Arguments.of(provideOver21Cards(), false)
        );
    }

    @DisplayName("카드의 합을 계산한다.")
    @Test
    void sumCardDenomination() {
        // given
        final Dealer dealer = new Dealer(provideUnder16Cards());
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(dealer,
                        new Players(provideTwoPlayersWithCards(provideUnder21Cards(), provideOver21Cards()))));

        // when & then
        assertAll(
                () -> assertThat(blackjackGame.sumCardDenomination(dealer)).isEqualTo(6),
                () -> assertThat(blackjackGame.sumCardDenomination(blackjackGame.getPlayer(0))).isEqualTo(5),
                () -> assertThat(blackjackGame.sumCardDenomination(blackjackGame.getPlayer(1))).isEqualTo(30)
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

    @DisplayName("한 장의 카드를 딜러에게 준다.")
    @Test
    void spreadOneCardToDealer() {
        // given
        Participants participants = provideParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                participants);

        // when
        blackjackGame.spreadOneCardToDealer();

        // then
        assertThat(participants.getDealer().showAllCards().getCards()).hasSize(1);
    }
}
