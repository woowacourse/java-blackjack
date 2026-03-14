package model.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import dto.status.DealerStatus;
import dto.status.PlayerStatus;
import org.junit.jupiter.api.Test;

public class ProfitCalculatorTest {
    private static final Integer BET_AMOUNT = 10000;

    @Test
    public void 플레이어만_버스트인_경우_정상_작동() {
        PlayerStatus player = new PlayerStatus("player", 22, BET_AMOUNT, true, false);
        DealerStatus dealer = new DealerStatus("dealer",20, false, false);

        Integer result = ProfitCalculator.calculateBetAmount(dealer, player);

        assertThat(result).isEqualTo(-BET_AMOUNT);
    }

    @Test
    public void 플레이어와_딜러_모두_버스트인_경우_정상_작동() {
        PlayerStatus player = new PlayerStatus("player", 22, BET_AMOUNT, true, false);
        DealerStatus dealer = new DealerStatus("dealer", 23, true, false);

        Integer result = ProfitCalculator.calculateBetAmount(dealer, player);

        assertThat(result).isEqualTo(-BET_AMOUNT);
    }

    @Test
    public void 플레이어만_블랙잭인_경우_정상_작동() {
        PlayerStatus player = new PlayerStatus("player", 21, BET_AMOUNT, false, true);
        DealerStatus dealer = new DealerStatus("dealer" ,20, false, false);

        Integer result = ProfitCalculator.calculateBetAmount(dealer, player);

        assertThat(result).isEqualTo((int) (BET_AMOUNT * 1.5));
    }

    @Test
    public void 플레이어와_딜러_모두_블랙잭인_경우_정상_작동() {
        PlayerStatus player = new PlayerStatus("player", 21, BET_AMOUNT, false, true);
        DealerStatus dealer = new DealerStatus("dealer", 21, false, true);

        Integer result = ProfitCalculator.calculateBetAmount(dealer, player);

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void 둘_다_일반_점수면서_플레이어_점수가_낮은_경우_정상_작동() {
        PlayerStatus player = new PlayerStatus("player", 17, BET_AMOUNT, false, false);
        DealerStatus dealer = new DealerStatus("dealer", 20, false, false);

        Integer result = ProfitCalculator.calculateBetAmount(dealer, player);

        assertThat(result).isEqualTo(-BET_AMOUNT);
    }

    @Test
    public void 딜러가_버스트이면서_플레이어가_일반_점수인_경우() {
        PlayerStatus player = new PlayerStatus("player", 17, BET_AMOUNT, false, false);
        DealerStatus dealer = new DealerStatus("dealer", 21, true, false);

        Integer result = ProfitCalculator.calculateBetAmount(dealer, player);

        assertThat(result).isEqualTo(BET_AMOUNT);
    }

    @Test
    public void 둘_다_일반_점수면서_플레이어_점수가_높은_경우_정상_작동() {
        PlayerStatus player = new PlayerStatus("player", 20, BET_AMOUNT, false, false);
        DealerStatus dealer = new DealerStatus("dealer", 17, false, false);

        Integer result = ProfitCalculator.calculateBetAmount(dealer, player);

        assertThat(result).isEqualTo(BET_AMOUNT);
    }

    @Test
    public void 둘_다_일반_점수면서_점수가_같은_경우_정상_작동() {
        PlayerStatus player = new PlayerStatus("player", 17, BET_AMOUNT, false, false);
        DealerStatus dealer = new DealerStatus("dealer", 17, false, false);

        Integer result = ProfitCalculator.calculateBetAmount(dealer, player);

        assertThat(result).isEqualTo(0);
    }
}
