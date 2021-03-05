package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import blackjack.domain.vo.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PlayerTest {

    private static final List<Card> CARDS_SCORE_19 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.EIGHT, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_20 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.NINE, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_21 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.TEN, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_22 = Arrays.asList(
            new Card(Symbol.JACK, Shape.HEART),
            new Card(Symbol.TEN, Shape.HEART),
            new Card(Symbol.TWO, Shape.HEART)
    );
    private static final List<Card> CARDS_BLACKJACK = Arrays.asList(
            new Card(Symbol.JACK, Shape.HEART),
            new Card(Symbol.ACE, Shape.DIAMOND)
    );

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(CARDS_SCORE_20, true),
                Arguments.of(CARDS_SCORE_21, false)
        );
    }

    @DisplayName("플레이어의 배팅 금액은 양의 정수여야한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void isValidBettingMoney(int bettingMoney) {
        assertThatCode(() -> {
            new Player("jason", bettingMoney);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양의 정수여야합니다.");
    }

    @DisplayName("ACE를 1로 했을 때 카드 합이 21 미만일 경우 true, 그 이상인 경우 false를 반환한다.")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("generateData")
    void isAbleToReceiveCard(List<Card> inputCards, boolean result) {
        Cards cards = new Cards(inputCards);
        Player player = new Player("jason");

        player.receiveCards(cards);
        boolean isAbleToReceiveCard = player.isAbleToReceiveCard();

        assertThat(isAbleToReceiveCard).isEqualTo(result);
    }

    @DisplayName("둘 다 21을 초과하지 않을 경우, 플레이어가 딜러보다 점가 높아야 이긴다.")
    @Test
    void judgeResult_PlayerAndDealerScoreUnder21_PlayerWin() {
        Player player = new Player("json");
        Dealer dealer = new Dealer();

        player.receiveCards(new Cards(CARDS_SCORE_20));
        dealer.receiveCards(new Cards(CARDS_SCORE_19));
        Result result = player.judgeResult(dealer);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @DisplayName("둘 다 21을 초과하지 않을 경우, 딜러가 플레이어보다 점수가 높으면 플레이어가 패배한다.")
    @Test
    void judgeResult_PlayerAndDealerScoreUnder21_PlayerLose() {
        Player player = new Player("json");
        Dealer dealer = new Dealer();

        player.receiveCards(new Cards(CARDS_SCORE_19));
        dealer.receiveCards(new Cards(CARDS_SCORE_20));
        Result result = player.judgeResult(dealer);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러가 21을 초과할 때 플레이어가 21을 초과하면 플레이어 패배")
    @Test
    void judgeResult_PlayerAndDealerScoreOver21() {
        Player player = new Player("json");
        Dealer dealer = new Dealer();

        player.receiveCards(new Cards(CARDS_SCORE_22));
        dealer.receiveCards(new Cards(CARDS_SCORE_22));
        Result result = player.judgeResult(dealer);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러가 21을 초과할 때 플레이어가 21을 초과하지 않으면 플레이어 승")
    @Test
    void judgeResult_DealerScoreOver21() {
        Player player = new Player("json");
        Dealer dealer = new Dealer();

        player.receiveCards(new Cards(CARDS_SCORE_19));
        dealer.receiveCards(new Cards(CARDS_SCORE_22));
        Result result = player.judgeResult(dealer);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @DisplayName("딜러가 버스트일 때 : 플레이어가 버스트면 해당 플레이어는 배팅 금액을 잃는다")
    @Test
    void loseBettingMoney_BothBust() {
        int bettingMoney = 1000;
        Player player = new Player("json", bettingMoney);
        player.receiveCards(new Cards(CARDS_SCORE_22));
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(CARDS_SCORE_22));

        int profitMoney = player.calculateProfitMoney(dealer);

        assertThat(profitMoney).isEqualTo(-1 * bettingMoney);
    }

    @DisplayName("딜러가 버스트일 때 : 플레이어가 버스트가 아니면 패에 상관없이(블랙잭이더라도) 배팅 금액 원금을 는다")
    @Test
    void earnProfitMoney_DealerBust() {
        int bettingMoney = 1000;
        Player player = new Player("json", bettingMoney);
        player.receiveCards(new Cards(CARDS_BLACKJACK));
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(CARDS_SCORE_22));

        int profitMoney = player.calculateProfitMoney(dealer);

        assertThat(profitMoney).isEqualTo(bettingMoney);
    }
}
