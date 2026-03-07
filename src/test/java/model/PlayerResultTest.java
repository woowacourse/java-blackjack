package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerResultTest {

    @ParameterizedTest(name = "플레이어 {0}, 딜러 {1}일 때 결과는 {2}이다")
    @MethodSource("fixture.PlayerResultTestFixture#플레이어의_상황별_승_패_정보제공")
    void 다양한_게임_상황에서_승패를_올바르게_판정한다(
            List<Card> playerCards,
            List<Card> dealerCards,
            GameStatus status
    ) {
        // given
        Dealer dealer = new Dealer();
        dealerCards.forEach(dealer::addCard);

        Player player = new Player("pobi");
        playerCards.forEach(player::addCard);

        PlayerResult playerResult = PlayerResult.initResult();

        // when
        playerResult.judgeByPlayer(dealer, player);

        // then
        assertThat(playerResult.countByStatus(status)).isEqualTo(1);
    }
}
