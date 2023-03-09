package blackjack.domain.game;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class BlackJackParticipantsTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        final Deck deck = new CardDeck();
        final List<String> nameValues = List.of("헤나", "시카");
        final List<Integer> moneyValues = List.of(1000, 1000);

        assertThatNoException()
                .isThrownBy(() -> new BlackJackParticipants(deck, nameValues, moneyValues));
    }

    @Test
    @DisplayName("블랙잭 결과를 가져온다.")
    void createBettingResults() {
        final Deck deck = new CardDeck();
        final List<String> nameValues = List.of("헤나", "시카");
        final List<Integer> moneyValues = List.of(1000, 1000);
        final BlackJackParticipants participants = new BlackJackParticipants(deck, nameValues, moneyValues);
        final BettingResults bettingResults = participants.createBettingResults();

        assertThat(bettingResults.getPlayerBettingResults()).hasSize(3);
    }

    @ParameterizedTest
    @MethodSource("getPlayersDummy")
    @DisplayName("플레이어 리스트를 가져온다.")
    void getPlayers(final List<String> nameValues, final List<Integer> moneyValues) {
        final CardDeck deck = new CardDeck();
        final BlackJackParticipants participants = new BlackJackParticipants(deck, nameValues, moneyValues);
        final List<Player> players = participants.getPlayers();

        assertThat(players).hasSize(nameValues.size());
    }

    @ParameterizedTest
    @MethodSource("getPlayersDummy")
    @DisplayName("딜러를 가져온다.")
    void getDealer(final List<String> nameValues, final List<Integer> moneyValues) {
        final CardDeck deck = new CardDeck();
        final BlackJackParticipants participants = new BlackJackParticipants(deck, nameValues, moneyValues);
        final Dealer dealer = participants.getDealer();
        final Name dealerName = dealer.getName();

        assertThat(dealerName.getValue()).isEqualTo("딜러");
    }

    static Stream<Arguments> getPlayersDummy() {
        return Stream.of(
                Arguments.arguments(
                        List.of("헤나1"),
                        List.of(0)),
                Arguments.arguments(
                        List.of("헤나1", "헤나2"),
                        List.of(0, 0)),
                Arguments.arguments(
                        List.of("헤나1", "헤나2", "헤나3"),
                        List.of(0, 0, 0)),
                Arguments.arguments(
                        List.of("헤나1", "헤나2", "헤나3", "시카1", "시카2", "시카3"),
                        List.of(0, 0, 0, 0, 0, 0))
        );
    }
}
