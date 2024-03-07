package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("딜러에게 2장의 카드가 주어졌는지 확인한다")
    void initializeDealer() {
        Blackjack blackjack = new Blackjack(Players.from("a,b,c".split(",")));
        assertThat(blackjack.getDealer().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 2장의 카드가 주어졌는지 확인한다")
    void initializePlayers() {
        Blackjack blackjack = new Blackjack(Players.from("a,b,c".split(",")));
        assertThat(blackjack.getPlayers().getPlayers().get(0).getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 1장의 카드를 추가로 지급한다")
    void dealCardsToPlayer() {
        Blackjack blackjack = new Blackjack(Players.from("a,b,c".split(",")));
        Player player = blackjack.getPlayers().getPlayers().get(0);

        blackjack.dealCard(player);

        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("게임의 결과가 제대로 계산됐는지 확인한다")
    void gameResultTest() {
        Player dealer = new Player("딜러");
        Player teba = new Player("테바");
        Player jonge = new Player("종이");
        teba.addCard(Card.makeRandomCard(new RandomNumberGeneartor(1, 2)));
        jonge.addCard(Card.makeRandomCard(new RandomNumberGeneartor(1, 2)));
        Blackjack blackjack = new Blackjack(new Players(List.of(teba, jonge)));

        Map<Player, Entry<Integer, Integer>> playerEntryMap = blackjack.finishGame();

        Integer dealerLose = playerEntryMap.get(dealer).getValue();
        Integer tebaWin = playerEntryMap.get(teba).getKey();
        Integer jongeWin = playerEntryMap.get(jonge).getKey();
        assertThat(tebaWin + jongeWin).isEqualTo(dealerLose);
    }
}
