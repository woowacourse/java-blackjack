package blackjack.domain.game;

import blackjack.domain.card.*;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.fixture.MockDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BlackJackManagerTest {
    private static final Card CLOVER_4 = new Card(CardShape.CLOVER, CardNumber.FOUR);
    private static final Card CLOVER_5 = new Card(CardShape.CLOVER, CardNumber.FIVE);
    private static final Card CLOVER_6 = new Card(CardShape.CLOVER, CardNumber.SIX);
    private static final Card HEART_4 = new Card(CardShape.HEART, CardNumber.FOUR);
    private static final Card HEART_5 = new Card(CardShape.HEART, CardNumber.FIVE);
    private static final Card HEART_6 = new Card(CardShape.HEART, CardNumber.SIX);
    private static final Card SPADE_KING = new Card(CardShape.SPADE, CardNumber.KING);
    private static final Card SPACE_JACK = new Card(CardShape.SPADE, CardNumber.JACK);

    @DisplayName("생성한다.")
    @Test
    void create() {
        final CardDeck cardDeck = new CardDeck();
        final List<String> nameValues = List.of("헤나", "시카");
        final List<Integer> moneyValues = List.of(10000, 20000);
        assertThatNoException()
                .isThrownBy(() -> new BlackJackManager(cardDeck, nameValues, moneyValues));
    }

    @DisplayName("플레이어 히트를 진행한다.")
    @ParameterizedTest
    @MethodSource("hitByPlayerDummy")
    void hitByPlayer(final Deck deck, List<Boolean> checkHitValues, List<Card> expectedCards) {
        // given
        final BlackJackManager blackJackManager = new BlackJackManager(deck, List.of("헤나"), List.of(10000));
        final Deque<Boolean> checkHit = new ArrayDeque<>(checkHitValues);

        // when
        blackJackManager.hitByPlayer((name) -> Boolean.TRUE.equals(checkHit.pollFirst()), System.out::println);
        final List<Player> players = blackJackManager.getPlayers();
        final List<Card> playerCards = players.get(0).openAll();

        // then
        assertThat(playerCards).containsExactlyInAnyOrderElementsOf(expectedCards);
    }

    @DisplayName("딜러 히트를 진행한다.")
    @ParameterizedTest
    @MethodSource("hitByDealerDummy")
    void hitByDealer(final Deck deck, List<Card> expectedDealerCards) {
        // given
        final BlackJackManager blackJackManager = new BlackJackManager(deck, List.of("헤나"), List.of(10000));
        blackJackManager.hitByDealer(System.out::println);

        // when
        final Dealer dealer = blackJackManager.getDealer();
        final List<Card> dealerCards = dealer.openAll();

        // then
        assertThat(dealerCards).containsExactlyInAnyOrderElementsOf(expectedDealerCards);
    }


    @DisplayName("블랙잭 배팅 결과를 반환한다.")
    @Test
    void createBettingResults() {
        // given
        final CardDeck deck = new CardDeck();
        final BlackJackManager blackJackManager = new BlackJackManager(deck, List.of("헤나"), List.of(10000));
        final Dealer dealer = blackJackManager.getDealer();
        final Player player = blackJackManager.getPlayers().get(0);
        final BettingResults bettingResults = blackJackManager.createBettingResults();

        // when
        final Map<Participant, Money> participants = bettingResults.getPlayerBettingResults();
        final Money playerProfit = participants.get(player);
        final Money dealerProfit = participants.get(dealer);

        // then
        assertThat(participants)
                .containsOnly(entry(player, playerProfit), entry(dealer, dealerProfit));
    }

    static Stream<Arguments> hitByPlayerDummy() {
        return Stream.of(
                Arguments.arguments(
                        MockDeck.create(List.of(
                                // Dealer
                                CLOVER_4, CLOVER_5,
                                // Player
                                CLOVER_6, HEART_4, HEART_5
                        )),
                        List.of(true, false),
                        List.of(CLOVER_6, HEART_4, HEART_5)
                ),
                Arguments.arguments(
                        MockDeck.create(List.of(
                                // Dealer
                                CLOVER_4, CLOVER_5,
                                // Player
                                HEART_4, HEART_5, HEART_6, SPADE_KING
                        )),
                        List.of(true, true, false),
                        List.of(HEART_4, HEART_5, HEART_6, SPADE_KING)
                )
        );
    }

    static Stream<Arguments> hitByDealerDummy() {
        return Stream.of(
                Arguments.arguments(
                        MockDeck.create(List.of(
                                // Dealer
                                CLOVER_4, CLOVER_5,
                                // Player
                                CLOVER_6, HEART_4,
                                // Additional Dealer Cards
                                SPACE_JACK
                        )),
                        List.of(CLOVER_4, CLOVER_5, SPACE_JACK)
                ),
                Arguments.arguments(
                        MockDeck.create(List.of(
                                // Dealer
                                CLOVER_4, CLOVER_5,
                                // Player
                                HEART_4, HEART_5,
                                // Additional Dealer Cards
                                HEART_6, CLOVER_6
                        )),
                        List.of(CLOVER_4, CLOVER_5, HEART_6, CLOVER_6)
                )
        );
    }

}
