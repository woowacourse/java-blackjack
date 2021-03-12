package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.CardDistributor;
import blackjack.domain.Response;
import blackjack.domain.ResultType;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Deck;
import blackjack.domain.cards.Shape;
import blackjack.domain.names.Name;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어 생성")
    void create() {
        Player root = new Player(new Name("root"), Betting.valueOf("1"));
        assertThat(root).isEqualTo(new Player(new Name("root"), Betting.valueOf("1")));
    }

    @Test
    @DisplayName("카드 한 장 뽑는 기능")
    void drawCard() {
        Player root = new Player(new Name("root"), Betting.valueOf("1"));
        root.draw(Card.valueOf(Shape.DIAMOND, CardValue.TEN));
        assertThat(root.getHand()).containsExactly(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN));
    }

    @Test
    @DisplayName("버스트 경우 계속 드로우 하려고 하는 경우 예외처리")
    void drawCardOverBust() {
        Player root = new Player(new Name("root"), Betting.valueOf("1"));

        assertThatIllegalStateException().isThrownBy(() ->
            root.draw(
                Card.valueOf(Shape.DIAMOND, CardValue.QUEEN),
                Card.valueOf(Shape.SPADE, CardValue.SEVEN),
                Card.valueOf(Shape.SPADE, CardValue.FIVE),
                Card.valueOf(Shape.SPADE, CardValue.TWO)
            )).withMessage("더 이상 카드를 뽑을 수 없는 플레이어입니다.");
    }

    @Test
    @DisplayName("유저가 카드를 계속 더 받을건지 입력")
    void willContinue() {
        Player root = new Player(new Name("root"), Betting.valueOf("1"));

        root.updateStateByResponse(Response.POSITIVE);
        assertThat(root.isContinue()).isTrue();

        root.updateStateByResponse(Response.NEGATIVE);
        assertThat(root.isContinue()).isFalse();
    }

    @Test
    @DisplayName("승패 결과에 따른 수익 계산")
    void matchForProfit() {
        Dealer dealer = new Dealer();
        dealer.draw(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT)
        );
        Player pobi = new Player(new Name("pobi"), Betting.valueOf("1000"));
        pobi.draw(
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT)
        );
        pobi.updateStateByResponse(Response.NEGATIVE);
        Player jason = new Player(new Name("jason"), Betting.valueOf("1000"));
        jason.draw(
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.EIGHT)
        );
        jason.updateStateByResponse(Response.NEGATIVE);
        Player root = new Player(new Name("root"), Betting.valueOf("1000"));
        root.draw(
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)
        );
        root.updateStateByResponse(Response.NEGATIVE);

        assertThat(pobi.matchForProfit(dealer)).isEqualTo(1000);
        assertThat(jason.matchForProfit(dealer)).isEqualTo(0);
        assertThat(root.matchForProfit(dealer)).isEqualTo(-1000);
    }

    @Test
    @DisplayName("플레이어가 버스트인 경우")
    void matchForBustPlayer() {
        Player root = new Player(new Name("root"), Betting.valueOf("1000"));
        root.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        root.draw(Card.valueOf(Shape.HEART, CardValue.TEN));
        root.draw(Card.valueOf(Shape.DIAMOND, CardValue.TEN));

        Dealer dealer = new Dealer();
        dealer.draw(Card.valueOf(Shape.SPADE, CardValue.SEVEN));
        dealer.draw(Card.valueOf(Shape.HEART, CardValue.SIX));
        dealer.draw(Card.valueOf(Shape.HEART, CardValue.QUEEN));
        assertThat(root.matchForProfit(dealer)).isEqualTo(-1000);

        dealer = new Dealer();
        dealer.draw(Card.valueOf(Shape.SPADE, CardValue.SEVEN));
        dealer.draw(Card.valueOf(Shape.HEART, CardValue.SEVEN));
        dealer.draw(Card.valueOf(Shape.DIAMOND, CardValue.SEVEN));
        assertThat(root.matchForProfit(dealer)).isEqualTo(-1000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우")
    void matchForBlackJackPlayer() {
        Player root = new Player(new Name("root"), Betting.valueOf("1000"));
        root.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        root.draw(Card.valueOf(Shape.HEART, CardValue.ACE));

        Dealer dealer = new Dealer();
        dealer.draw(Card.valueOf(Shape.SPADE, CardValue.SEVEN));
        dealer.draw(Card.valueOf(Shape.HEART, CardValue.SEVEN));
        dealer.draw(Card.valueOf(Shape.DIAMOND, CardValue.SEVEN));

        assertThat(root.matchForProfit(dealer)).isEqualTo(1500);

        dealer = new Dealer();
        dealer.draw(Card.valueOf(Shape.DIAMOND, CardValue.TEN));
        dealer.draw(Card.valueOf(Shape.CLOVER, CardValue.ACE));

        assertThat(root.matchForProfit(dealer)).isEqualTo(0);
    }
}
