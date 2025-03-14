package blackjack.domain.user;

import static blackjack.fixture.CardFixture.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.game.GameResultType;
import blackjack.domain.game.PlayerProfit;
import blackjack.exception.ExceptionMessage;
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
    void canRegisterBettingAmount() {
        BettingAmount expectedBettingAmount = new BettingAmount(1000);
        player.registerBettingAmount(expectedBettingAmount);

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
    void canHit() {
        Card newCard = new Card(CardShape.HEART, CardValue.EIGHT);

        player.hitUntilLimit(newCard);

        assertThat(player.getHand()).hasSize(1);
        assertThat(player.getHand().getFirst()).isEqualTo(newCard);
    }

    @Test
    @DisplayName("히트가 불가능한 상태에서 히트를 수행할 경우 예외가 발생한다.")
    void cannotHit() {
        List<Card> initialCards = List.of(make(CardValue.KING), make(CardValue.JACK), make(CardValue.ACE));
        player.addInitialCards(initialCards);

        assertThatCode(() -> player.hitUntilLimit(make(CardValue.ACE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.CANNOT_HIT.getContent());
    }

    @ParameterizedTest
    @DisplayName("히트 가능 여부를 확인할 수 있다.")
    @MethodSource()
    void canCalculateTotalPoint(List<Card> cards, boolean expectedIsHitPossible) {
        player.addInitialCards(cards);

        boolean actualIsHitPossible = player.canHit();

        assertThat(actualIsHitPossible).isEqualTo(expectedIsHitPossible);
    }

    static Stream<Arguments> canCalculateTotalPoint() {
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
    void canCreateWhenPlayerBlackjackWithInitialCard() {
        Dealer dealer = DealerFixture.createBust();
        Player player = PlayerFixture.createBlackJackWithInitialHand("플레이어");

        PlayerProfit profit = player.calculateProfit(dealer);

        int expectedProfit = GameResultType.WIN_WITH_INITIAL_HAND_BLACKJACK.calculateProfit(player.getBettingAmount());
        assertThat(profit.getNickname()).isEqualTo("플레이어");
        assertThat(profit.getProfit()).isEqualTo(expectedProfit);
    }

    @ParameterizedTest
    @DisplayName("딜러가 버스트일 경우 플레이어의 수익을 구할 수 있다.")
    @MethodSource()
    void canCreateWhenDealerBust(Player player, GameResultType expectedType) {
        Dealer dealer = DealerFixture.createBust();

        PlayerProfit profit = player.calculateProfit(dealer);

        int expectedProfit = expectedType.calculateProfit(player.getBettingAmount());
        assertThat(profit.getNickname()).isEqualTo(player.getNickname());
        assertThat(profit.getProfit()).isEqualTo(expectedProfit);
    }

    static Stream<Arguments> canCreateWhenDealerBust() {
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
    void canCreateByWinningType(Dealer dealer, Player player, GameResultType expectedType) {
        PlayerProfit profit = player.calculateProfit(dealer);

        int expectedProfit = expectedType.calculateProfit(player.getBettingAmount());
        assertThat(profit.getNickname()).isEqualTo("플레이어");
        assertThat(profit.getProfit()).isEqualTo(expectedProfit);
    }

    static Stream<Arguments> canCreateByWinningType() {
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