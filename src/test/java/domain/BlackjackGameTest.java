package domain;

import static fixture.CardFixture.카드;
import static fixture.DealerFixture.딜러;
import static fixture.PlayersFixture.플레이어;
import static fixture.PlayersFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(Lifecycle.PER_CLASS)
class BlackjackGameTest {
    @Test
    void 게임을_시작하면_딜러와_플레이어들에게_카드를_나눠준다() { // todo 플레이어의 카드 개수도 검증
        Dealer dealer = 딜러();
        Players players = 플레이어들("뽀로로", "프린", "포비");
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.startGame();

        assertThat(dealer).extracting("cardHand").extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(2);
    }

    @ParameterizedTest
    @MethodSource("provideDealerAndResult")
    void 블랙잭_게임의_결과를_반환한다(Dealer dealer, ResultStatus resultStatus) {
        Players players = 플레이어들("뽀로로");
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.startGame();

        GameResult result = blackjackGame.createGameResult();
        Map<Player, ResultStatus> playerResult = result.getPlayerResult();
        assertThat(playerResult).containsEntry(플레이어("뽀로로"), resultStatus);
    }

    private Stream<Arguments> provideDealerAndResult() {
        return Stream.of(
                Arguments.of(
                        딜러(카드(Denomination.THREE), 카드(Denomination.TEN), 카드(Denomination.SIX), 카드(Denomination.QUEEN)),
                        ResultStatus.LOSE
                ),
                Arguments.of(
                        딜러(카드(Denomination.TEN), 카드(Denomination.KING), 카드(Denomination.JACK), 카드(Denomination.QUEEN)),
                        ResultStatus.DRAW
                ),
                Arguments.of(
                        딜러(카드(Denomination.TEN), 카드(Denomination.THREE), 카드(Denomination.QUEEN), 카드(Denomination.SIX)),
                        ResultStatus.WIN
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDealerAndBoolean")
    void 딜러가_카드를_더_받아야_하는지_확인한다(Dealer dealer, boolean expected) {
        Players players = 플레이어들("프린");
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.startGame();
        boolean actual = blackjackGame.shouldDealerDrawCard();

        assertThat(actual).isEqualTo(expected);
    }

    private Stream<Arguments> provideDealerAndBoolean() {
        return Stream.of(
                Arguments.of(
                        딜러(카드(Denomination.THREE), 카드(Denomination.TEN), 카드(Denomination.SIX), 카드(Denomination.QUEEN)),
                        false
                ),
                Arguments.of(
                        딜러(카드(Denomination.TEN), 카드(Denomination.THREE), 카드(Denomination.QUEEN), 카드(Denomination.SIX)),
                        true
                )
        );
    }
}
