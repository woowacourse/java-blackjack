package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchResultTest {

    @DisplayName("Player가 bust 라면 Dealer가 승리한다.")
    @Test
    void win_bust() {
        Participant dealer = new Dealer();
        dealer.hit(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        dealer.hit(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        Participant player = new Player("리버");
        player.hit(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.hit(new Card(TrumpNumber.QUEEN, TrumpSymbol.SPADE));
        player.hit(new Card(TrumpNumber.THREE, TrumpSymbol.DIAMOND));

        assertThat(MatchResult.match(dealer, player).get()).isEqualTo("승");
    }

    @DisplayName("Dealer와 Player 모두 bust이면 Dealer가 승리한다.")
    @Test
    void win_bust_dealer_and_player() {
        Participant dealer = new Dealer();
        dealer.hit(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        dealer.hit(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));
        dealer.hit(new Card(TrumpNumber.SEVEN, TrumpSymbol.HEART));

        Participant player = new Player("리버");
        player.hit(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.hit(new Card(TrumpNumber.QUEEN, TrumpSymbol.SPADE));
        player.hit(new Card(TrumpNumber.THREE, TrumpSymbol.DIAMOND));

        assertThat(MatchResult.match(dealer, player).get()).isEqualTo("승");
    }

    @DisplayName("Dealer가 blackjack이고 Player가 blackjack이 아니면 Dealer가 승리한다.")
    @Test
    void win_blackjack() {
        Participant dealer = new Dealer();
        dealer.hit(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        dealer.hit(new Card(TrumpNumber.ACE, TrumpSymbol.HEART));

        Participant player = new Player("리버");
        player.hit(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.hit(new Card(TrumpNumber.QUEEN, TrumpSymbol.SPADE));

        assertThat(MatchResult.match(dealer, player).get()).isEqualTo("승");
    }

    @DisplayName("Dealer와 Player가 bust나 blackjack이 아닐때, Dealer가 Player 보다 점수가 높으면 승리한다.")
    @Test
    void win_score() {
        Participant dealer = new Dealer();
        dealer.hit(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        dealer.hit(new Card(TrumpNumber.QUEEN, TrumpSymbol.HEART));

        Participant player = new Player("리버");
        player.hit(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.hit(new Card(TrumpNumber.NINE, TrumpSymbol.SPADE));

        assertThat(MatchResult.match(dealer, player).get()).isEqualTo("승");
    }

    @DisplayName("Dealer가 blackjack이 아니고 Player가 blackjack이면 Dealer가 패배한다.")
    @Test
    void lose_blackjack() {
        Participant dealer = new Dealer();
        dealer.hit(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        dealer.hit(new Card(TrumpNumber.QUEEN, TrumpSymbol.HEART));

        Participant player = new Player("리버");
        player.hit(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.hit(new Card(TrumpNumber.ACE, TrumpSymbol.SPADE));

        assertThat(MatchResult.match(dealer, player).get()).isEqualTo("패");
    }

    @DisplayName("Dealer가 bust이고 Player가 bust가 아니라면 Dealer가 패배한다.")
    @Test
    void lose_bust() {
        Participant dealer = new Dealer();
        dealer.hit(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        dealer.hit(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));
        dealer.hit(new Card(TrumpNumber.FOUR, TrumpSymbol.CLOVER));

        Participant player = new Player("리버");
        player.hit(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.hit(new Card(TrumpNumber.QUEEN, TrumpSymbol.SPADE));

        assertThat(MatchResult.match(dealer, player).get()).isEqualTo("패");
    }

    @DisplayName("Dealer와 Player가 모두 bust가 아닐때, Dealer가 Player 보다 점수가 낮으면 패배한다.")
    @Test
    void lose_score() {
        Participant dealer = new Dealer();
        dealer.hit(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        dealer.hit(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        Participant player = new Player("리버");
        player.hit(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.hit(new Card(TrumpNumber.QUEEN, TrumpSymbol.SPADE));
        player.hit(new Card(TrumpNumber.ACE, TrumpSymbol.DIAMOND));

        assertThat(MatchResult.match(dealer, player).get()).isEqualTo("패");
    }

    @DisplayName("Dealer와 Player가 모두 blackjack이라면 비긴다.")
    @Test
    void draw_blackjack() {
        Participant dealer = new Dealer();
        dealer.hit(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        dealer.hit(new Card(TrumpNumber.ACE, TrumpSymbol.HEART));

        Participant player = new Player("리버");
        player.hit(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.hit(new Card(TrumpNumber.ACE, TrumpSymbol.DIAMOND));

        assertThat(MatchResult.match(dealer, player).get()).isEqualTo("무");
    }

    @DisplayName("Dealer와 Player가 모두 bust가 아닐때, 점수가 같다면 비긴다.")
    @Test
    void draw_score() {
        Participant dealer = new Dealer();
        dealer.hit(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));
        dealer.hit(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        Participant player = new Player("리버");
        player.hit(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.hit(new Card(TrumpNumber.EIGHT, TrumpSymbol.DIAMOND));

        assertThat(MatchResult.match(dealer, player).get()).isEqualTo("무");
    }
}