package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetRecordTest {

    @DisplayName("배팅내역을 생성한다")
    @Test
    public void create() {
        assertThatCode(() -> new BetRecord(Map.of(new Name("이상"), new AmountOfBet(1000))))
                .doesNotThrowAnyException();
    }

    @DisplayName("무승부면 배팅 금액의 0배를 수익금으로 갖는다")
    @Test
    public void calculateProfit() {
        Players players = Players.createInitialPlayers(List.of(new Name("이상")));
        Dealer dealer = Dealer.createInitialStateDealer();
        BetRecord betRecord = new BetRecord(Map.of(new Name("이상"), new AmountOfBet(1000)));

        Deck playerDeck = Deck.of(new CardFactory(), cards -> cards);
        players = players.initializePlayersHands(playerDeck);
        Deck dealerDeck = Deck.of(new CardFactory(), cards -> cards);
        dealer = dealer.draw(dealerDeck);

        players = players.getPlayers().stream()
                .map(player -> player.draw(playerDeck))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::updatePlayers));
        dealer = dealer.draw(dealerDeck);

        betRecord.calculateProfit(players, dealer);
    }
}
