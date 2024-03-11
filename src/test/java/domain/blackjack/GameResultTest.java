package domain.blackjack;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Participant;
import domain.player.Player;
import domain.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameResultTest {

    @BeforeEach
    void init() {
        Dealer.getInstance().clear();
    }

    @Test
    @DisplayName("플레이어가 버스트 되면 딜러가 게임을 이긴다")
    void playerBust() {
        final Player dealer = Dealer.getInstance();
        final Player teba = new Participant(new Name("테바"));
        teba.hit(new Card(Rank.JACK, Suit.CLUBS));
        teba.hit(new Card(Rank.KING, Suit.CLUBS));
        teba.hit(new Card(Rank.SIX, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(new Players(new ArrayList<>(List.of(teba))), dealer);

        final GameResult blackjackResultDTO = blackjack.finishGame();
        final Integer tebaLose = blackjackResultDTO.gameResult().get(teba).lose();
        final Integer dealerWin = blackjackResultDTO.gameResult().get(dealer).win();

        Assertions.assertThat(tebaLose).isEqualTo(1);
        Assertions.assertThat(dealerWin).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 버스트 되면 플레이어가 게임을 이긴다")
    void dealerBust() {
        final Player dealer = Dealer.getInstance();
        final Player teba = new Participant(new Name("테바"));
        dealer.hit(new Card(Rank.JACK, Suit.CLUBS));
        dealer.hit(new Card(Rank.KING, Suit.CLUBS));
        dealer.hit(new Card(Rank.SIX, Suit.CLUBS));
        final Blackjack blackjack = new Blackjack(new Players(new ArrayList<>(List.of(teba))), dealer);

        final GameResult blackjackResultDTO = blackjack.finishGame();
        final Integer dealerLose = blackjackResultDTO.gameResult().get(dealer).lose();
        final Integer tebaWin = blackjackResultDTO.gameResult().get(teba).win();

        Assertions.assertThat(dealerLose).isEqualTo(1);
        Assertions.assertThat(tebaWin).isEqualTo(1);
    }
}
