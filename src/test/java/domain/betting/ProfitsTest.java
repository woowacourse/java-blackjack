package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.Results;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitsTest {

    Card card_K = Card.getCard(Rank.RANK_K, Suit.DIAMOND);
    Card card_9 = Card.getCard(Rank.RANK_9, Suit.CLOVER);
    Card card_4 = Card.getCard(Rank.RANK_4, Suit.CLOVER);
    Card card_5 = Card.getCard(Rank.RANK_5, Suit.DIAMOND);
    List<Card> cards_19 = List.of(card_K, card_9);
    List<Card> cards_BUST = List.of(card_K, card_9, card_4);
    Name name_player_19 = new Name("player_19");
    Name name_player_BUST = new Name("player_Bust");
    Dealer dealer_15 = new Dealer(List.of(card_K, card_5));

    Players players;
    Results results;
    BettingReceipts bettingReceipts;
    Profits profits;

    @BeforeEach
    void setUp() {
        Player player_19 = new Player(name_player_19, cards_19);
        Player player_BUST = new Player(name_player_BUST, cards_BUST);
        players = new Players(List.of(player_19, player_BUST));
        results = Results.generateResultAtFinal(dealer_15, players);
        Map<Name, BettingMoney> maps = new LinkedHashMap<>();
        maps.put(name_player_19, new BettingMoney(1000));
        maps.put(name_player_BUST, new BettingMoney(2000));
        bettingReceipts = new BettingReceipts(maps);
        profits = Profits.generateProfits(results, bettingReceipts, players);
    }

    @Test
    @DisplayName("플레이어의 수익 반환")
    void getProfit() {
        assertThat(profits.getProfit(name_player_19)).isEqualTo(1000);
        assertThat(profits.getProfit(name_player_BUST)).isEqualTo(-2000);
    }

    @Test
    @DisplayName("플레이어의 수익 반환")
    void calculateDealerProfit() {
        assertThat(profits.calculateDealerProfit()).isEqualTo(1000);
    }
}
