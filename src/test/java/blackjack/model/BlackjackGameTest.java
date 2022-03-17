package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.Participant;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("BlackjackGame이 생성되는지 확인한다.")
    void construct() {
        List<String> names = List.of("리버", "포키", "크리스");
        BlackjackGame blackjackGame = new BlackjackGame(names);

        assertThat(blackjackGame).isInstanceOf(BlackjackGame.class);
    }

    @DisplayName("참가자들 모두가 카드를 두장 뽑으면 새로운 BlackjackGame을 반환한다.")
    @Test
    void start_new_Blackjackgame() {
        BlackjackGame blackjackGame = new BlackjackGame(List.of("리버", "포키", "크리스"));
        BlackjackGame otherBlackjackGame = blackjackGame.start();

        assertThat(blackjackGame).isNotEqualTo(otherBlackjackGame);
    }

    @DisplayName("게임 시작시 딜러에게 카드 두장을 분배한다.")
    @Test
    void start_dealer_card_count() {
        BlackjackGame blackjackGame = new BlackjackGame(List.of("리버", "포키", "크리스"));
        blackjackGame.start();
        Participant dealer = blackjackGame.getDealer();

        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @DisplayName("게임 시작시 플레이어들 에게 카드 두장을 분배한다.")
    @Test
    void start_players_card_count() {
        BlackjackGame blackjackGame = new BlackjackGame(List.of("리버", "포키", "크리스"));
        blackjackGame.start();
        List<Participant> players = blackjackGame.getPlayers();

        for (Participant player : players) {
            assertThat(player.getCards().size()).isEqualTo(2);
        }
    }
}