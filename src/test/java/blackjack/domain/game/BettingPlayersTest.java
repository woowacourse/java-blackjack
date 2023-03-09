package blackjack.domain.game;

import blackjack.domain.card.*;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.fixture.ParticipantCardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BettingPlayersTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        final Deck deck = new CardDeck();
        final ParticipantCards participantsCards1 = ParticipantCardsFixture.create(deck, List.of());
        final ParticipantCards participantsCards2 = ParticipantCardsFixture.create(deck, List.of());
        final ParticipantCards participantsCards3 = ParticipantCardsFixture.create(deck, List.of());
        final Player player1 = new Player(participantsCards1, "헤나01");
        final Player player2 = new Player(participantsCards2, "헤나02");
        final Player player3 = new Player(participantsCards3, "헤나03");
        final List<Player> players = List.of(player1, player2, player3);
        final List<Money> moneys = List.of(Money.ZERO, Money.ZERO, Money.ZERO);

        assertThatNoException().isThrownBy(() -> new BettingPlayers(players, moneys));
    }

    @Test
    @DisplayName("플레이어 목록이 비어있을 경우 예외가 발생한다.")
    void throwExceptionWhenPlayersIsEmpty() {
        assertThatThrownBy(() -> new BettingPlayers(Collections.emptyList(), List.of(Money.ZERO)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("돈 목록이 비어있을 경우 예외가 발생한다.")
    void throwExceptionWhenMoneysIsEmpty() {
        final Deck deck = new CardDeck();
        final ParticipantCards participantsCards = ParticipantCardsFixture.create(deck, List.of());
        final Player player = new Player(participantsCards, "헤나");

        assertThatThrownBy(() -> new BettingPlayers(List.of(player), Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("decideBetResultByDummy")
    @DisplayName("배팅 결과를 계산한다.")
    void decideBetResultBy(
            final Card playerCard1,
            final Card playerCard2,
            final List<Card> playerAdditionalCards,
            final Card dealerCard1,
            final Card dealerCard2,
            final List<Card> dealerAdditionalCards,
            final int playerBeforeBetMoney,
            final int playerAfterBetMoney
    ) {
        final ParticipantCards dealerCards = ParticipantCardsFixture.create(dealerCard1, dealerCard2, dealerAdditionalCards);
        final ParticipantCards participantCard = ParticipantCardsFixture.create(playerCard1, playerCard2, playerAdditionalCards);

        final Dealer dealer = new Dealer(dealerCards);
        final Player player = new Player(participantCard, "헤나");
        final BettingPlayers bettingPlayers = new BettingPlayers(List.of(player), List.of(new Money(playerBeforeBetMoney)));

        final Map<Participant, Money> players = bettingPlayers.findBettingResultsBy(dealer);
        final Money currentMoney = players.get(player);

        assertThat(currentMoney.getValue()).isEqualTo(playerAfterBetMoney);
    }

    @Test
    @DisplayName("플레이어 리스트를 가져온다.")
    void getPlayers() {
        final Deck deck = new CardDeck();
        final ParticipantCards cards = ParticipantCardsFixture.create(deck, List.of());
        final Player player = new Player(cards, "헤나");
        final BettingPlayers bettingPlayers = new BettingPlayers(List.of(player), List.of(Money.ZERO));
        final List<Player> players = bettingPlayers.getPlayers();

        assertThat(players).contains(player);
    }

    static Stream<Arguments> decideBetResultByDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 플레이어 패배, 플레이어 딜러 모두 버스트하는 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.QUEEN),
                        List.of(new Card(CardShape.SPADE, CardNumber.JACK),
                                new Card(CardShape.HEART, CardNumber.KING)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.QUEEN),
                        List.of(new Card(CardShape.CLOVER, CardNumber.JACK),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 금액
                        0
                ),
                Arguments.arguments(
                        // 플레이어 패배, 플레이어만 버스트하는 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.QUEEN),
                        List.of(new Card(CardShape.SPADE, CardNumber.JACK),
                                new Card(CardShape.HEART, CardNumber.KING)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.THREE),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 금액
                        0
                ),
                Arguments.arguments(
                        // 플레이어 승리, 플레이어의 점수가 딜러보다 높을 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.THREE),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.TWO),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 금액
                        20000
                ),
                Arguments.arguments(
                        // 플레이어 패배, 플레이어의 점수보다 딜러가 높을 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.TWO),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.THREE),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 금액
                        0
                ),
                Arguments.arguments(
                        // 플레이어 패배, 플레이어와 딜러의 점수가 동일하고 블랙잭이 아닐 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.TWO),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.TWO),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 금액
                        0
                ),
                Arguments.arguments(
                        // 플레이어 무승부, 플레이어와 딜러의 점수가 블랙잭일 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.FOUR),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.FOUR),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 금액
                        10000
                ),
                Arguments.arguments(
                        // 플레이어 승리, 플레이어가 블랙잭이고 딜러는 아닐 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.FOUR),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.THREE),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 배팅 이전 금액
                        10000,
                        // 플레이어 배팅 이후 금액
                        25000
                )
        );
    }
}
