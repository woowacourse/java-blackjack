package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class MatchRuleTest {
    private static final Card[] blackjackCards = new Card[]{ace, king};
    private static final Card[] bustCards = new Card[]{king, queen, jack};
    private static final Card[] maximumScoreCards = new Card[]{king, five, six};
    private static final Card[] minimumScoreCards = new Card[]{two, three};

    private Dealer dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player(Name.of("player"), Money.of(10000));
    }

    private static Stream<Arguments> cardsProvider() {
        return Stream.of(
                Arguments.of(blackjackCards, maximumScoreCards, ResultType.BLACKJACK_WIN, "only player has blackjack"),
                Arguments.of(maximumScoreCards, blackjackCards, ResultType.LOSE, "only dealer has blackjack"),
                Arguments.of(blackjackCards, blackjackCards, ResultType.DRAW, "both has blackjack"),
                Arguments.of(bustCards, bustCards, ResultType.LOSE, "both bust"),
                Arguments.of(bustCards, minimumScoreCards, ResultType.LOSE, "only player bust"),
                Arguments.of(minimumScoreCards, bustCards, ResultType.WIN, "only dealer bust"),
                Arguments.of(maximumScoreCards, minimumScoreCards, ResultType.WIN, "higher player's score"),
                Arguments.of(minimumScoreCards, maximumScoreCards, ResultType.LOSE, "higher dealer's score"),
                Arguments.of(maximumScoreCards, maximumScoreCards, ResultType.DRAW, "both has equal score")
        );
    }

    @DisplayName("모든 경우에 대한 승패 테스트를 진행한다.")
    @ParameterizedTest(name="{3}")
    @MethodSource("cardsProvider")
    void getMatchResultTest(Card[] playerCards, Card[] dealerCards, ResultType playerResult, String testCaseDescription) {
        for (Card card : playerCards) {
            player.draw(card);
        }
        for (Card card : dealerCards) {
            dealer.draw(card);
        }

        assertThat(MatchRule.getMatchResult(player, dealer)).isEqualTo(playerResult);
    }

}