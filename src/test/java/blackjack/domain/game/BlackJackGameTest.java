package blackjack.domain.game;

import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGameTest {

    BlackJackGame blackJackGame;

    @BeforeEach
    void beforeEach() {
        //given
        Gambler gambler1 = new Gambler("pobi", new Money(10000));
        Gambler gambler2 = new Gambler("jason", new Money(20000));
        Gamblers gamblers = new Gamblers(Arrays.asList(gambler1, gambler2));
        blackJackGame = new BlackJackGame(gamblers);
    }

    @Test
    @DisplayName("딜러, 겜블러 카드 초기화 테스트")
    void testInitializeCards() {
        //when
        blackJackGame.initDealerCards();
        blackJackGame.initGamblersCards();

        //then
        assertThat(blackJackGame.getDealer().cards().countCards()).isEqualTo(2);
        blackJackGame.getGamblers().gamblers().forEach(gambler -> assertThat(gambler.cards().countCards()).isEqualTo(2));
    }

    @Test
    @DisplayName("겜블러 카드 드로우 테스트")
    void testGiveGamblerCard() {
        //given
        Gambler targetGambler = new Gambler("pobi", new Money(10000));

        //when
        blackJackGame.giveGamblerCard(targetGambler);

        //then
        assertThat(blackJackGame.getGamblers().gamblers().stream()
                .filter(gambler -> gambler.isSameName(targetGambler))
                .findFirst()
                .get()
                .cards()
                .countCards()).isEqualTo(1);
    }
}
