package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 점수가_21점을_초과하면_버스트이다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        boolean result = player.isBust();

        assertEquals(true, result);
    }

    @Test
    void 점수가_21점이면_버스트가_아니다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.NINE));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        boolean result = player.isBust();

        assertEquals(false, result);
    }

    @Test
    void 점수가_21점_미만이면_카드를_추가로_받을_수_있다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.EIGHT));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        boolean result = player.isContinueGame();

        assertEquals(true, result);
    }

    @Test
    void 점수가_21점_이상이면_카드를_추가로_받을_수_없다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.NINE));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        boolean result = player.isContinueGame();

        assertEquals(false, result);
    }

    @Test
    void 딜러보다_점수가_높으면_승리한다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.EIGHT));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.WIN, result);
    }

    @Test
    void 딜러와_점수가_같으면_무승부이다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.JACK));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.DRAW, result);
    }

    @Test
    void 딜러보다_점수가_낮으면_패배한다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TEN));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.JACK));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.LOSE, result);
    }

    @Test
    void 플레이어가_버스트이면_패배한다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TWO));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.CLUB, Number.JACK));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.EIGHT));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.LOSE, result);
    }

    @Test
    void 딜러가_버스트이면_승리한다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer(new Name("딜러"));

        player.receiveCard(new Card(Shape.SPADE, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));

        dealer.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        dealer.receiveCard(new Card(Shape.CLUB, Number.TWO));
        dealer.receiveCard(new Card(Shape.HEART, Number.TEN));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.WIN, result);
    }
}
