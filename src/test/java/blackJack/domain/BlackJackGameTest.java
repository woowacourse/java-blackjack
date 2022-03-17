package blackJack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player1 = new Player("kei");
        player2 = new Player("rookie");
        player3 = new Player("parang");
        dealer = new Dealer();
    }

    @Test
    @DisplayName("BlackJackGame 생성 테스트")
    void createBlackJackGame() {
        Participants participants = new Participants(dealer, List.of(player1, player2, player3));

        assertThat(new BlackJackGame(participants)).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 최초 카드 분배 기능 테스트")
    void firstCardDispensing() {
        Participants participants = new Participants(dealer, List.of(player1, player2, player3));
        BlackJackGame blackJackGame = new BlackJackGame(participants);

        blackJackGame.firstCardDispensing();

        assertThat(player1.getCardsInfo().size()).isEqualTo(2);
    }
}
