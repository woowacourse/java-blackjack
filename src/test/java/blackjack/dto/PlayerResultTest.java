package blackjack.dto;

import blackjack.domain.Deck;
import blackjack.domain.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.NoShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("플레이어 결과")
class PlayerResultTest {

    @DisplayName("존재하지 않는 플레이어의 이름으로 조회하면 예외가 발생한다")
    @Test
    void findByNonexistentNameException() {
        //given
        PlayerResult playerResult = new PlayerResult();
        Deck deck = new Deck(new NoShuffleStrategy());
        Dealer dealer = new Dealer(deck);
        playerResult.addResult(new Player("exist", dealer), GameResult.WIN);

        //when & then
        assertThatThrownBy(() -> playerResult.findByName("nonExist"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}