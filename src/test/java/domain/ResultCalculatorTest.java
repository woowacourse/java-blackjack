package domain;

import java.util.List;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultCalculatorTest {

    @Test
    @DisplayName("대결 시 승패 여부가 올바르게 저장된다.")
    void shouldSuccessSaveFightWinOrLoseResult() {
        Player player = new Player(new Name("dino"));
        Dealer dealer = new Dealer();
        ResultCalculator resultCalculator = new ResultCalculator(new Players(List.of(player)), dealer);
        player.takeCard(new Card("5다이아몬드", 5));
        player.takeCard(new Card("10다이아몬드", 10));
        dealer.takeCard(new Card("3하트", 3));
        dealer.takeCard(new Card("8클로버", 8));
        resultCalculator.fight(player, dealer);

        List<Integer> playerResults =resultCalculator.getResultsByName("dino");
        List<Integer> dealerResults =resultCalculator.getResultsByName("딜러");

        Assertions.assertThat(playerResults).isEqualTo(List.of(1, 0, 0));
        Assertions.assertThat(dealerResults).isEqualTo(List.of(0, 0, 1));
    }

    @Test
    @DisplayName("대결 시 무승부 여부가 올바르게 저장된다.")
    void shouldSuccessSaveFightDrawResult() {
        Player player = new Player(new Name("dino"));
        Dealer dealer = new Dealer();
        ResultCalculator resultCalculator = new ResultCalculator(new Players(List.of(player)), dealer);
        player.takeCard(new Card("5다이아몬드", 5));
        player.takeCard(new Card("10다이아몬드", 10));
        dealer.takeCard(new Card("10하트", 10));
        dealer.takeCard(new Card("5클로버", 5));
        resultCalculator.fight(player, dealer);

        List<Integer> playerResults =resultCalculator.getResultsByName("dino");
        List<Integer> dealerResults =resultCalculator.getResultsByName("딜러");

        Assertions.assertThat(playerResults).isEqualTo(List.of(0, 1, 0));
        Assertions.assertThat(dealerResults).isEqualTo(List.of(0, 1, 0));
    }
}
