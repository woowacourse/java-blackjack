package domain;

import domain.box.BoxStatus;
import domain.box.Boxes;
import domain.box.PlayResult;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.user.Dealer;
import domain.user.Player;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class BoxesTest {

    @DisplayName("모든 박스 생성 테스트")
    @Test
    void create() {
        Boxes boxes = Boxes.of("echo,split");
        Assertions.assertThat(boxes)
            .extracting("boxes")
            .asInstanceOf(InstanceOfAssertFactories.map(Player.class, BoxStatus.class))
            .containsKeys(new Player("echo"), new Player("split"));
    }

    @DisplayName("모든 박스의 상태 업데이트 기능 구현")
    @Test
    void updateTest() {
        Player participant = new Player("echo");
        Boxes boxes = Boxes.of("echo, split");
        participant.dealt(new Card(Denomination.JACK, Suit.SPADE));
        participant.dealt(new Card(Denomination.JACK, Suit.SPADE));
        participant.dealt(new Card(Denomination.JACK, Suit.SPADE));
        boxes.updatePlayerBox(participant);
        BoxStatus boxStatus = boxes.getBoxStatusByParticipant(participant);
        Assertions.assertThat(boxStatus)
            .extracting("playResult")
            .isEqualTo(PlayResult.BUST);
        Assertions.assertThat(boxStatus)
            .extracting("point")
            .isEqualTo(30);
    }

    @DisplayName("현재 턴의 박스의 참가자를 반환한다.")
    @TestFactory
    Stream<DynamicTest> getCurrentParticipant() {
        Boxes boxes = Boxes.of("firstPlayer,secondPlayer,thirdPlayer");
        return Stream.of(
            DynamicTest.dynamicTest("처음에는 첫 참가자를 반환한다.",
                () -> Assertions.assertThat(boxes.getCurrentTurnPlayer())
                    .isEqualTo(new Player("firstPlayer"))),
            DynamicTest.dynamicTest("앞에 참가자가 카드를 더 이상 못 뽑을 경우 다음 참가자를 반환한다.", () -> {
                Player firstPlayer = boxes.getCurrentTurnPlayer();
                firstPlayer.dealt(new Card(Denomination.JACK, Suit.SPADE));
                firstPlayer.dealt(new Card(Denomination.QUEEN, Suit.HEART));
                firstPlayer.dealt(new Card(Denomination.ACE, Suit.DIAMOND));
                boxes.updatePlayerBox(firstPlayer);
                Assertions.assertThat(boxes.getCurrentTurnPlayer()).isEqualTo(new Player("secondPlayer"));
            }),
            DynamicTest.dynamicTest("두번째 참가자가 아무 카드도 받지 않는 경우 다음 참가자를 반환한다.", () -> {
                Player secondPlayer = boxes.getCurrentTurnPlayer();
                boxes.updatePlayerBox(secondPlayer);
                Assertions.assertThat(boxes.getCurrentTurnPlayer()).isEqualTo(new Player("thirdPlayer"));
            }),
            DynamicTest.dynamicTest("반환할 플레이어가 없는 경우, 딜러를 반환한다.", () -> {
                Player thirdPlayer = boxes.getCurrentTurnPlayer();
                thirdPlayer.dealt(new Card(Denomination.JACK, Suit.SPADE));
                thirdPlayer.dealt(new Card(Denomination.QUEEN, Suit.HEART));
                thirdPlayer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
                boxes.updatePlayerBox(thirdPlayer);
                Assertions.assertThat(boxes.getCurrentTurnPlayer()).isEqualTo(new Dealer());
            }),
            DynamicTest.dynamicTest("반환할 참가자가 없는 경우, 예외를 발생시킨다.", () -> {
                Player dealer = boxes.getCurrentTurnPlayer();
                dealer.dealt(new Card(Denomination.JACK, Suit.SPADE));
                dealer.dealt(new Card(Denomination.QUEEN, Suit.HEART));
                dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
                boxes.updatePlayerBox(dealer);
                Assertions.assertThatThrownBy(boxes::getCurrentTurnPlayer)
                    .isExactlyInstanceOf(IllegalStateException.class);
            })
        );
    }
}
