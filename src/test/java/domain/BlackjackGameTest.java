package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    void 딜러보다_점수가_크다면_배팅_금악만큼_수익이다() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.of(List.of("둘리"));
        Player player = blackjackGame.getPlayers().get(0);
        Card card = new Card(Denomination.NINE, Suit.SPADE);
        player.addCard(card);

        // when
        player.betPlayer(10_000);

        // then
        assertThat(blackjackGame.getBettingResult()).contains(
                // 딜러 0 vs 둘리 9 -> 딜러 승
                Map.entry(player, 10_000)
        );
    }

    @Test
    void 딜러와_점수가_같다면_수익은_없다() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.of(List.of("둘리"));
        Player player = blackjackGame.getPlayers().get(0);
        Card card = new Card(Denomination.NINE, Suit.SPADE);
        blackjackGame.getDealer().addCard(card);
        player.addCard(card);

        // when
        player.betPlayer(10_000);

        // then
        assertThat(blackjackGame.getBettingResult()).contains(
                // 딜러 9 vs 둘리 9 -> 무승부
                Map.entry(player, 0)
        );
    }

    @Test
    void 딜러보다_점수가_낮으면_배팅금액만큼_잃는다() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.of(List.of("패배자이름"));
        Dealer dealer = blackjackGame.getDealer();
        Player player = blackjackGame.getPlayers().get(0);
        dealer.addCard(new Card(Denomination.NINE, Suit.SPADE));
        player.addCard(new Card(Denomination.TWO, Suit.SPADE));

        // when
        player.betPlayer(10_000);

        // then
        assertThat(blackjackGame.getBettingResult()).contains(
                // 딜러 9 vs 패배자 1
                Map.entry(player, -10_000)
        );
    }

    @Test
    void 플레이어_카드_합이_블랙잭이면_150_퍼센트_수익이다() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.of(List.of("둘리"));
        Player player = blackjackGame.getPlayers().get(0);
        player.addCard(new Card(Denomination.ACE, Suit.SPADE));
        player.addCard(new Card(Denomination.TEN, Suit.SPADE));

        // when
        player.betPlayer(10_000);

        // then
        assertThat(blackjackGame.getBettingResult()).contains(
                // 딜러 0 vs 둘리 21
                Map.entry(player, 15_000)
        );
    }
}
