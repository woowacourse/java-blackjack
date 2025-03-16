package blackjack.domain.user;

import static blackjack.fixture.CardFixture.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.game.GameResultType;
import blackjack.domain.game.PlayerProfit;
import blackjack.domain.value.BettingAmount;
import blackjack.domain.value.Nickname;
import blackjack.fixture.DealerFixture;
import blackjack.fixture.PlayerFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    Player player = new Player(new Nickname("플레이어"));

    @Test
    @DisplayName("배팅 금액을 추가할 수 있다.")
    void canAddBettingAmount() {
        BettingAmount expectedBettingAmount = new BettingAmount(1000);
        player.addBettingAmount(expectedBettingAmount);

        assertThat(player.getBettingAmount()).isEqualTo(expectedBettingAmount.getValue());
    }

    @Test
    @DisplayName("초기 카드를 추가할 수 있다.")
    void canAddInitialCards() {
        List<Card> initialCards = List.of(
                new Card(CardShape.HEART, CardValue.EIGHT),
                new Card(CardShape.CLOVER, CardValue.EIGHT));

        player.addInitialCards(initialCards);

        assertThat(player.getHand()).hasSize(2);
    }

    @Test
    @DisplayName("히트를 할 수 있다.")
    void canHitUntilLimit() {
        Card newCard = new Card(CardShape.HEART, CardValue.EIGHT);

        player.hitUntilLimit(newCard);

        assertAll(
                () -> assertThat(player.getHand()).hasSize(1),
                () -> assertThat(player.getHand().getFirst()).isEqualTo(newCard));
    }

    @Test
    @DisplayName("히트가 불가능한 상태에서 히트를 수행할 경우 예외가 발생한다.")
    void cannotHitUntilLimit() {
        List<Card> initialCards = List.of(make(CardValue.KING), make(CardValue.JACK), make(CardValue.ACE));
        player.addInitialCards(initialCards);

        player.hitUntilLimit(make(CardValue.ACE));

        assertThat(player.getPoint()).isEqualTo(21);
    }

    @ParameterizedTest
    @DisplayName("히트 가능 여부를 확인할 수 있다.")
    @MethodSource()
    void checkCanHit(List<Card> cards, boolean expectedIsHitPossible) {
        player.addInitialCards(cards);

        boolean actualIsHitPossible = player.canHit();

        assertThat(actualIsHitPossible).isEqualTo(expectedIsHitPossible);
    }

    static Stream<Arguments> checkCanHit() {
        return Stream.of(
                Arguments.of(List.of(make(CardValue.EIGHT), make(CardValue.EIGHT)), true),
                Arguments.of(List.of(make(CardValue.EIGHT), make(CardValue.EIGHT), make(CardValue.EIGHT)), false),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.QUEEN)), false),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.QUEEN), make(CardValue.KING)), false),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.ACE)), true),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.ACE), make(CardValue.ACE)), true),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.ACE), make(CardValue.QUEEN),
                        make(CardValue.KING)), false)
        );
    }

    @Test
    @DisplayName("플레이어가 초기카드로 블랙잭을 만들 경우 수익을 구할 수 있다.")
    void canCalculateProfitWhenBlackjack() {
        Dealer dealer = DealerFixture.createBust();
        Player player = PlayerFixture.createBlackJackWithInitialHand("플레이어");

        PlayerProfit profit = player.calculateProfit(dealer.getPoint());

        int expectedProfit = PlayerFixture.DEFAULT_BETTING_AMOUNT.calculateMultiplication(
                GameResultType.WIN_WITH_INITIAL_HAND_BLACKJACK.getProfitRate());
        assertAll(
                () -> assertThat(profit.getNickname()).isEqualTo("플레이어"),
                () -> assertThat(profit.getProfit()).isEqualTo(expectedProfit));
    }

    @ParameterizedTest
    @DisplayName("딜러가 버스트일 경우 플레이어의 수익을 구할 수 있다.")
    @MethodSource()
    void canCalculateProfitWhenDealerBust(Player player, GameResultType expectedType) {
        Dealer dealer = DealerFixture.createBust();

        PlayerProfit profit = player.calculateProfit(dealer.getPoint());

        int expectedProfit = PlayerFixture.DEFAULT_BETTING_AMOUNT.calculateMultiplication(expectedType.getProfitRate());
        assertAll(
                () -> assertThat(profit.getNickname()).isEqualTo(player.getNickname()),
                () -> assertThat(profit.getProfit()).isEqualTo(expectedProfit));
    }

    static Stream<Arguments> canCalculateProfitWhenDealerBust() {
        return Stream.of(
                Arguments.of(PlayerFixture.createBust("플레이어1"),
                        GameResultType.WIN),
                Arguments.of(PlayerFixture.createBlackJackWithInitialHand("플레이어2"),
                        GameResultType.WIN_WITH_INITIAL_HAND_BLACKJACK),
                Arguments.of(PlayerFixture.createBlackJackWithFinalHand("플레이어3"),
                        GameResultType.WIN),
                Arguments.of(PlayerFixture.createLessThanBlackjack("플레이어4"),
                        GameResultType.WIN)
        );
    }

    @ParameterizedTest
    @DisplayName("승패에 따라 플레이어의 수익을 구할 수 있다.")
    @MethodSource()
    void canCalculateProfitByGameResult(Dealer dealer, Player player, GameResultType expectedType) {
        PlayerProfit profit = player.calculateProfit(dealer.getPoint());

        int expectedProfit = PlayerFixture.DEFAULT_BETTING_AMOUNT.calculateMultiplication(expectedType.getProfitRate());
        assertAll(
                () -> assertThat(profit.getNickname()).isEqualTo("플레이어"),
                () -> assertThat(profit.getProfit()).isEqualTo(expectedProfit));
    }

    static Stream<Arguments> canCalculateProfitByGameResult() {
        return Stream.of(
                Arguments.of(
                        DealerFixture.createBlackJackWithFinalHand(),
                        PlayerFixture.createBlackJackWithFinalHand("플레이어"), GameResultType.DRAW),
                Arguments.of(
                        DealerFixture.createBlackJackWithFinalHand(),
                        PlayerFixture.createLessThanBlackjack("플레이어"), GameResultType.LOSE),
                Arguments.of(
                        DealerFixture.createLessThanBlackjack(),
                        PlayerFixture.createBlackJackWithFinalHand("플레이어"), GameResultType.WIN)
        );
    }
}