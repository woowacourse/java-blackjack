package domain.blackjack;

import domain.card.Deck;
import domain.participant.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackGameTest {

    @DisplayName("딜러에게 2장의 카드가 주어졌는지 확인한다")
    @Test
    void initializeDealer() {
        final Map<Name, BetAmount> mapNamesToBetAmounts = new HashMap<>();
        mapNamesToBetAmounts.put(new Name("pobi"), new BetAmount(100));
        mapNamesToBetAmounts.put(new Name("crong"), new BetAmount(100));
        mapNamesToBetAmounts.put(new Name("tebah"), new BetAmount(100));

        final Players players = Players.from(mapNamesToBetAmounts);
        final Dealer dealer = new Dealer(new Deck());
        final BlackjackGame blackjack = new BlackjackGame(players, dealer);

        assertThat(blackjack.getDealer().hand().size()).isEqualTo(2);
    }

    @DisplayName("플레이어에게 2장의 카드가 주어졌는지 확인한다")
    @Test
    void initializePlayers() {
        final Map<Name, BetAmount> mapNamesToBetAmounts = new HashMap<>();
        mapNamesToBetAmounts.put(new Name("pobi"), new BetAmount(100));
        mapNamesToBetAmounts.put(new Name("crong"), new BetAmount(100));
        mapNamesToBetAmounts.put(new Name("tebah"), new BetAmount(100));
        final Players players = Players.from(mapNamesToBetAmounts);
        final Dealer dealer = new Dealer(new Deck());
        final BlackjackGame blackjack = new BlackjackGame(players, dealer);

        assertThat(blackjack.getPlayers().get(0).hand().size()).isEqualTo(2);
    }

    @DisplayName("플레이어에게 1장의 카드를 추가로 지급한다")
    @Test
    void dealCardsToPlayer() {
        final Map<Name, BetAmount> mapNamesToBetAmounts = new HashMap<>();
        mapNamesToBetAmounts.put(new Name("pobi"), new BetAmount(100));
        mapNamesToBetAmounts.put(new Name("crong"), new BetAmount(100));
        mapNamesToBetAmounts.put(new Name("tebah"), new BetAmount(100));
        final Players players = Players.from(mapNamesToBetAmounts);
        final Dealer dealer = new Dealer(new Deck());
        final BlackjackGame blackjack = new BlackjackGame(players, dealer);
        final Player player = blackjack.getPlayers().get(0);
        blackjack.dealCard(player);

        assertThat(player.hand().size()).isEqualTo(3);
    }
}
