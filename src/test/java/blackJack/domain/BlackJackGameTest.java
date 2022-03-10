package blackJack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;

class BlackJackGameTest {

    @Test
    @DisplayName("BlackJackGame 생성 테스트")
    void createValidDealer() {
        Participants participants = new Participants(new Dealer(), List.of(new Player("rookie")));

        assertThat(new BlackJackGame(participants)).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 최초 카드 분배 기능 테스트")
    void firstCardDispensing() {
        Player player1 = new Player("kei");
        Player player2 = new Player("rookie");
        Participants participants = new Participants(new Dealer(), List.of(player1, player2));

        BlackJackGame blackJackGame = new BlackJackGame(participants);
        blackJackGame.firstCardDispensing();

        assertThat(player1.getCards().size()).isEqualTo(2);
    }
}
