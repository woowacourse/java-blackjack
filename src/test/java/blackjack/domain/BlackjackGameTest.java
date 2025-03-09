package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDump;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("처음에 플레이어와 딜러는 각자 카드 2장씩 받는다.")
    @Test
    void test_distributeInitialCards() {
        // given
        Player player = new Player("player", new CardDeck());
        List<Player> players = List.of(player);
        Dealer dealer = new Dealer(new CardDeck());
        BlackjackGame game = new BlackjackGame(players, dealer, new CardDump());

        // when
        game.distributeInitialCards();

        // then
        assertThat(player.getCardSize()).isEqualTo(2);
        assertThat(dealer.getCardSize()).isEqualTo(2);
    }

    @DisplayName("딜러는 16이상이 될때까지 카드를 자동으로 더 받는다.")
    @Test
    void test_dealerTurn() {
        // given
        Player player = new Player("player", new CardDeck());
        List<Player> players = List.of(player);
        Dealer dealer = new Dealer(new CardDeck());
        BlackjackGame game = new BlackjackGame(players, dealer, new CardDump());

        // when
        game.dealerTurn();

        // then
        int score = dealer.calculateScore();
        assertThat(score).isGreaterThan(16);
    }

}