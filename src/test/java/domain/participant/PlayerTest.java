package domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Help;
import domain.card.Number;
import domain.card.Shape;
import domain.game.GameResult;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 점수가_21점을_초과하면_버스트이다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.TEN));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));
        player.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TWO));

        boolean result = player.isBust();

        assertEquals(true, result);
    }

    @Test
    void 점수가_21점이면_버스트가_아니다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.NINE));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));
        player.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TWO));

        boolean result = player.isBust();

        assertEquals(false, result);
    }

    @Test
    void 에이스와_10점카드를_받으면_블랙잭이다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.ACE));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));

        boolean result = player.isBlackJack();

        assertEquals(true, result);
    }

    @Test
    void 두장의_합이_21이아니면_블랙잭이_아니다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.JACK));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));

        boolean result = player.isBlackJack();

        assertEquals(false, result);
    }

    @Test
    void 세장의_합이_21이어도_블랙잭이_아니다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.EIGHT));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.THREE));

        boolean result = player.isBlackJack();

        assertEquals(false, result);
    }

    @Test
    void 점수가_21점_미만이면_카드를_추가로_받을_수_있다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.EIGHT));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));
        player.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TWO));

        boolean result = player.isContinueGame();

        assertEquals(true, result);
    }

    @Test
    void 점수가_21점_이상이면_카드를_추가로_받을_수_없다() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.NINE));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));
        player.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TWO));

        boolean result = player.isContinueGame();

        assertEquals(false, result);
    }

    @Test
    void 딜러보다_점수가_높으면_승리한다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer();

        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.TEN));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));

        dealer.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TEN));
        dealer.receiveCard(new Help(Shape.CLUB, domain.card.Number.EIGHT));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.WIN, result);
    }

    @Test
    void 딜러와_점수가_같으면_무승부이다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer();

        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.TEN));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));

        dealer.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TEN));
        dealer.receiveCard(new Help(Shape.CLUB, domain.card.Number.JACK));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.DRAW, result);
    }

    @Test
    void 딜러보다_점수가_낮으면_패배한다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer();

        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.TEN));

        dealer.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TEN));
        dealer.receiveCard(new Help(Shape.CLUB, domain.card.Number.JACK));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.LOSE, result);
    }

    @Test
    void 플레이어가_버스트이면_패배한다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer();

        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.TWO));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));
        player.receiveCard(new Help(Shape.CLUB, domain.card.Number.JACK));

        dealer.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TEN));
        dealer.receiveCard(new Help(Shape.CLUB, domain.card.Number.EIGHT));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.LOSE, result);
    }

    @Test
    void 딜러가_버스트이면_승리한다() {
        Player player = new Player(new Name("pobi"));
        Dealer dealer = new Dealer();

        player.receiveCard(new Help(Shape.SPADE, domain.card.Number.TEN));
        player.receiveCard(new Help(Shape.HEART, domain.card.Number.JACK));

        dealer.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TEN));
        dealer.receiveCard(new Help(Shape.CLUB, domain.card.Number.TWO));
        dealer.receiveCard(new Help(Shape.HEART, Number.TEN));

        GameResult result = player.judgeResult(dealer);

        assertEquals(GameResult.WIN, result);
    }
}
