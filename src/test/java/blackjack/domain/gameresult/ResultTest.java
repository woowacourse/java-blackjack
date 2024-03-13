package blackjack.domain.gameresult;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static blackjack.domain.card.Kind.*;
import static blackjack.domain.card.Value.*;
import static blackjack.domain.gameresult.Result.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ResultTest {
    private static Stream<Arguments> makeTestPlayerAndTestDealer() {
        Map<Name, Batting> playerNamesAndBattings = new HashMap<>();
        playerNamesAndBattings.put(new Name("pobi"), Batting.from(100.0));
        Player testPlayer = new Players(playerNamesAndBattings)
                .getPlayers()
                .get(0);
        Dealer testDealer = new Dealer();

        return Stream.of(arguments(testPlayer, testDealer));
    }

    @DisplayName("블랙잭 승리(1.5배) : 플레이어 첫턴 블랙잭 && testDealer가 블랙잭이 아닐 때")
    @ParameterizedTest
    @MethodSource("makeTestPlayerAndTestDealer")
    void should_returnBlackJackWin_When_PlayerBlackJack_And_DealerIsNotBlackJack(Player testPlayer, Dealer testDealer) {
        testPlayer.addCard(new Card(SPADE, ACE)); // 플레이어 : 블랙잭
        testPlayer.addCard(new Card(SPADE, TEN));
        testDealer.addCard(new Card(HEART, ACE)); // 딜러 : 11

        Result testResult = Result.judge(testPlayer, testDealer);

        assertThat(testResult).isEqualTo(BLACKJACK_WIN);
    }

    @DisplayName("플레이어 승 : 플레이어 카드패 > 딜러 카드패")
    @ParameterizedTest
    @MethodSource("makeTestPlayerAndTestDealer")
    void should_returnWin_When_PlayerHands_Higher_Than_DealerHands(Player testPlayer, Dealer testDealer) {
        testPlayer.addCard(new Card(SPADE, ACE)); // 플레이어 - 20
        testPlayer.addCard(new Card(SPADE, NINE));
        testDealer.addCard(new Card(HEART, ACE)); // 딜러 - 11

        Result testResult = Result.judge(testPlayer, testDealer);

        assertThat(testResult).isEqualTo(WIN);
    }

    @DisplayName("플레이어 승 : 플레이어 - NonBurst, 딜러 - Burst")
    @ParameterizedTest
    @MethodSource("makeTestPlayerAndTestDealer")
    void should_returnWin_When_PlayerNonBust_DealerBust(Player testPlayer, Dealer testDealer) {
        testPlayer.addCard(new Card(SPADE, ACE)); // player - 20
        testPlayer.addCard(new Card(SPADE, NINE));
        testDealer.addCard(new Card(HEART, NINE)); // 딜러 - bust(27)
        testDealer.addCard(new Card(DIAMOND, NINE));
        testDealer.addCard(new Card(CLOVER, NINE));

        Result testResult = Result.judge(testPlayer, testDealer);

        assertThat(testResult).isEqualTo(WIN);
    }

    @DisplayName("플레이어 패 : 플레이어 카드패 < 딜러 카드패")
    @ParameterizedTest
    @MethodSource("makeTestPlayerAndTestDealer")
    void should_returnLose_When_PlayerHands_Lower_Than_DealerHands(Player testPlayer, Dealer testDealer) {
        testPlayer.addCard(new Card(SPADE, ACE)); // 플레이어 -13
        testPlayer.addCard(new Card(SPADE, TWO));
        testDealer.addCard(new Card(HEART, ACE)); // 딜러 - 14
        testDealer.addCard(new Card(HEART, THREE));

        Result testResult = Result.judge(testPlayer, testDealer);

        assertThat(testResult).isEqualTo(LOSE);
    }

    @DisplayName("플레이어 패 : 플레이어 - Burst, 딜러 - NonBurst")
    @ParameterizedTest
    @MethodSource("makeTestPlayerAndTestDealer")
    void should_returnLose_When_PlayerBust_DealerNonBust(Player testPlayer, Dealer testDealer) {
        testPlayer.addCard(new Card(SPADE, TEN)); //플레이어 - bust(30)
        testPlayer.addCard(new Card(SPADE, JACK));
        testPlayer.addCard(new Card(SPADE, QUEEN));
        testDealer.addCard(new Card(HEART, NINE)); //딜러 - 18
        testDealer.addCard(new Card(SPADE, NINE));

        Result testResult = Result.judge(testPlayer, testDealer);

        assertThat(testResult).isEqualTo(LOSE);
    }

    @DisplayName("플레이어 패 : 플레이어- Burst, 딜러- Burst")
    @ParameterizedTest
    @MethodSource("makeTestPlayerAndTestDealer")
    void should_returnDraw_When_Both_Burst(Player testPlayer, Dealer testDealer) {
        testPlayer.addCard(new Card(SPADE, TEN)); //플레이어 - bust(30)
        testPlayer.addCard(new Card(SPADE, JACK));
        testPlayer.addCard(new Card(SPADE, QUEEN));
        testDealer.addCard(new Card(HEART, NINE)); //딜러 - bust(27)
        testDealer.addCard(new Card(SPADE, NINE));
        testDealer.addCard(new Card(CLOVER, NINE));

        Result testResult = Result.judge(testPlayer, testDealer);

        assertThat(testResult).isEqualTo(LOSE);
    }

    @DisplayName("무승부 : 플레이어 카드패 == 딜러 카드패")
    @ParameterizedTest
    @MethodSource("makeTestPlayerAndTestDealer")
    void should_returnDraw_When_PlayerHands_Equal_DealerHands(Player testPlayer, Dealer testDealer) {
        testPlayer.addCard(new Card(SPADE, ACE)); // 플레이어 - 11
        testDealer.addCard(new Card(HEART, ACE)); //딜러 - 11

        Result testResult = Result.judge(testPlayer, testDealer);

        assertThat(testResult).isEqualTo(DRAW);
    }
}
