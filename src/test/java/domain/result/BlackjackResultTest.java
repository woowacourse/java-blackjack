package domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import static domain.card.CardFixture.cardsOf;
import static domain.card.Rank.ACE;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Deck;
import domain.participant.dealer.Dealer;
import domain.participant.dealer.DealerResult;
import domain.participant.player.PlayerFixture;
import domain.participant.player.PlayerResults;
import domain.participant.player.Players;

class BlackjackResultTest {

    static Stream<Arguments> of() {
        return Stream.of(
                Arguments.of(
                        new Dealer(new Deck(cardsOf(ACE, KING, ACE, NINE))),
                        Players.from(List.of(PlayerFixture.of("Pobi", 1000))),
                        ProfitFixture.amountOf(1000),
                        ProfitFixture.amountOf(-1000)
                ),
                Arguments.of(
                        new Dealer(new Deck(cardsOf(ACE, NINE, ACE, KING))),
                        Players.from(List.of(PlayerFixture.of("Pobi", 1000))),
                        ProfitFixture.amountOf(-1500),
                        ProfitFixture.amountOf(1500)
                ),
                Arguments.of(
                        new Dealer(new Deck(cardsOf(ACE, KING, ACE, KING))),
                        Players.from(List.of(PlayerFixture.of("Pobi", 1000))),
                        ProfitFixture.amountOf(0),
                        ProfitFixture.amountOf(0)
                )
        );
    }

    @DisplayName("딜러와 플레이어들을 입력받아 블랙잭 게임 결과를 종합한다.")
    @MethodSource
    @ParameterizedTest
    void of(Dealer dealer, Players players, Profit expectedDealerProfit, Profit expectedPlayerProfit) {
        dealer.dealInitialCards(players);
        BlackjackResult result = BlackjackResult.of(dealer, players);
        DealerResult dealerResult = result.getDealerResult();
        PlayerResults playerResults = result.getPlayerResults();
        assertAll(
                () -> assertThat(dealerResult.getProfit()).isEqualTo(expectedDealerProfit),
                () -> assertThat(playerResults.profitOf(PlayerFixture.from("Pobi")))
                        .isEqualTo(expectedPlayerProfit)
        );
    }
}