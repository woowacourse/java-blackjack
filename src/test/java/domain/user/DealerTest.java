package domain.user;

import domain.GameManager;
import domain.TrumpCardManager;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @BeforeEach
    public void setUp() {
        TrumpCardManager.bin();
        TrumpCardManager.initCache();
    }

    @DisplayName("딜러의 모든 카드를 연다")
    @Test
    void test() {
        //given
        GameManager gameManager = new GameManager(List.of("레몬"));
        Dealer dealer = (Dealer) gameManager.getDealer();
        //when
        for (int i = 0; i < 5; i++) {
            dealer.drawCard();
        }
        //then
        Assertions.assertThat(dealer.openAllCard()).hasSize(5);
    }

    @DisplayName("딜러가 16이 넘을때까지 카드를 뽑는다")
    @Test
    void test1() {
        //given
        GameManager gameManager = new GameManager(List.of("레몬"));
        Dealer dealer = (Dealer) gameManager.getDealer();

        //when
        while (!dealer.isImpossibleDraw()) {
            dealer.drawCard();
        }

        //then
        Assertions.assertThat(dealer.getCardDeck().calculateScore()).isGreaterThanOrEqualTo(16);

    }

}
