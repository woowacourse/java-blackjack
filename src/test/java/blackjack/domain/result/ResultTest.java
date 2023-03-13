package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.ChallengerName;
import blackjack.domain.player.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.dto.ChallengerNameAndMoneyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    private Players players;
    private Result result;

    void setPlayers() {
        ChallengerNameAndMoneyDto ditoo = new ChallengerNameAndMoneyDto(new ChallengerName("ditoo"), Money.from(1000));
        ChallengerNameAndMoneyDto bada = new ChallengerNameAndMoneyDto(new ChallengerName("bada"), Money.from(1000));
        ChallengerNameAndMoneyDto oing = new ChallengerNameAndMoneyDto(new ChallengerName("oing"), Money.from(1000));
        players = Players.from(List.of(ditoo, bada, oing));
        setBustedChallenger(players.getChallengers().get(0));
        setBlackjackChallenger(players.getChallengers().get(1));
        setLowPointChallenger(players.getChallengers().get(2));
    }

    void setBustedChallenger(Player bustedChallenger) {
        Card cloverKing = new Card(Shape.CLOVER, Number.KING);
        Card cloverSeven = new Card(Shape.CLOVER, Number.SEVEN);
        Card cloverEight = new Card(Shape.CLOVER, Number.EIGHT);

        bustedChallenger.pickStartCards(cloverKing, cloverSeven);
        bustedChallenger.pick(cloverEight);
    }

    void setBlackjackChallenger(Player blackjackChallenger) {
        Card heartQueen = new Card(Shape.HEART, Number.QUEEN);
        Card heartAce = new Card(Shape.HEART, Number.ACE);

        blackjackChallenger.pickStartCards(heartQueen, heartAce);
    }

    void setLowPointChallenger(Player lowPointChallenger) {
        Card diamondTwo = new Card(Shape.DIAMOND, Number.TWO);
        Card diamondEight = new Card(Shape.DIAMOND, Number.EIGHT);

        lowPointChallenger.pickStartCards(diamondTwo, diamondEight);
    }

    @Nested
    @DisplayName("딜러가 bust 라면")
    class DealerBust {

        @BeforeEach
        void setup() {
            setPlayers();
            setDealer();
            setResult();
        }

        void setDealer() {
            Player dealer = players.getDealer();

            Card spadeKing = new Card(Shape.SPADE, Number.KING);
            Card spadeFive = new Card(Shape.SPADE, Number.FIVE);
            Card spadeJack = new Card(Shape.SPADE, Number.JACK);

            dealer.pickStartCards(spadeKing, spadeFive);
            dealer.pick(spadeJack);
        }

        void setResult() {
            result = Result.from(players);
        }

        @Test
        @DisplayName("bust인 도전자는 수익이 0원이다")
        void busted_challenger_draw_with_dealer() {
            Challenger bustedChallenger = players.getChallengers().get(0);
            Money bettingMoney = bustedChallenger.getMoney();
            double rate = Rank.DRAW.getRateOfReturn();

            assertThat(result.getChallengerProfit(bustedChallenger)).isEqualTo(bettingMoney.multiplyBy(rate));
        }

        @Test
        @DisplayName("blackjack인 도전자는 배팅액의 1.5배를 받는다")
        void blackjack_challenger_win_dealer() {
            Challenger blackjackChallenger = players.getChallengers().get(1);
            Money bettingMoney = blackjackChallenger.getMoney();
            double rate = Rank.BLACKJACK.getRateOfReturn();

            assertThat(result.getChallengerProfit(blackjackChallenger)).isEqualTo(bettingMoney.multiplyBy(rate));
        }

        @Test
        @DisplayName("작은 점수를 획득한 도전자는 딜러를 이긴다")
        void low_point_challenger_win_dealer() {
            Challenger lowPointChallenger = players.getChallengers().get(2);
            Money bettingMoney = lowPointChallenger.getMoney();
            double rate = Rank.WIN.getRateOfReturn();

            assertThat(result.getChallengerProfit(lowPointChallenger)).isEqualTo(bettingMoney.multiplyBy(rate));
        }

        @Test
        @DisplayName("딜러의 총 수입은 -2,500원 이다")
        void dealer_result() {
            assertThat(result.getDealerProfit().getAmount()).isEqualTo(-2500);
        }
    }

    @Nested
    @DisplayName("딜러가 19점 이라면")
    class DealerPointIsTwenty {

        @BeforeEach
        void setup() {
            setPlayers();
            setDealer();
            setResult();
        }

        void setDealer() {
            Player dealer = players.getDealer();

            Card spadeKing = new Card(Shape.SPADE, Number.KING);
            Card spadeNine = new Card(Shape.SPADE, Number.NINE);

            dealer.pickStartCards(spadeKing, spadeNine);
        }

        void setResult() {
            result = Result.from(players);
        }

        @Test
        @DisplayName("bust인 도전자는 배팅액을 잃는다")
        void busted_challenger_draw_with_dealer() {
            Challenger bustedChallenger = players.getChallengers().get(0);
            Money bettingMoney = bustedChallenger.getMoney();
            double rate = Rank.LOSE.getRateOfReturn();

            assertThat(result.getChallengerProfit(bustedChallenger)).isEqualTo(bettingMoney.multiplyBy(rate));
        }

        @Test
        @DisplayName("blackjack인 도전자는 1.5배의 배팅액을 받는다")
        void blackjack_challenger_win_dealer() {
            Challenger blackjackChallenger = players.getChallengers().get(1);
            Money bettingMoney = blackjackChallenger.getMoney();
            double rate = Rank.BLACKJACK.getRateOfReturn();
            System.out.println(result.getChallengerProfit(blackjackChallenger).getAmount());

            assertThat(result.getChallengerProfit(blackjackChallenger)).isEqualTo(bettingMoney.multiplyBy(rate));
        }

        @Test
        @DisplayName("작은 점수를 획득한 도전자는 배팅액을 잃는다")
        void low_point_challenger_win_dealer() {
            Challenger lowPointChallenger = players.getChallengers().get(2);
            Money bettingMoney = lowPointChallenger.getMoney();
            double rate = Rank.LOSE.getRateOfReturn();

            assertThat(result.getChallengerProfit(lowPointChallenger)).isEqualTo(bettingMoney.multiplyBy(rate));
        }

        @Test
        @DisplayName("같은 점수의 도전자는 수익이 없다")
        void draw_with_same_score() {
            Player sameScoreChallenger = players.getChallengers().get(2);
            Card diamondNine = new Card(Shape.DIAMOND, Number.NINE);
            sameScoreChallenger.pick(diamondNine);
            result = Result.from(players);

            assertThat(result.getChallengerProfit(sameScoreChallenger)).isEqualTo(Money.zero());
        }

        @Test
        @DisplayName("딜러의 수익은 500원이다")
        void dealer_result() {
            assertThat(result.getDealerProfit().getAmount()).isEqualTo(500);
        }
    }
}
