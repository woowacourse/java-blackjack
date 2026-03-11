package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.GameResult;
import vo.Money;
import vo.Rank;
import vo.Suit;

public class DealerTest {
    @Test
    @DisplayName("유저가 21점을 초과하면, 무조건 패배한다.")
    void 유저_버스트_패배_테스트() {
        // given 딜러 18점
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.CLUB, Rank.QUEEN),
                new Card(Suit.CLUB, Rank.EIGHT)
        ));

        // given 유저 25점
        User user = createUserWithCards(List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.HEART, Rank.FIVE)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(user);

        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 유저가 모두 2장으로 21점인 경우, 무승부가 된다.")
    void 둘_다_블랙잭인_경우_무승부_테스트() {
        // given
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.KING)
        ));

        // given
        User user = createUserWithCards(List.of(
                new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.HEART, Rank.KING)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(user);

        // then
        assertThat(gameResult).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고, 유저가 3장으로 21점인 경우, 딜러가 승리한다.")
    void 딜러_블랙잭_유저_블랙잭_아님_테스트() {
        // given
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.KING)
        ));

        // given
        User user = createUserWithCards(List.of(
                new Card(Suit.HEART, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.CLUB, Rank.SEVEN)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(user);

        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE_BY_BLACKJACK);
    }

    @Test
    @DisplayName("딜러가 21점을 초과하고, 유저는 21점 이하인 경우, 무조건 승리한다.")
    void 딜러_버스트_유저_승리_테스트() {
        // given 딜러 26점
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.CLUB, Rank.EIGHT)
        ));

        // given 유저 15점
        User user = createUserWithCards(List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.HEART, Rank.FIVE)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(user);

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러가 18점이고, 유저가 19점인 경우, 유저가 승리한다.")
    void 딜러_21점_미만_유저_승리_테스트() {
        // given 딜러 18점
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.CLUB, Rank.QUEEN),
                new Card(Suit.CLUB, Rank.EIGHT)
        ));

        // given 유저 19점
        User user = createUserWithCards(List.of(
                new Card(Suit.HEART, Rank.QUEEN),
                new Card(Suit.HEART, Rank.NINE)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(user);

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러가 18점이고, 유저가 2장으로 21점인 경우, 유저는 블랙잭으로 승리한다.")
    void 딜러_21점_미만_유저_블랙잭_테스트() {
        // given 딜러 18점
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.CLUB, Rank.QUEEN),
                new Card(Suit.CLUB, Rank.EIGHT)
        ));

        // given 유저 카드 2장, 21점
        User user = createUserWithCards(List.of(
                new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.HEART, Rank.KING)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(user);

        // then
        assertThat(gameResult).isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    @DisplayName("딜러가 18점이고, 유저가 3장으로 21점인 경우, 유저는 일반 승리한다.")
    void 딜러_21점_미만_유저_카드_세_장_블랙잭_아님_테스트() {
        // given 딜러 18점
        Dealer dealer = createDealerWithCards(List.of(
                new Card(Suit.CLUB, Rank.QUEEN),
                new Card(Suit.CLUB, Rank.EIGHT)
        ));

        // given 유저 카드 3장, 21점
        User user = createUserWithCards(List.of(
                new Card(Suit.HEART, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.CLUB, Rank.SEVEN)
        ));

        // when
        GameResult gameResult = dealer.judgeResultForUser(user);

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    private Dealer createDealerWithCards(List<Card> cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.receiveCard(card);
        }
        dealer.calculateScore();
        return dealer;
    }

    private User createUserWithCards(List<Card> cards) {
        User user = new User("라이", new Money(10000));
        for (Card card : cards) {
            user.receiveCard(card);
        }
        user.calculateScore();
        return user;
    }
}
