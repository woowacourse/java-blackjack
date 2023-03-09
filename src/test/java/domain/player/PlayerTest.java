package domain.player;

import domain.blackjack.Result;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.of(Name.from("pobi"), BettingMoney.from(10000));
    }

    @DisplayName("플레이어는 카드를 받을 수 있다.")
    @Test
    void receiveCardSuccessTest() {
        player.receive(Card.of(CardShape.SPADE, CardRank.ACE));

        assertThat(player).extracting("cards")
                .asList()
                .hasSize(1);
    }

    @DisplayName("플레이어가 받은 카드의 점수를 확인할 수 있다.")
    @Test
    void calculateScoreSuccessTest() {
        player.receive(Card.of(CardShape.CLUB, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.THREE));

        assertThat(player.calculateScore())
                .isEqualTo(14);
    }

    @DisplayName("21을 넘은 경우 ACE는 1로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAce() {
        player.receive(Card.of(CardShape.CLUB, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.THREE));
        player.receive(Card.of(CardShape.HEART, CardRank.TEN));

        assertThat(player.calculateScore())
                .isEqualTo(14);
    }

    @DisplayName("21을 넘지 않는 경우 까지 ACE는 11로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAceUntilNotBusted() {
        player.receive(Card.of(CardShape.CLUB, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.TEN));

        assertThat(player.calculateScore())
                .isEqualTo(12);
    }

    @DisplayName("카드의 합이 21이 넘으면 버스트 된다.")
    @Test
    void isBustedSuccessTest() {
        player.receive(Card.of(CardShape.CLUB, CardRank.JACK));
        player.receive(Card.of(CardShape.HEART, CardRank.TEN));
        player.receive(Card.of(CardShape.HEART, CardRank.QUEEN));

        assertThat(player.isBusted()).isTrue();
    }

    @DisplayName("카드의 합이 21이 넘지 않으면 버스트 되지 않는다.")
    @Test
    void isNotBustedSuccessTest() {
        player.receive(Card.of(CardShape.CLUB, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.ACE));
        player.receive(Card.of(CardShape.SPADE, CardRank.ACE));

        assertThat(player.isBusted()).isFalse();
    }

    @DisplayName("게임에서 승리할 경우, 플레이어가 배팅한 금액의 수익이 생긴다.")
    @Test
    void updateBettingMoneyAboutWinSuccessTest() {
        int bettingMoney = player.getMoney();

        assertThat(player.getProfit(Result.WIN))
                .isEqualTo(bettingMoney);
    }

    @DisplayName("게임에서 패배할 경우, 플레이어가 배팅한 금액만큼 손해가 생긴다.")
    @Test
    void updateBettingMoneyAboutLoseSuccessTest() {
        int bettingMoney = player.getMoney();

        assertThat(player.getProfit(Result.LOSE))
                .isEqualTo(bettingMoney * -1);
    }

    @DisplayName("게임에서 비길 경우, 플레이어의 수익이 생기지 않는다.")
    @Test
    void updateBettingMoneyAboutDrawSuccessTest() {
        assertThat(player.getProfit(Result.DRAW))
                .isEqualTo(0);
    }

    @DisplayName("게임에서 블랙잭으로 승리할 경우, 플레이어는 배팅 금액의 1.5배의 수익을 얻는다.")
    @Test
    void updateBettingMoneyAboutWinWithBlackjackSuccessTest() {
        int moneyBeforeWin = player.getMoney();

        player.receive(Card.of(CardShape.CLUB, CardRank.ACE));
        player.receive(Card.of(CardShape.CLUB, CardRank.TEN));

        assertThat(player.getProfit(Result.WIN))
                .isEqualTo((int) (moneyBeforeWin * 1.5));
    }
}
