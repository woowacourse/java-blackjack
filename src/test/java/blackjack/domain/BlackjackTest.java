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

        assertThat(playerCardNum == 2 && dealerCardNum == 2).isTrue();
    }

    @DisplayName("addCondition이 true일때 현재 turn플레이어 카드 추가되는지 테스트")
    @Test
    void additionalCardToTurnPlayerTest() {
        blackjack.addtionalCardToTurnPlayer(new IntendedNumberGenerator(List.of(1)), true);
        assertThat(blackjack.getPlayers().firstPlayer().getMyCards().size()).isEqualTo(1);
    }

    @DisplayName("addCondition이 false일때 turn플레이어 다음으로 넘어가는지 테스트")
    @Test
    void additionalCardToTurnPlayerTest2() {
        blackjack.addtionalCardToTurnPlayer(new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6, 7)), false);
        assertThat(blackjack.turnPlayer().isSameName("jason")).isTrue();
    }

    @DisplayName("addCondition이 true이고 카드추가 후 burst가 되면 turn플레이어 다음으로 넘어가는지 테스트")
    @Test
    void additionalCardToTurnPlayerTest3() {
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(0, 1, 2, 9, 3, 4, 10));
        blackjack.distributeInitialCards(numberGenerator);
        blackjack.addtionalCardToTurnPlayer(numberGenerator, true);
        assertThat(blackjack.turnPlayer().isSameName("jason")).isTrue();
    }

    @DisplayName("모든 플레이어가 turn플레이어로 한 번 다 돌았으면 true 리턴하는지 테스트")
    @Test
    void cycleIsOverTest() {
        blackjack.addtionalCardToTurnPlayer(new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6, 7)), false);
        blackjack.addtionalCardToTurnPlayer(new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6, 7)), false);
        assertThat(blackjack.cycleIsOver()).isTrue();
    }

    @DisplayName("아직 모든 플레이어가 turn플레이어로 다 안돌았으면 false 리턴하는지 테스트")
    @Test
    void cycleIsOverTest2() {
        assertThat(blackjack.cycleIsOver()).isFalse();
    }

    @DisplayName("딜러가 hit할때까지 나눠주는 기능 테스트")
    @Test
    void distributeCardToDealerUntilHitTest() {
        int numberOfAddedCards = blackjack.distributeCardToDealerUntilHit(intendedNumberGenerator);
        assertThat(numberOfAddedCards).isEqualTo(4);
    }
}
