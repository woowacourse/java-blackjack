package domain.box;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class BoxesTest {

    @DisplayName("모든 박스 생성 테스트")
    @Test
    void createBoxes() {
        Boxes boxes = Boxes.of("echo,split");
        List<Player> playersAndDealerAtLast = boxes.getPlayersAndDealerAtLast();
        Assertions.assertThat(playersAndDealerAtLast).containsExactly(
            new Player("echo"), new Player("split"), new Dealer()
        );
    }

    @DisplayName("게임 결과 반환 테스트")
    @Test
    void getBoxResult() {
        Boxes boxes = Boxes.of("echo");
        List<Player> playersAndDealerAtLast = boxes.getPlayersAndDealerAtLast();
        Player player = playersAndDealerAtLast.get(0);
        Player dealer = playersAndDealerAtLast.get(1);
        makeBlackJack(player);
        boxes.updatePlayerBox(player);
        makeBustByTwentyTwo(dealer);
        boxes.updatePlayerBox(dealer);
        GameResult gameResult = boxes.getGameResult(player);
        Assertions.assertThat(gameResult.getWinCount()).isEqualTo(1);
        Assertions.assertThat(gameResult.getLoseCount()).isEqualTo(0);
    }

    @DisplayName("현재 턴의 박스의 참가자를 반환하고 업데이트 한다.")
    @TestFactory
    Stream<DynamicTest> getCurrentParticipant() {
        Boxes boxes = Boxes.of("firstPlayer,secondPlayer");
        return Stream.of(
            DynamicTest.dynamicTest("처음에 첫 참가자를 반환한다.", () -> {
                Assertions.assertThat(boxes.getCurrentTurnPlayer()).isEqualTo(new Player("firstPlayer"));
            }),
            DynamicTest.dynamicTest("첫 참가자의 턴이 끝난 경우 두번째 참가자를 반환한다.(블랙잭)", () -> {
                Player firstPlayer = boxes.getCurrentTurnPlayer();
                makeBlackJack(firstPlayer);
                boxes.updatePlayerBox(firstPlayer);
                Assertions.assertThat(boxes.getBoxStatusByParticipant(firstPlayer))
                    .isEqualTo(new BoxStatus(PlayerStatus.BLACK_JACK, 21));
                Assertions.assertThat(boxes.getCurrentTurnPlayer()).isEqualTo(new Player("secondPlayer"));
            }),
            DynamicTest.dynamicTest("모든 참가자 턴이 끝나면 딜러를 반환한다.(스탠드)", () -> {
                Player secondPlayer = boxes.getCurrentTurnPlayer();
                boxes.updatePlayerBox(secondPlayer);
                Assertions.assertThat(boxes.getBoxStatusByParticipant(secondPlayer))
                    .isEqualTo(new BoxStatus(PlayerStatus.STAND, 0));
                Assertions.assertThat(boxes.getCurrentTurnPlayer()).isEqualTo(new Dealer());
            }),
            DynamicTest.dynamicTest("딜러턴 모두 종료된 후 다음 턴을 조회할 경우 오류를 던진다.", () -> {
                Player dealer = boxes.getCurrentTurnPlayer();
                boxes.updatePlayerBox(dealer);
                Assertions.assertThatThrownBy(boxes::getCurrentTurnPlayer)
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
