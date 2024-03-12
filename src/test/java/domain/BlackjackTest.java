package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackTest {

    @Test
    @DisplayName("딜러에게 2장의 카드가 주어졌는지 확인한다")
    void initializeDealer() {
        final Players players = Players.from(List.of("pobi", "crong", "tebah"));
        final Dealer dealer = new Dealer(new Deck());
        final Blackjack blackjack = new Blackjack(players, dealer);

        assertThat(blackjack.getDealer().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 2장의 카드가 주어졌는지 확인한다")
    void initializePlayers() {
        final Players players = Players.from(List.of("pobi", "crong", "tebah"));
        final Dealer dealer = new Dealer(new Deck());
        final Blackjack blackjack = new Blackjack(players, dealer);

        assertThat(blackjack.getPlayers().get(0).getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 1장의 카드를 추가로 지급한다")
    void dealCardsToPlayer() {
        final Players players = Players.from(List.of("pobi", "crong", "tebah"));
        final Dealer dealer = new Dealer(new Deck());
        final Blackjack blackjack = new Blackjack(players, dealer);
        final Player player = blackjack.getPlayers().get(0);
        blackjack.dealCard(player);

        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("게임의 결과가 제대로 계산됐는지 확인한다")
    void gameResultTest() {
        final Dealer dealer = new Dealer(new Deck());
        final Player teba = new Player(new Name("테바"));
        final Player jonge = new Player(new Name("종이"));
        teba.dealCard(new Card(Denomination.ACE, Suit.CLUBS));
        jonge.dealCard(new Card(Denomination.ACE, Suit.HEART));
        final Blackjack blackjack = new Blackjack(new Players(new ArrayList<>(List.of(teba, jonge))), dealer);

        final BlackjackResult blackjackResultDTO = blackjack.finishGame();
        final Integer dealerLose = blackjackResultDTO.results().get(dealer).getValue();
        final Integer tebaWin = blackjackResultDTO.results().get(teba).getKey();
        final Integer jongeWin = blackjackResultDTO.results().get(jonge).getKey();

        assertThat(tebaWin + jongeWin).isEqualTo(dealerLose);
    }
}
