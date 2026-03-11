package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 점수가 21점을 초과하면 isBurst는 true를 반환한다.")
    void isBurst_Score22_ReturnTrue() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        boolean result = player.isBurst();

        assertEquals(true, result);
    }

    @Test
    @DisplayName("플레이어의 점수가 21점이면 isBurst는 false를 반환한다.")
    void isBurst_Score21_ReturnFalse() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.NINE));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        boolean result = player.isBurst();

        assertEquals(false, result);
    }

    @Test
    @DisplayName("플레이어는 점수가 21점 미만일 때 카드를 더 받을 수 있다.")
    void isContinueGame_UnderScore21_ReturnTrue() {

        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.EIGHT));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        boolean result = player.isContinueGame();

        assertEquals(true, result);
    }

    @Test
    @DisplayName("플레이어는 점수가 21점 이상일 때 카드를 더 받을 수 없다.")
    void isContinueGame_OverScore21_ReturnFalse() {

        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.NINE));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        boolean result = player.isContinueGame();

        assertEquals(false, result);
    }

    @Test
    @DisplayName("둘 다 버스트되지 않고, 플레이어 점수가 딜러 점수보다 높으면 플레이어가 승리한다.")
    void isWin_PlayerScoreHigher_ReturnTrue() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.EIGHT));

        boolean result = player.isWin(dealer);

        assertEquals(true, result);
    }

    @Test
    @DisplayName("둘 다 버스트되지 않고, 플레이어 점수와 딜러 점수가 같으면 플레이어가 승리한다.")
    void isWin_PlayerScoreSame_ReturnTrue() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.JACK));

        boolean result = player.isWin(dealer);

        assertEquals(true, result);
    }

    @Test
    @DisplayName("둘 다 버스트되지 않고, 플레이어 점수보다 딜러 점수가 높으면 플레이어가 패배한다.")
    void isWin_DealerScoreHigher_ReturnFalse() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TEN));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.JACK));

        boolean result = player.isWin(dealer);

        assertEquals(false, result);
    }

    @Test
    @DisplayName("플레이어가 버스트 되면, 딜러 점수 상관없이 패배한다.")
    void isWin_PlayerBurst_ReturnFalse() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TWO));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.CLUB, Number.JACK));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.EIGHT));

        boolean result = player.isWin(dealer);

        assertEquals(false, result);
    }

    @Test
    @DisplayName("플레이어가 버스트 되지 않을 때, 딜러가 버스트되면 플레이어가 승리한다.")
    void isWin_DealerBurst_ReturnTrue() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.TWO));
        dealer.receiveCard(new Card(Shape.HEART, Number.TEN));

        boolean result = player.isWin(dealer);

        assertEquals(true, result);
    }
}
