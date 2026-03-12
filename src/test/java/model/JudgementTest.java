package model;

import static fixture.CardsTestFixture.createDealer;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.judgement.GameStatus;
import model.judgement.Judgement;
import model.judgement.PlayerResult;
import model.paticipant.Dealer;
import model.paticipant.Player;
import model.paticipant.Players;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class JudgementTest {

    @ParameterizedTest(name = "플레이어 {0}, 딜러 {1}일 때 결과는 {2}이다")
    @MethodSource("fixture.PlayerResultTestFixture#플레이어의_상황별_승_패_정보제공")
    void 다양한_게임_상황에서_승패를_올바르게_판정한다(
            List<Card> playerCards,
            List<Card> dealerCards,
            GameStatus status
    ) {
        // given
        Dealer dealer = createDealer();
        dealerCards.forEach(dealer::addCard);

        Player player = new Player("pobi");
        playerCards.forEach(player::addCard);

        // when
        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, new Players(List.of(player)));

        // then
        assertThat(playerResult.countByStatus(status)).isEqualTo(1);
    }
}
