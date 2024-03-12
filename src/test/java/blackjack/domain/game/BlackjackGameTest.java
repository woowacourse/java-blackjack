package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Dealer;
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
    @DisplayName("플레이어 입력이 HIT여도 카드 합이 버스트이면 hit 할 수 없다.")
    void playerNotHitWhenBust() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player("lemone", new Hand(List.of()));
        PlayerCommand hitCommand = PlayerCommand.HIT;

        // when
        player.draw(List.of(new Card(NINE, CLUB),
                new Card(TEN, SPADE),
                new Card(TEN, DIAMOND)));

        // then
        assertThat(blackjackGame.isPlayerCanHit(player, hitCommand))
                .isEqualTo(false);
    }

    @Test
    @DisplayName("플레이어 입력이 HIT여도 카드 합이 블랙잭이면 hit 할 수 없다.")
    void playerNotHitWhenBlackjack() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player("lemone", new Hand(List.of()));
        PlayerCommand hitCommand = PlayerCommand.HIT;

        // when
        player.draw(List.of(new Card(TEN, SPADE), new Card(ACE, DIAMOND)));

        // then
        assertThat(blackjackGame.isPlayerCanHit(player, hitCommand))
                .isEqualTo(false);
    }

    @Test
    @DisplayName("플레이어 입력 커맨드가 HIT이면 카드를 한장 뽑는다.")
    void playerCommandHitThenDraw() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player("lemone", new Hand(List.of()));
        PlayerCommand hitCommand = PlayerCommand.HIT;

        // when
        blackjackGame.hitOrStand(player, hitCommand);

        // then
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어 입력 커맨드가 STAND 이면 카드를 뽑지 않는다.")
    void playerCommandStandThenNotDraw() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Player player = new Player("lemone", new Hand(List.of()));
        PlayerCommand hitCommand = PlayerCommand.STAND;

        // when
        blackjackGame.hitOrStand(player, hitCommand);

        // then
        assertThat(player.getCards().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 카드를 한장 더 뽑늗다.")
    void dealerHitTest() {
        // given
        Dealer dealer = new Dealer(new Hand(List.of()));
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
        blackjackGame.drawIfScoreUnderBound(dealer);

        // then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }
}
