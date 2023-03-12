package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.K;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static blackjack.domain.participant.Players.PLAYERS_COUNT_ERROR_MESSAGE;
import static blackjack.domain.result.Result.WIN;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class PlayersTest {

    private final List<Player> playerData = new ArrayList<>(List.of(
            new Player("player1"),
            new Player("player2")
    ));
    private Players players;

    @BeforeEach
    void setUp() {
        this.players = new Players(playerData);
    }

    @ParameterizedTest(name = PLAYERS_COUNT_ERROR_MESSAGE + "{0}")
    @ValueSource(ints = {1, 9})
    void create_invalidCount(int playerCount) {
        List<Player> players = IntStream.range(0, playerCount)
                .mapToObj(x -> new Player(String.valueOf(playerCount)))
                .collect(toList());

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Players(players)
        ).withMessage(PLAYERS_COUNT_ERROR_MESSAGE + players);
    }

    @Test
    @DisplayName("플레이어의 수와 다른 갯수의 Hand를 받으면 예외가 발생한다.")
    void distributeHandsTest_different_size_exception() {
        Hand hand = new Hand(List.of(new Card(ACE, HEART), new Card(TWO, SPADE)));
        List<Hand> hands = new ArrayList<>(List.of(hand));

        assertThatIllegalArgumentException().isThrownBy(
                () -> players.distributeHands(hands)
        ).withMessage("세팅 카드와 플레이어의 수가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("플레이어의 수에 일치한 Hands를 받으면 2장의 카드를 분배한다.")
    void distributeHandsTest() {
        Hand hand1 = new Hand(List.of(new Card(ACE, HEART), new Card(TWO, SPADE)));
        Hand hand2 = new Hand(List.of(new Card(ACE, HEART), new Card(TWO, SPADE)));
        List<Hand> hands = new ArrayList<>(List.of(hand1, hand2));

        assertThatNoException().isThrownBy(
                () -> players.distributeHands(hands)
        );
    }

    @Test
    @DisplayName("딜러가 블랙잭의 핸드를 가진 경우 compareTo를 테스트한다.")
    void compareHandToTest() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(K, HEART));
        dealer.receiveCard(new Card(TEN, HEART));
        dealer.receiveCard(new Card(TWO, HEART));

        Player player1 = playerData.get(0);
        player1.receiveCard(new Card(TWO, DIAMOND));
        player1.receiveCard(new Card(TWO, CLOVER));

        Player player2 = playerData.get(1);
        player2.receiveCard(new Card(TWO, DIAMOND));
        player2.receiveCard(new Card(TWO, CLOVER));

        Map<Player, Result> playersResult = players.compareHandTo(dealer.getHand());

        assertAll(
                () -> assertThat(playersResult.get(player1)).isEqualTo(WIN),
                () -> assertThat(playersResult.get(player2)).isEqualTo(WIN)
        );
    }
}
