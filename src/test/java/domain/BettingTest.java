package domain;

import dto.BettingDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BettingTest {

    @Test
    @DisplayName("Betting 에서 Player 의 배팅 금액을 알아낸다.")
    void getBettingMoney() {
        BettingDto bettingDto = new BettingDto();
        Player player = new Player(new Name("aa"), new Cards(Collections.emptyList()));
        bettingDto.putPlayerAndMoney(player, Money.of(10000));

        Betting betting = new Betting(bettingDto.getBetting());
        int money = betting.getPlayerMoney(player);

        assertThat(money).isEqualTo(10000);
    }

}
