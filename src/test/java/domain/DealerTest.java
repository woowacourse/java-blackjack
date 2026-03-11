package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vo.GameResult;
import vo.Rank;
import vo.Suit;

public class DealerTest {
    @ParameterizedTest
    @DisplayName("딜러의 점수가 21점 미만인 경우, 승패를 판정한다.")
    @CsvSource(
            value = {
                    "19, WIN",
                    "21, BLACKJACK",
                    "22, LOSE"
            }
    )
    void 딜러_21점_미만_테스트(int userScore, GameResult expectedGameResult) {
        // given 딜러 18점
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.CLUB, Rank.QUEEN),
                new Card(Suit.CLUB, Rank.EIGHT)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(userScore);

        // then
        assertThat(gameResult).isEqualTo(expectedGameResult);
    }

    @ParameterizedTest
    @DisplayName("딜러의 점수가 21점인 경우, 승패를 판정한다.")
    @CsvSource(
            value = {
                    "19, LOSE_BY_BLACKJACK",
                    "20, LOSE_BY_BLACKJACK",
                    "21, DRAW",
                    "22, LOSE"
            }
    )
    void 딜러_21점_테스트(int userScore, GameResult expectedGameResult) {
        // given 딜러 21점
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.CLUB, Rank.QUEEN),
                new Card(Suit.CLUB, Rank.ACE)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(userScore);

        // then
        assertThat(gameResult).isEqualTo(expectedGameResult);
    }

    @ParameterizedTest
    @DisplayName("딜러의 점수가 21점 초과인 경우, 승패를 판정한다.")
    @CsvSource(
            value = {
                    "19, WIN",
                    "20, WIN",
                    "21, BLACKJACK",
                    "22, LOSE"
            }
    )
    void 딜러_21점_초과_테스트(int userScore, GameResult expectedGameResult) {
        // given 딜러 30점
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.CLUB, Rank.QUEEN),
                new Card(Suit.CLUB, Rank.JACK),
                new Card(Suit.CLUB, Rank.KING)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(userScore);

        // then
        assertThat(gameResult).isEqualTo(expectedGameResult);
    }

    private Dealer createDealerWithCards(List<Card> cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.receiveCard(card);
        }
        dealer.calculateScore();
        return dealer;
    }
}
