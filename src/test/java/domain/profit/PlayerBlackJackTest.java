package domain.profit;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerBlackJackTest {
    @DisplayName("1.5 배의 수익이 계산되는지 테스트")
    @Test
    void getProfitTest() {
        Player player = new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.SEVEN, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.FOUR, CardSuitSymbol.DIAMOND)
        )), 10_000);
        ProfitStrategy profitStrategy = new PlayerBlackJack();

        Assertions.assertThat(profitStrategy.getProfit(player.getBettingMoney())).isEqualTo(15_000d);
    }
}
