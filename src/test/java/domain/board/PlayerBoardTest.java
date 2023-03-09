package domain.board;

import domain.TurnAction;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.user.Player;
import domain.user.PlayerStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerBoardTest {

    @DisplayName("플레이어와 점수를 가진 현황판을 생성한다.")
    @Test
    void createPlayerBoard() {
        Player player = new Player("split");
        PlayerBoard playerBoard = new PlayerBoard(player, PlayerStatus.HIT_ABLE);
        Assertions.assertThat(playerBoard.getPlayer()).isEqualTo(player);
        Assertions.assertThat(playerBoard.getPoint()).isEqualTo(0);
    }

    @DisplayName("플레이어의 카드추가 선택을 통해 업데이트한다.")
    @Nested
    class updatePlayerBoardByPlayerTurnAction {

        @DisplayName("카드를 받고 점수가 21보다 낮을 때")
        @Test
        void updateWhenPlayerHitAndUnderBlackJack() {
            Player player = new Player("split");
            PlayerBoard playerBoard = new PlayerBoard(player, PlayerStatus.HIT_ABLE);
            playerBoard.update(TurnAction.HIT);
            Assertions.assertThat(playerBoard).extracting("status").isEqualTo(PlayerStatus.HIT_ABLE);
        }

        @DisplayName("카드를 받고 점수가 21일 때")
        @Test
        void updateWhenPlayerHitAndBlackJAck() {
            Player player = new Player("split");
            PlayerBoard playerBoard = new PlayerBoard(player, PlayerStatus.HIT_ABLE);
            player.dealt(new Card(Denomination.ACE, Suit.DIAMOND));
            player.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            playerBoard.update(TurnAction.HIT);
            Assertions.assertThat(playerBoard).extracting("status").isEqualTo(PlayerStatus.BLACK_JACK);
        }

        @DisplayName("카드를 받고 점수가 21보다 높을 때")
        @Test
        void updateWhenPlayerHitAndBUst() {
            Player player = new Player("split");
            PlayerBoard playerBoard = new PlayerBoard(player, PlayerStatus.HIT_ABLE);
            player.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            player.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            player.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            playerBoard.update(TurnAction.HIT);
            Assertions.assertThat(playerBoard).extracting("status").isEqualTo(PlayerStatus.BUST);
        }

        @DisplayName("카드를 받지 않을 때")
        @Test
        void updateWhenPlayerStand() {
            Player player = new Player("split");
            PlayerBoard playerBoard = new PlayerBoard(player, PlayerStatus.HIT_ABLE);
            Assertions.assertThat(playerBoard.getPoint()).isEqualTo(0);
            playerBoard.update(TurnAction.STAND);
            Assertions.assertThat(playerBoard).extracting("status").isEqualTo(PlayerStatus.STAND);
        }
    }

}
