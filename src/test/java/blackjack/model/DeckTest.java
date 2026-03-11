package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("딜러 및 플레이어에게 초기 카드 2장 정상 배분")
    void test_provide_init_cards() {
        List<Card> cards = List.of(new Card(Suit.HEART, Rank.TEN));
        Deck deck = new Deck(new FixShuffleStrategy(cards));

        List<Player> allPlayers = List.of(new Player("pobi", 1000), new Player("james", 1000));
        Players players = new Players(allPlayers);
        Dealer dealer = new Dealer();

        deck.provideInitCards(players, dealer);

        for (Player player : players.all()) {
            assertThat(player.cards().size()).isEqualTo(2);
        }
        assertThat(dealer.cards().size()).isEqualTo(2);
    }


    @Test
    @DisplayName("카드 추가 지급 정상")
    void test_provide_one_card_success() {
        Player player = new Player("pobi", 1000);
        List<Card> cards = List.of(new Card(Suit.HEART, Rank.TEN));
        Deck deck = new Deck(new FixShuffleStrategy(cards));

        deck.provideOneCard(player);

        assertThat(player.cards().size()).isEqualTo(1);
    }

}
