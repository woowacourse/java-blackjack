package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Chip;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.card.Rank.ACE;
import static blackjack.domain.card.Rank.NINE;
import static blackjack.domain.card.Rank.SEVEN;
import static blackjack.domain.card.Rank.TEN;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("블랙잭 게임")
public class BlackjackGameTest {
    @Test
    @DisplayName("블랙잭 게임에서 deal을 하면 딜러, 모든 플레이어에게 각각 두장의 카드를 부여한다.")
    void dealTest() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Dealer dealer = new Dealer(new Chip(0L));
        Player player1 = new Player(new Name("lemone"), new Chip(1000L));
        Player player2 = new Player(new Name("seyang"), new Chip(1000L));
        Players players = new Players(List.of(player1, player2));

        // when
        blackjackGame.deal(dealer, players);

        // then
        for (Player player : players.values()) {
            assertThat(player.cards().size()).isEqualTo(2);
        }
        assertThat(dealer.cards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 합이 버스트이면 hit 할 수 없다.")
    void playerNotHitWhenBust() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player(new Name("lemone"), new Chip(1000L));

        // when
        player.draw(List.of(Card.of(NINE, CLUB),
                Card.of(TEN, SPADE),
                Card.of(TEN, DIAMOND)));

        // then
        assertThat(blackjackGame.isPlayerCanHit(player))
                .isEqualTo(false);
    }

    @Test
    @DisplayName("카드 합이 블랙잭이면 hit 할 수 없다.")
    void playerNotHitWhenBlackjack() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player(new Name("lemone"), new Chip(1000L));

        // when
        player.draw(List.of(Card.of(TEN, SPADE), Card.of(ACE, DIAMOND)));

        // then
        assertThat(blackjackGame.isPlayerCanHit(player))
                .isEqualTo(false);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 21이면 카드를 뽑지 않는다.")
    void playerNotHitWhenMaxScore() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player(new Name("lemone"), new Chip(1000L));

        // when
        player.draw(List.of(Card.of(TEN, SPADE), Card.of(ACE, DIAMOND), Card.of(TEN, DIAMOND)));
        blackjackGame.hit(player);

        // then
        assertThat(blackjackGame.isPlayerCanHit(player))
                .isEqualTo(false);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 버스트가 아니고 블랙잭도 아니고 21도 아니면 카드를 한 장 뽑는다.")
    void playerHitWhenNoBustNoBlackjackNoMaxScore() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player(new Name("lemone"), new Chip(1000L));

        // when
        player.draw(List.of(Card.of(TEN, SPADE), Card.of(TEN, DIAMOND)));
        blackjackGame.hit(player);

        // then
        assertThat(player.cards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 카드를 한장 더 뽑늗다.")
    void dealerHitTest() {
        // given
        Dealer dealer = new Dealer(new Chip(0L));
        List<Card> cards = new ArrayList<>(List.of(Card.of(NINE, SPADE), Card.of(SEVEN, CLUB)));
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());

        // when
        dealer.draw(cards);
        blackjackGame.drawUntilOverBoundWithCount(dealer);

        // then
        assertThat(dealer.cards().size()).isEqualTo(3);
    }
}
