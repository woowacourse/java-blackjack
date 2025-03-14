package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackJackGame2Test {

    @Test
    void 게임이_시작하면_모든_플레이어에게_카드를_두장씩_배분한다() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("벡터");
        Player player2 = new Player("한스");
        Players players = new Players(List.of(player1, player2));
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        BlackJackGame2 blackJackGame2 = new BlackJackGame2(deckInitializer, dealer, players);
        blackJackGame2.initializeGame();

        // then
        assertThat(dealer.getReceivedCards().size()).isEqualTo(2);
        assertThat(player1.getReceivedCards().size()).isEqualTo(2);
        assertThat(player2.getReceivedCards().size()).isEqualTo(2);

    }

    @Test
    void 참여자가_카드를_받는다() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("벡터");
        Player player2 = new Player("한스");
        Players players = new Players(List.of(player1, player2));
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        BlackJackGame2 blackJackGame2 = new BlackJackGame2(deckInitializer, dealer, players);
        blackJackGame2.receiveCard(true);
        blackJackGame2.receiveCard(true);

        // then
        assertThat(player1.getReceivedCards().size()).isEqualTo(2);
    }

    @Test
    void 참여자가_카드를_받지_않는다() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("벡터");
        Player player2 = new Player("한스");
        Players players = new Players(List.of(player1, player2));
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        BlackJackGame2 blackJackGame2 = new BlackJackGame2(deckInitializer, dealer, players);
        blackJackGame2.receiveCard(true);
        blackJackGame2.receiveCard(false);

        // then
        assertThat(player1.getReceivedCards().size()).isEqualTo(1);
        assertThat(blackJackGame2.getCurrentTurnParticipant()).isEqualTo(player2);
    }

    @Test
    void 딜러에게_카드를_추가한다() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("벡터");
        Player player2 = new Player("한스");
        Players players = new Players(List.of(player1, player2));
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        BlackJackGame2 blackJackGame2 = new BlackJackGame2(deckInitializer, dealer, players);
        blackJackGame2.drewDealerCards();

        // then
        assertThat(dealer.getReceivedCards().size()).isEqualTo(1);
    }

    @Test
    void 딜러의_포인트가_16_이하면_카드를_더_받아야_한다() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("벡터");
        Player player2 = new Player("한스");
        Players players = new Players(List.of(player1, player2));
        DeckInitializer deckInitializer = new DeckInitializer();
        BlackJackGame2 blackJackGame2 = new BlackJackGame2(deckInitializer, dealer, players);
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_2));
        // when

        // then
        assertThat(blackJackGame2.isDrawableDealerCard()).isTrue();
    }

    @Test
    void 딜러의_포인트가_17_이상이면_카드를_더_받지_않는다() {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player("벡터");
        Player player2 = new Player("한스");
        Players players = new Players(List.of(player1, player2));
        DeckInitializer deckInitializer = new DeckInitializer();
        BlackJackGame2 blackJackGame2 = new BlackJackGame2(deckInitializer, dealer, players);
        dealer.putCard(new Card(CardShape.HEART, CardType.KING));
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_9));
        // when

        // then
        assertThat(blackJackGame2.isDrawableDealerCard()).isFalse();
    }
}
