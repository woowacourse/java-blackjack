package domain.profit;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfitFactoryTest {
    @DisplayName("플레이어와 딜러 모두 블랙잭일 때 올바른 전략을 반환하는지 테스트")
    @Test
    void returnBothBlackJackTest() {
        Player player = new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.KING, CardSuitSymbol.DIAMOND)
        )), 10_000);
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.KING, CardSuitSymbol.CLUB)
        )));

        Assertions.assertThat(ProfitFactory.create(player, dealer)).isInstanceOf(BothBlackJack.class);
    }

    @DisplayName("플레이어만 블랙잭일 때 올바른 전략을 반환하는지 테스트")
    @Test
    void returnPlayerBlackJackTest() {
        Player player = new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.KING, CardSuitSymbol.DIAMOND)
        )), 10_000);
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.FOUR, CardSuitSymbol.CLUB),
                Card.of(CardNumber.KING, CardSuitSymbol.CLUB)
        )));

        Assertions.assertThat(ProfitFactory.create(player, dealer)).isInstanceOf(PlayerBlackJack.class);
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이 아니고 플레이어가 이겼을 때 올바른 전략을 반환하는지 테스트")
    @Test
    void returnPlayerWinTest() {
        Player player = new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.FOUR, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.KING, CardSuitSymbol.DIAMOND)
        )), 10_000);
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.FOUR, CardSuitSymbol.CLUB),
                Card.of(CardNumber.KING, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB)
        )));

        Assertions.assertThat(ProfitFactory.create(player, dealer)).isInstanceOf(PlayerWin.class);
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이 아니고 플레이어가 졌을 때 올바른 전략을 반환하는지 테스트")
    @Test
    void returnPlayerLooseTest() {
        Player player = new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.FOUR, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.KING, CardSuitSymbol.DIAMOND)
        )), 10_000);
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.KING, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB)
        )));

        Assertions.assertThat(ProfitFactory.create(player, dealer)).isInstanceOf(PlayerLoose.class);
    }
}
