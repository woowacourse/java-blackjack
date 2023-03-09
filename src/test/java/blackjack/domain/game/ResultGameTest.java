package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class ResultGameTest {
    private Dealer dealer;
    private Participants participants;


    @BeforeEach
    void setting() {
        dealer = new Dealer();
        participants = new Participants(dealer, List.of("pobi", "crong", "dali"));
    }

    @Test
    @DisplayName("생성자 테스트")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new ResultGame(participants));
    }

    @Test
    @DisplayName("플레이어의 결과를 출력하는 테스트")
    void getPlayerResultTest() {
        // given
        ResultGame resultGame = new ResultGame(participants);
        dealer.drawCard(new Card(Shape.CLOVER, Letter.EIGHT));
        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.NINE));

        // when
        resultGame.calculateResult();

        // then
        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
    }
}
