import domain.BlackjackGame;
import domain.Dealer;
import domain.Participant;
import domain.Player;
import domain.PlayerNames;
import domain.Players;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private BlackjackGame blackjackGame;
    private Players players;

    @BeforeEach
    void setUp() {
        players = Players.from(PlayerNames.from(List.of("pobi", "crong")));
        blackjackGame = new BlackjackGame(players);
    }

    @DisplayName("게임 참가자에게 카드를 나눠줄 수 있다.")
    @Test
    void giveCardToSuccessTest() {
        Player pobi = players.getPlayers().get(0);

        blackjackGame.handOutCardTo(pobi, 2);

        assertPlayerCardSize(pobi, 2);
    }

    @DisplayName("게임 시작 시, 모든 플레이어에게 두 장의 카드를 나눠준다.")
    @Test
    void giveInitCardsSuccessTest() {
        Player pobi = players.getPlayers().get(0);
        Player crong = players.getPlayers().get(1);

        blackjackGame.handOutInitialCards();
        Dealer dealer = blackjackGame.getDealer();

        assertPlayerCardSize(pobi, 2);
        assertPlayerCardSize(crong, 2);
        assertPlayerCardSize(dealer, 2);
    }

    private void assertPlayerCardSize(Participant player, int size) {
        Assertions.assertThat(player)
                .extracting("cards")
                .asList()
                .hasSize(size);
    }

    @DisplayName("딜러는 카드의 합이 17이 넘을때 까지 추가 카드를 받아야 한다.")
    @Test
    void giveAdditionalCardToDealerSuccessTest() {
        blackjackGame.handOutInitialCards();
        blackjackGame.handOutAdditionalCardToDealer();

        Participant dealer = blackjackGame.getDealer();

        Assertions.assertThat(dealer.calculateBlackjackScore())
                .isGreaterThanOrEqualTo(17);
    }

}
