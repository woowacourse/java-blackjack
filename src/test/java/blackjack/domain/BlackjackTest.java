package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.view.OutputView;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {
    private Blackjack blackjack;
    private IntendedNumberGenerator intendedNumberGenerator;

    @BeforeEach
    void setUp() {
        blackjack = new Blackjack(List.of("pobi", "jason"));
        intendedNumberGenerator = new IntendedNumberGenerator(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
    }

    @DisplayName("처음 두장 나눠주는 기능 테스트")
    @Test
    void distributeInitialCardsTest() {
        blackjack.distributeInitialCards(intendedNumberGenerator);
        int playerCardNum = blackjack.getPlayers().getPlayers().get(0).getMyCards().size();
        int dealerCardNum = blackjack.getDealer().getMyCards().size();
        System.out.println(playerCardNum);
        System.out.println(dealerCardNum);
        assertThat(playerCardNum == 2 && dealerCardNum == 2).isTrue();
    }

    @DisplayName("addCondition이 true일때 현재 turn플레이어 카드 추가되는지 테스트")
    @Test
    void additionalCardToTurnPlayerTest() {
        blackjack.addtionalCardToPlayer(
                new IntendedNumberGenerator(List.of(1)), blackjack.turnPlayer(), true);
        assertThat(blackjack.getPlayers().firstPlayer().getMyCards().size()).isEqualTo(1);
    }

    @DisplayName("addCondition이 false일때 turn플레이어 다음으로 넘어가는지 테스트")
    @Test
    void additionalCardToTurnPlayerTest2() {
        blackjack.addtionalCardToPlayer(
                new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6, 7)), blackjack.turnPlayer(), false);
        assertThat(blackjack.turnPlayer().isSameName("jason")).isTrue();
    }

    @DisplayName("addCondition이 true이고 카드추가 후 burst가 되면 turn플레이어 다음으로 넘어가는지 테스트")
    @Test
    void additionalCardToTurnPlayerTest3() {
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(0, 1, 2, 9, 3, 4, 10));
        blackjack.distributeInitialCards(numberGenerator);
        blackjack.addtionalCardToPlayer(numberGenerator, blackjack.turnPlayer(), true);
        assertThat(blackjack.turnPlayer().isSameName("jason")).isTrue();
    }

    @DisplayName("모든 플레이어가 turn플레이어로 한 번 다 돌았으면 true 리턴하는지 테스트")
    @Test
    void cycleIsOverTest() {
        blackjack.addtionalCardToPlayer(
                new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6, 7)), blackjack.turnPlayer(), false);
        blackjack.addtionalCardToPlayer(
                new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6, 7)), blackjack.turnPlayer(), false);
        assertThat(blackjack.cycleIsOver()).isTrue();
    }

    @DisplayName("아직 모든 플레이어가 turn플레이어로 다 안돌았으면 false 리턴하는지 테스트")
    @Test
    void cycleIsOverTest2() {
        assertThat(blackjack.cycleIsOver()).isFalse();
    }

    @DisplayName("딜러가 hit이면 카드 한장 더 주는지 테스트")
    @Test
    void distributeCardToDealerUntilHitTest() {
        blackjack.distributeInitialCards(new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(blackjack.additionalCardToDealer(new IntendedNumberGenerator(List.of(7)))).isTrue();
    }

    @DisplayName("딜러가 hit이 아니면 카드 안주는지 테스트")
    @Test
    void distributeCardToDealerUntilHitTest2() {
        blackjack.distributeInitialCards(new IntendedNumberGenerator(List.of(1, 2, 0, 4, 5, 9)));
        assertThat(blackjack.additionalCardToDealer(new IntendedNumberGenerator(List.of(7)))).isFalse();
    }
}
