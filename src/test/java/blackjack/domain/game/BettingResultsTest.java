package blackjack.domain.game;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BettingResultsTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        final Deck deck = new CardDeck();
        final Player player = new Player(deck, "헤나");
        final Dealer dealer = new Dealer(deck);
        final Map<Participant, Money> bettingResults = new LinkedHashMap<>();
        bettingResults.put(player, Money.ZERO);

        assertThatNoException().isThrownBy(() -> new BettingResults(bettingResults, dealer));
    }

    @Test
    @DisplayName("배팅 결과가 비어있을 경우 예외가 발생한다.")
    void throwExceptionWhenBettingResultsIsEmpty() {
        final Deck deck = new CardDeck();
        final Dealer dealer = new Dealer(deck);
        final Map<Participant, Money> bettingResults = new LinkedHashMap<>();

        assertThatThrownBy(() -> new BettingResults(bettingResults, dealer))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:12345", "10000:-12345", "0:0", "-10000:-2345"}, delimiter = ':')
    @DisplayName("플레이어들의 배팅 결과를 가져온다.")
    void getPlayerBettingResults(final int moneyValueOne, final int moneyValueTwo) {
        // given
        final Deck deck = new CardDeck();
        final Player player1 = new Player(deck, "헤나");
        final Player player2 = new Player(deck, "시카");
        final Dealer dealer = new Dealer(deck);

        final Map<Participant, Money> playersBetting = new LinkedHashMap<>();
        playersBetting.put(player1, new Money(moneyValueOne));
        playersBetting.put(player2, new Money(moneyValueTwo));

        // when
        final BettingResults bettingResults = new BettingResults(playersBetting, dealer);
        final Map<Participant, Money> playerBettingResults = bettingResults.getPlayerBettingResults();
        final int dealerProfit = -moneyValueOne - moneyValueTwo;

        // then
        assertThat(playerBettingResults).contains(
                entry(dealer, new Money(dealerProfit)),
                entry(player1, new Money(moneyValueOne)),
                entry(player2, new Money(moneyValueTwo))
        );
    }
}
