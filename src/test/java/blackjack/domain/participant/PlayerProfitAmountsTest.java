package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.BlackjackCardsFactory;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerProfitAmountsTest {

    @DisplayName("무승부면 베팅 금액의 0배를 수익금으로 갖는다")
    @Test
    public void calculateProfit() {
        Name name = new Name("이상");
        Players players = Players.createInitialPlayers(List.of(name));
        Dealer dealer = Dealer.createInitialStateDealer();
        PlayerBetAmounts playerBetAmounts = new PlayerBetAmounts(Map.of(name, new BetAmount(1000)));

        Deck playerDeck = Deck.of(new BlackjackCardsFactory(), cards -> cards);
        players = players.takeFirstHand(playerDeck);
        Deck dealerDeck = Deck.of(new BlackjackCardsFactory(), cards -> cards);
        dealer = dealer.takeFirstHand(dealerDeck);

        Players newPlayers = players.getPlayers().stream()
                .map(Player::stand)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::updatePlayers));

        PlayerProfitAmounts playerProfitAmounts = PlayerProfitAmounts.calculateProfit(newPlayers, dealer,
                playerBetAmounts);

        ProfitAmount profitAmount = playerProfitAmounts.calculateDealerProfit();

        assertThat(profitAmount.getAmount()).isEqualTo(0);
    }
}
