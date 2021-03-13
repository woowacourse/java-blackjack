package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    private static final Card aceCard = new Card(Suit.CLOVER, Rank.ACE);
    private static final Card kingCard = new Card(Suit.CLOVER, Rank.KING);
    private static final Card sevenCard = new Card(Suit.CLOVER, Rank.SEVEN);

    private static final Hand blackjack = new Hand(Arrays.asList(aceCard, kingCard));
    private static final Hand twentyOne = new Hand(Arrays.asList(sevenCard, sevenCard, sevenCard));
    private static final Hand twenty = new Hand(Arrays.asList(kingCard, kingCard));
    private static final Hand bust = new Hand(Arrays.asList(kingCard, kingCard, kingCard));

    @ParameterizedTest(name = "상금 계산이 올바른지")
    @MethodSource("calculateTestcase")
    void calculate(Hand dealerHand, Hand playerHand, int dealerMoney, int playerMoney) {
        Dealer dealer = new Dealer(dealerHand);
        List<Player> players = createPlayers(playerHand);

        GameResult result = GameResult.calculate(dealer, players);
        assertThat(result.getDealerWinningMoney()).isEqualTo(dealerMoney);

        PlayerResult playersResult = result.getPlayersResults().get(0);
        assertThat(playersResult.getWinningMoney()).isEqualTo(playerMoney);
    }

    private static Stream<Arguments> calculateTestcase() {
        return Stream.of(
                // 딜러 승리
                Arguments.of(twentyOne, twenty, 10000, -10000),
                // 플레이어 승리
                Arguments.of(twenty, twentyOne, -10000, 10000),
                // 무승부
                Arguments.of(twentyOne, twentyOne, 0, 0),

                // 딜러 블랙잭 승리
                Arguments.of(blackjack, twentyOne, 10000, -10000),
                // 플레이어 블랙잭 승리
                Arguments.of(twentyOne, blackjack, -15000, 15000),
                // 블랙잭 무승부
                Arguments.of(blackjack, blackjack, 0, 0),

                // 딜러 버스트, 플레이어 승리
                Arguments.of(bust, twentyOne, -10000, 10000),
                // 딜러 버스트, 플레이어 버스트
                Arguments.of(bust, bust, 10000, -10000),
                // 딜러 승리, 플레이어 버스트
                Arguments.of(twentyOne, bust, 10000, -10000)
        );
    }

    private static List<Player> createPlayers(Hand hand) {
        return Collections.singletonList(new Player(new Name("joy"), hand, new Money(10000)));
    }
}