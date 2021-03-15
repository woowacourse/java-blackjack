package blackjack.domain.game;

import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGameTest {

    BlackJackGame blackJackGame;

    @BeforeEach
    void beforeEach() {
        Gambler gambler1 = new Gambler("pobi", new BettingMoney(10000));
        Gambler gambler2 = new Gambler("jason", new BettingMoney(20000));
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
        blackJackGame.Gamblers().getGamblers().forEach(gambler -> assertThat(gambler.cards().countCards()).isEqualTo(2));
    }

    @Test
    @DisplayName("겜블러 카드 드로우 테스트")
    void testGiveGamblerCard() {
        //given
        Gambler targetGambler = new Gambler("pobi", new BettingMoney(10000));

        //when
        blackJackGame.giveGamblerCard(targetGambler);

        //then
        assertThat(blackJackGame.Gamblers().getGamblers().stream()
                .filter(gambler -> gambler.isSameName(targetGambler))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 겜블러가 없습니다."))
                .cards()
                .countCards()).isEqualTo(1);
    }
}
