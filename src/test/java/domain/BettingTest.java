package domain;

import domain.gamer.Name;
import domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BettingTest {

    @DisplayName("게임 결과가 주어지면, 플레이어의 수익을 구한다.")
    @Test
    void calculateProfit(){
        //given
        Player player = new Player(new Name("pobi"));
        String money = "500000";
        Betting betting = new Betting(Map.of(player,new Money(money)));
        PlayerResult playerResult = PlayerResult.BLACK_JACK_WIN;
        BigDecimal expectedProfit = new BigDecimal(money).multiply(new BigDecimal("1.5"));

        //when
        BigDecimal result = betting.calculateProfit(player,playerResult);

        //then
        assertThat(result).isEqualByComparingTo(expectedProfit);
    }
}
