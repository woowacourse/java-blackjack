package blackJack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    @DisplayName("BlackJackGame 생성 테스트")
    void createBlackJackGame() {
        Participants participants = new Participants(List.of("kei", "rookie", "parang"));

        assertThat(new BlackJackGame(participants)).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 최초 카드 분배 기능 테스트")
    void firstCardDispensing() {
        Participants participants = new Participants(List.of("kei", "rookie", "parang"));
        BlackJackGame blackJackGame = new BlackJackGame(participants);
        Player player = participants.getPlayers().get(0);

        blackJackGame.firstCardDispensing();

        assertThat(player.getCards().size()).isEqualTo(2);
    }
}
