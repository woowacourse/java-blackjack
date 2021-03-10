package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    private static final Card ace = new Card(Suit.CLOVER, Rank.ACE);
    private static final Card king = new Card(Suit.CLOVER, Rank.KING);

    @ParameterizedTest(name = "딜러 승무패 계산이 올바른지")
    @MethodSource("calculateTestcase")
    void calculate(Hand dealerHand, Hand playerHand, int win, int lose, int tie) {
        Dealer dealer = new Dealer(dealerHand);
        List<Player> players = createPlayers(playerHand);

        GameResult result = GameResult.calculate(dealer, players);

        DealerResult dealerResult = result.getDealerResult();
        assertThat(dealerResult.getWinCount()).isEqualTo(win);
        assertThat(dealerResult.getLoseCount()).isEqualTo(lose);
        assertThat(dealerResult.getTieCount()).isEqualTo(tie);
    }

    private static Stream<Arguments> calculateTestcase() {
        return Stream.of(
                // 딜러 승리
                Arguments.of(createHand(ace, king), createHand(king, king), 1, 0, 0),
                // 플레이어 승리
                Arguments.of(createHand(king, king), createHand(ace, king), 0, 1, 0),
                // 무승부
                Arguments.of(createHand(ace, king), createHand(ace, king), 0, 0, 1)
        );
    }

    private static List<Player> createPlayers(Hand... hands) {
        return Arrays.stream(hands)
                .map(hand -> new Player("플레이어", hand))
                .collect(Collectors.toList());
    }

    private static Hand createHand(Card... cards) {
        return new Hand(Arrays.asList(cards));
    }
}