package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.CardDistributor;
import blackjack.domain.CardDistributorForTest;
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

    private CardDistributor cardDistributor;

    @BeforeEach
    void setUp() {
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT),
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT),
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)));
        cardDistributor = new CardDistributor(deck);
    }

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

        cardDistributor.distributeCardTo(root);
        assertThat(root.getHand()).containsExactly(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN));
    }

    @Test
    @DisplayName("버스트 경우 계속 드로우 하려고 하는 경우 예외처리")
    void drawCardOverSix() {
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.QUEEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN),
            Card.valueOf(Shape.SPADE, CardValue.FIVE),
            Card.valueOf(Shape.SPADE, CardValue.TWO)));
        cardDistributor = new CardDistributor(deck);

        Player root = new Player(new Name("root"), Betting.valueOf("1"));
        for (int i = 0; i < 3; i++) {
            cardDistributor.distributeCardTo(root);
        }

        assertThatIllegalStateException().isThrownBy(() ->
            cardDistributor.distributeCardTo(root))
            .withMessage("더 이상 카드를 뽑을 수 없는 플레이어입니다.");
    }

    @Test
    @DisplayName("유저가 카드를 계속 더 받을건지 입력")
    void willContinue() {
        Player root = new Player(new Name("root"), Betting.valueOf("1"));

        root.updateStatusByResponse(Response.getResponse("y"));
        assertThat(root.isContinue()).isTrue();

        root.updateStatusByResponse(Response.getResponse("n"));
        assertThat(root.isContinue()).isFalse();

        assertThatThrownBy(() ->
            root.updateStatusByResponse(Response.getResponse("x")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("불가능한 입력 입니다.");
    }

    @Test
    @DisplayName("승패 결과")
    void match() {
        CardDistributorForTest cardDistributorForTest = CardDistributorForTest
            .valueOf(cardDistributor);
        Dealer dealer = new Dealer();
        cardDistributorForTest.distributeCardsTo(dealer, 2);
        Player pobi = new Player(new Name("pobi"), Betting.valueOf("1"));
        cardDistributorForTest.distributeCardsTo(pobi, 2);
        Player jason = new Player(new Name("jason"), Betting.valueOf("1"));
        cardDistributorForTest.distributeCardsTo(jason, 2);
        Player root = new Player(new Name("root"), Betting.valueOf("1"));
        cardDistributorForTest.distributeCardsTo(root, 2);

        assertThat(pobi.match(dealer)).isEqualTo(ResultType.WIN);
        assertThat(jason.match(dealer)).isEqualTo(ResultType.TIE);
        assertThat(root.match(dealer)).isEqualTo(ResultType.LOSE);
    }

    @Test
    @DisplayName("승패 결과에 따른 수익 계산")
    void matchForProfit() {
        CardDistributorForTest cardDistributorForTest =
            CardDistributorForTest.valueOf(cardDistributor);
        Dealer dealer = new Dealer();
        cardDistributorForTest.distributeCardsTo(dealer, 2);
        Player pobi = new Player(new Name("pobi"), Betting.valueOf("1000"));
        cardDistributorForTest.distributeCardsTo(pobi, 2);
        Player jason = new Player(new Name("jason"), Betting.valueOf("1000"));
        cardDistributorForTest.distributeCardsTo(jason, 2);
        Player root = new Player(new Name("root"), Betting.valueOf("1000"));
        cardDistributorForTest.distributeCardsTo(root, 2);

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
