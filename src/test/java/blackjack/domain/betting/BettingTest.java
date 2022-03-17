package blackjack.domain.betting;

import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BettingTest {

    @Test
    @DisplayName("Betting은 Player와 Money를 가질 수 있다.")
    void getPlayerAndMoney() {
        Betting betting = new Betting(new Participant("corinne", name -> true), new Money(10000));

        assertThat(betting).isNotNull();
    }
}
