package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.view.PlayerCommand;
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
    @DisplayName("카드 합이 버스트이면 hit 할 수 없다.")
    void playerNotHitWhenBust() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player(new Gamer(new Hand(List.of())), "lemone");
        PlayerCommand hitCommand = PlayerCommand.HIT;

        // when
        player.draw(List.of(new Card(NINE, CLUB),
                new Card(TEN, SPADE),
                new Card(TEN, DIAMOND)));

        // then
        assertThat(blackjackGame.isPlayerCanHit(player))
                .isEqualTo(false);
    }

    @Test
    @DisplayName("카드 합이 블랙잭이면 hit 할 수 없다.")
    void playerNotHitWhenBlackjack() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player(new Gamer(new Hand(List.of())), "lemone");

        // when
        player.draw(List.of(new Card(TEN, SPADE), new Card(ACE, DIAMOND)));

        // then
        assertThat(blackjackGame.isPlayerCanHit(player))
                .isEqualTo(false);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 21이면 카드를 뽑지 않는다.")
    void playerNotHitWhenMaxScore() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player(new Gamer(new Hand(List.of())), "lemone");

        // when
        player.draw(List.of(new Card(TEN, SPADE), new Card(ACE, DIAMOND), new Card(TEN, DIAMOND)));
        blackjackGame.hit(player);

        // then
        assertThat(blackjackGame.isPlayerCanHit(player)).isEqualTo(false);
    }

    @Test
    @DisplayName("플레이어의 카드 합이 버스트가 아니고 블랙잭도 아니고 21도 아니면 카드를 한 장 뽑는다.")
    void playerHitWhenNoBustNoBlackjackNoMaxScore() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player(new Gamer(new Hand(List.of())), "lemone");

        // when
        player.draw(List.of(new Card(TEN, SPADE), new Card(TEN, DIAMOND)));
        blackjackGame.hit(player);

        // then
        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 카드를 한장 더 뽑늗다.")
    void dealerHitTest() {
        // given
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        List<Card> cards = new ArrayList<>(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB)));
        Deck cardPicker = new Deck(cards) {
            @Override
            public List<Card> pickCards(int count) {
                return cards;
            }
        };
        BlackjackGame blackjackGame = new BlackjackGame(cardPicker);

        // when
        dealer.draw(cards);
        blackjackGame.drawUntilOverBoundWithCount(dealer);

        // then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }
}
