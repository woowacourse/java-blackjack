package domain.box;

import domain.GameResult;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerStatus;
import dto.ParticipantDTO;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class GameBoxesTest {

    @DisplayName("모든 박스 생성 테스트")
    @Test
    void createBoxes() {
        GameBoxes gameBoxes = GameBoxes.of("echo,split");
        List<Player> playersAndDealerAtLast = gameBoxes.getPlayersAndDealerAtLast();
        Assertions.assertThat(playersAndDealerAtLast).containsExactly(
            new Player("echo"), new Player("split"), new Dealer()
        );
    }

    @DisplayName("참가자 DTO 에 데이터를 전달한다.")
    @Test
    void setParticipantDTOTest() {
        ParticipantDTO participantDTO = new ParticipantDTO();
        GameBoxes gameBoxes = GameBoxes.of("echo,split");
        gameBoxes.setParticipantDTO(participantDTO);
        Assertions.assertThat(participantDTO.getPlayers()).containsExactly(
            new Player("echo"), new Player("split")
        );
    }

    @DisplayName("게임 결과 반환 테스트")
    @Test
    void getBoxResult() {
        GameBoxes gameBoxes = GameBoxes.of("echo");
        List<Player> playersAndDealerAtLast = gameBoxes.getPlayersAndDealerAtLast();
        Player player = playersAndDealerAtLast.get(0);
        Player dealer = playersAndDealerAtLast.get(1);
        makeBlackJack(player);
        gameBoxes.updatePlayerBox(player);
        makeBustByTwentyTwo(dealer);
        gameBoxes.updatePlayerBox(dealer);
        GameResult gameResult = gameBoxes.getGameResult(player);
        Assertions.assertThat(gameResult.getWinCount()).isEqualTo(1);
        Assertions.assertThat(gameResult.getLoseCount()).isEqualTo(0);
    }

    @DisplayName("현재 턴의 박스의 참가자를 반환하고 업데이트 한다.")
    @TestFactory
    Stream<DynamicTest> getCurrentParticipant() {
        GameBoxes gameBoxes = GameBoxes.of("firstPlayer,secondPlayer");
        return Stream.of(
            DynamicTest.dynamicTest("처음에 첫 참가자를 반환한다.", () -> {
                Assertions.assertThat(gameBoxes.getCurrentTurnPlayer()).isEqualTo(new Player("firstPlayer"));
            }),
            DynamicTest.dynamicTest("첫 참가자의 턴이 끝난 경우 두번째 참가자를 반환한다.(블랙잭)", () -> {
                Player firstPlayer = gameBoxes.getCurrentTurnPlayer();
                makeBlackJack(firstPlayer);
                gameBoxes.updatePlayerBox(firstPlayer);
                Assertions.assertThat(gameBoxes.getGameBoxInfoBy(firstPlayer))
                    .isEqualTo(new GameBoxInfo(PlayerStatus.BLACK_JACK, 21));
                Assertions.assertThat(gameBoxes.getCurrentTurnPlayer()).isEqualTo(new Player("secondPlayer"));
            }),
            DynamicTest.dynamicTest("모든 참가자 턴이 끝나면 딜러를 반환한다.(스탠드)", () -> {
                Player secondPlayer = gameBoxes.getCurrentTurnPlayer();
                gameBoxes.updatePlayerBox(secondPlayer);
                Assertions.assertThat(gameBoxes.getGameBoxInfoBy(secondPlayer))
                    .isEqualTo(new GameBoxInfo(PlayerStatus.STAND, 0));
                Assertions.assertThat(gameBoxes.getCurrentTurnPlayer()).isEqualTo(new Dealer());
            }),
            DynamicTest.dynamicTest("딜러턴 모두 종료된 후 다음 턴을 조회할 경우 오류를 던진다.", () -> {
                Player dealer = gameBoxes.getCurrentTurnPlayer();
                gameBoxes.updatePlayerBox(dealer);
                Assertions.assertThatThrownBy(gameBoxes::getCurrentTurnPlayer)
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessage("더 이상 게임을 진행할 박스가 없습니다.");
            })
        );
    }

    void makeBustByTwentyTwo(Player player) {
        player.dealt(new Card(Denomination.JACK, Suit.SPADE));
        player.dealt(new Card(Denomination.JACK, Suit.HEART));
        player.dealt(new Card(Denomination.TWO, Suit.DIAMOND));
    }

    void makeBlackJack(Player player) {
        player.dealt(new Card(Denomination.JACK, Suit.SPADE));
        player.dealt(new Card(Denomination.JACK, Suit.HEART));
        player.dealt(new Card(Denomination.ACE, Suit.DIAMOND));
    }
}
