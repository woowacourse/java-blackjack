package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.result.Result;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PlayerTest {

    @DisplayName("플레이어 이름과 배팅 금액으로 플레이어를 생성한다.")
    @Test
    void createPlayer() {
        //given
        Name name = new Name("ted");
        int betAmount = 1000;

        //when
        Player player = new Player(name, betAmount);
        String playerName = player.name();
        int playerBetAmount = player.betAmount();

        //then
        assertAll(
                () -> assertThat(playerName).isEqualTo(name.getName()),
                () -> assertThat(playerBetAmount).isEqualTo(betAmount)
        );
    }

    @DisplayName("플레이어가 카드를 받는다.")
    @Test
    void receiveCard() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Card card = new Card(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        player.receiveCard(card);
        List<Card> playerDeck = player.allCards();

        //then
        assertThat(playerDeck).containsExactly(card);
    }

    @DisplayName("플레이어의 카드 개수를 확인한다.")
    @Test
    void deckSize() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.FIVE);
        Card card2 = new Card(CardPattern.CLOVER, CardNumber.SEVEN);

        //when
        player.receiveCard(card1);
        player.receiveCard(card2);
        int deckSize = player.deckSize();

        //then
        assertThat(deckSize).isEqualTo(2);
    }

    @DisplayName("플레이어의 카드합을 확인한다.")
    @Test
    void calculateTotalScore() {
        //given
        Player player = new Player(new Name("ted"), 100);
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.FIVE);
        Card card2 = new Card(CardPattern.CLOVER, CardNumber.SEVEN);

        //when
        player.receiveCard(card1);
        player.receiveCard(card2);
        int totalScore = player.totalScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }

    @DisplayName("플레이어에 게임 결과에 따른 수익금을 등록한다.")
    @ParameterizedTest
    @EnumSource(Result.class)
    void applyResult(Result gameResult) {
        //given
        int betAmount = 1000;
        Player player = new Player(new Name("ted"), betAmount);
        int expectedProfitAmount = (int) (betAmount * gameResult.getPayoutRate());

        //when
        player.applyResult(gameResult);
        int profitAmount = player.profitAmount();

        //then
        assertThat(profitAmount).isEqualTo(expectedProfitAmount);
    }

    @DisplayName("플레이어에 게임 결과에 따른 순수익을 확인한다.")
    @ParameterizedTest
    @EnumSource(Result.class)
    void netProfit(Result gameResult) {
        //given
        int betAmount = 1000;
        Player player = new Player(new Name("ted"), betAmount);
        int profitAmount = (int) (betAmount * gameResult.getPayoutRate());
        int expectedNetProfit = profitAmount - betAmount;

        //when
        player.applyResult(gameResult);
        int netProfit = player.netProfit();

        //then
        assertThat(netProfit).isEqualTo(expectedNetProfit);
    }
}
