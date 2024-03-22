package domain.state.fininsh;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.player.Result;
import domain.state.Started;
import domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {
    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 블랙잭이면 무승부다")
    void tie() {
        final State dealer = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));
        final State player = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));

        assertThat(dealer.isFinished()).isTrue();
        assertThat(player.isFinished()).isTrue();
        assertThat(dealer.compareWith(player)).isSameAs(Result.TIE);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 버스트면 딜러가 승리한다")
    void win() {
        final State dealer = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));
        State player = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        player = player.add(new Card(Rank.TEN, Suit.CLUBS));

        assertThat(dealer.isFinished()).isTrue();
        assertThat(player.isFinished()).isTrue();
        assertThat(dealer.compareWith(player)).isSameAs(Result.WIN);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 스탠드이면 딜러가 승리한다")
    void lose() {
        final State dealer = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));
        State player = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        player = player.stand();

        assertThat(dealer.isFinished()).isTrue();
        assertThat(player.isFinished()).isTrue();
        assertThat(dealer.compareWith(player)).isSameAs(Result.WIN);
    }
}
