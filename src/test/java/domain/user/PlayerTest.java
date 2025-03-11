package domain.user;

import domain.GameManager;
import domain.TrumpCardManager;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @BeforeEach
    public void setUp() {
        TrumpCardManager.bin();
        TrumpCardManager.initCache();
    }

    @DisplayName("플레이어가 21이 넘을때까지 카드를 뽑는다")
    @Test
    void test1() {
        //given
        GameManager gameManager = new GameManager(List.of("레몬"));
        User user = gameManager.findUserByUsername("레몬");

        //when
        while (!user.isImpossibleDraw()) {
            user.drawCard();
        }

        //then
        Assertions.assertThat(user.getCardDeck().calculateScore()).isGreaterThanOrEqualTo(21);
    }

    @DisplayName("플레이어는 dealer 혹은 딜러이름을 사용할 수 없다.")
    @Test
    void test2() {
        //given
        List<String> dealer1 = List.of("dealer");
        List<String> dealer2 = List.of("딜러");

        //when & then
        SoftAssertions.assertSoftly((softAssertions) -> {
            softAssertions.assertThatIllegalArgumentException().isThrownBy(() -> new GameManager(dealer1))
                .withMessage("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
            softAssertions.assertThatIllegalArgumentException().isThrownBy(() -> new GameManager(dealer2))
                .withMessage("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
        });
    }
}
