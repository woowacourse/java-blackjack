package domain.board;

import domain.PlayerStatus;
import domain.user.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBoardsTest {

    @DisplayName("플레이어 이름을 가진 리스트를 입력받아 플레이어 현황판들을 생성한다.")
    @Test
    void createBoxes() {
        PlayerBoards playerBoards = PlayerBoards.from(List.of("split", "echo"));
        List<PlayerBoard> allPlayBoard = playerBoards.getAllPlayerBoard();
        Assertions.assertThat(allPlayBoard).containsExactly(
            new PlayerBoard(new Player("split"), PlayerStatus.HIT_ABLE),
            new PlayerBoard(new Player("echo"), PlayerStatus.HIT_ABLE)
        );
    }
//
//    @DisplayName("참가자 DTO 에 데이터를 전달한다.")
//    @Test
//    void setParticipantDTOTest() {
//        ParticipantDTO participantDTO = new ParticipantDTO();
//        PlayerBoards PlayerBoards = PlayerBoards.of("echo,split");
//        PlayerBoards.setParticipantDTO(participantDTO);
//        Assertions.assertThat(participantDTO.getPlayers()).containsExactly(
//            new Player("echo"), new Player("split")
//        );
//    }
//
//    @DisplayName("게임 결과 반환 테스트")
//    @Test
//    void getBoxResult() {
//        PlayerBoards PlayerBoards = PlayerBoards.of("echo");
//        List<Player> playersAndDealerAtLast = PlayerBoards.getPlayersAndDealerAtLast();
//        Player player = playersAndDealerAtLast.get(0);
//        Player dealer = playersAndDealerAtLast.get(1);
//        makeBlackJack(player);
//        PlayerBoards.updatePlayerBox(player);
//        makeBustByTwentyTwo(dealer);
//        PlayerBoards.updatePlayerBox(dealer);
//        GameResult gameResult = PlayerBoards.getGameResult(player);
//        Assertions.assertThat(gameResult.getWinCount()).isEqualTo(1);
//        Assertions.assertThat(gameResult.getLoseCount()).isEqualTo(0);
//    }
//
//    @DisplayName("현재 턴의 박스의 참가자를 반환하고 업데이트 한다.")
//    @TestFactory
//    Stream<DynamicTest> getCurrentParticipant() {
//        PlayerBoards PlayerBoards = PlayerBoards.of("firstPlayer,secondPlayer");
//        return Stream.of(
//            DynamicTest.dynamicTest("처음에 첫 참가자를 반환한다.", () -> {
//                Assertions.assertThat(PlayerBoards.getCurrentTurnPlayer()).isEqualTo(new Player("firstPlayer"));
//            }),
//            DynamicTest.dynamicTest("첫 참가자의 턴이 끝난 경우 두번째 참가자를 반환한다.(블랙잭)", () -> {
//                Player firstPlayer = PlayerBoards.getCurrentTurnPlayer();
//                makeBlackJack(firstPlayer);
//                PlayerBoards.updatePlayerBox(firstPlayer);
//                Assertions.assertThat(PlayerBoards.getGameBoxInfoBy(firstPlayer))
//                    .isEqualTo(new GameBoxInfo(PlayerStatus.BLACK_JACK, 21));
//                Assertions.assertThat(PlayerBoards.getCurrentTurnPlayer()).isEqualTo(new Player("secondPlayer"));
//            }),
//            DynamicTest.dynamicTest("모든 참가자 턴이 끝나면 딜러를 반환한다.(스탠드)", () -> {
//                Player secondPlayer = PlayerBoards.getCurrentTurnPlayer();
//                PlayerBoards.updatePlayerBox(secondPlayer);
//                Assertions.assertThat(PlayerBoards.getGameBoxInfoBy(secondPlayer))
//                    .isEqualTo(new GameBoxInfo(PlayerStatus.STAND, 0));
//                Assertions.assertThat(PlayerBoards.getCurrentTurnPlayer()).isEqualTo(new Dealer());
//            }),
//            DynamicTest.dynamicTest("딜러턴 모두 종료된 후 다음 턴을 조회할 경우 오류를 던진다.", () -> {
//                Player dealer = PlayerBoards.getCurrentTurnPlayer();
//                PlayerBoards.updatePlayerBox(dealer);
//                Assertions.assertThatThrownBy(PlayerBoards::getCurrentTurnPlayer)
//                    .isExactlyInstanceOf(IllegalStateException.class)
//                    .hasMessage("더 이상 게임을 진행할 박스가 없습니다.");
//            })
//        );
//    }
//
//    void makeBustByTwentyTwo(Player player) {
//        player.dealt(new Card(Denomination.JACK, Suit.SPADE));
//        player.dealt(new Card(Denomination.JACK, Suit.HEART));
//        player.dealt(new Card(Denomination.TWO, Suit.DIAMOND));
//    }
//
//    void makeBlackJack(Player player) {
//        player.dealt(new Card(Denomination.JACK, Suit.SPADE));
//        player.dealt(new Card(Denomination.JACK, Suit.HEART));
//        player.dealt(new Card(Denomination.ACE, Suit.DIAMOND));
//    }
}
