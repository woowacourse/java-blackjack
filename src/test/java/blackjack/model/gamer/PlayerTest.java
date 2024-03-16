package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어 이름으로 플레이어를 생성한다.")
    @Test
    void createPlayer() {
        //given
        String name = "ted";

        //when
        Player player = new Player(name);
        String playerName = player.getName();

        //then
        assertThat(playerName).isEqualTo(name);
    }

    @DisplayName("플레이어가 카드를 받는다.")
    @Test
    void receiveCard() {
        //given
        Player player = new Player("ted");
        Card card = new Card(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        player.receiveCard(card);
        List<Card> playerDeck = player.getCards();

        //then
        assertThat(playerDeck).containsExactly(card);
    }

    @DisplayName("플레이어의 카드 개수를 확인한다.")
    @Test
    void deckSize() {
        //given
        Player player = new Player("ted");
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.FIVE);
        Card card2 = new Card(CardPattern.CLOVER, CardNumber.SEVEN);

        //when
        player.receiveCard(card1);
        player.receiveCard(card2);
        int deckSize = player.deckSize();

        //then
        assertThat(deckSize).isEqualTo(2);
    }

    @DisplayName("플레이어의 점수를 계산한다.")
    @Test
    void calculateTotalScore() {
        //given
        Player player = new Player("ted");
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.FIVE);
        Card card2 = new Card(CardPattern.CLOVER, CardNumber.SEVEN);

        //when
        player.receiveCard(card1);
        player.receiveCard(card2);
        int totalScore = player.calculateScore().getScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }

    @DisplayName("플레이어의 스코어가 21 미만이라면 히트할 수 있다.")
    @Test
    void canHitByExpectedTrue() {
        //given
        Player player = new Player("ted");
        Card card = new Card(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        player.receiveCard(card);

        //then
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("플레이어의 스코어가 21 이상이라면 히트할 수 없다.")
    @Test
    void canHitByExpectedFalse() {
        //given
        Player player = new Player("ted");
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.TEN);
        Card card2 = new Card(CardPattern.SPADE, CardNumber.TEN);
        Card card3 = new Card(CardPattern.CLOVER, CardNumber.ACE);

        //when
        player.receiveCard(card1);
        player.receiveCard(card2);
        player.receiveCard(card3);

        //then
        assertThat(player.canHit()).isFalse();
    }
//    @DisplayName("플레이어에 게임 결과에 따른 수익금을 등록한다.")
//    @ParameterizedTest
//    @EnumSource(Result.class)
//    void applyResult(Result gameResult) {
//        //given
//        int betAmount = 1000;
//        Player player = new Player(new Name("ted"), betAmount);
//        int expectedProfitAmount = (int) (betAmount * gameResult.getPayoutRate());
//
//        //when
//        player.applyResult(gameResult);
//        int profitAmount = player.profitAmount();
//
//        //then
//        assertThat(profitAmount).isEqualTo(expectedProfitAmount);
//    }
//
//    @DisplayName("플레이어에 게임 결과에 따른 순수익을 확인한다.")
//    @ParameterizedTest
//    @EnumSource(Result.class)
//    void netProfit(Result gameResult) {
//        //given
//        int betAmount = 1000;
//        Player player = new Player(new Name("ted"), betAmount);
//        int profitAmount = (int) (betAmount * gameResult.getPayoutRate());
//        int expectedNetProfit = profitAmount - betAmount;
//
//        //when
//        player.applyResult(gameResult);
//        int netProfit = player.netProfit();
//
//        //then
//        assertThat(netProfit).isEqualTo(expectedNetProfit);
//    }
}
