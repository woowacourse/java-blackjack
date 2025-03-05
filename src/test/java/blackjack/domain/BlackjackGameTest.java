package blackjack.domain;


import static blackjack.fixture.TestFixture.provideOver16Cards;
import static blackjack.fixture.TestFixture.provideOver21Cards;
import static blackjack.fixture.TestFixture.provideParticipants;
import static blackjack.fixture.TestFixture.providePlayersWithCards;
import static blackjack.fixture.TestFixture.provideUnder16Cards;
import static blackjack.fixture.TestFixture.provideUnder21Cards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardManager;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
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
        Assertions.assertThatCode(blackjackGame::spreadInitialCards)
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어가 카드를 받을 수 있는지 물어본다.")
    @Test
    void canPlayerMoreCardToPlayer() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(new Dealer(new ArrayList<>()),
                        new Players(providePlayersWithCards(provideUnder21Cards(), provideOver21Cards()))));

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
        assertThat(blackjackGame.getPlayer(0).getCards()).hasSize(1);
    }

    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인한다.")
    @ParameterizedTest
    @MethodSource
    void canDealerMoreCard(final List<Card> cards, final boolean expected) {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(new Dealer(cards),
                        new Players(providePlayersWithCards(new ArrayList<>(), new ArrayList<>()))));

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
    void canPlayerMoreCard(final List<Card> cards, final boolean expected) {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(new CardManager(new CardRandomGenerator()),
                new Participants(new Dealer(cards),
                        new Players(providePlayersWithCards(cards, new ArrayList<>()))));

        // when & then
        assertThat(blackjackGame.canPlayerMoreCard(0)).isEqualTo(expected);
    }

    private static Stream<Arguments> canPlayerMoreCard() {
        return Stream.of(
                Arguments.of(provideUnder21Cards(), true),
                Arguments.of(provideOver21Cards(), false)
        );
    }
}