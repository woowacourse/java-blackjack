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

class StandTest {

    @Test
    @DisplayName("딜러가 스탠드이고 플레이어가 버스트면 딜러가 승리한다")
    void tie() {
        State dealer = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        State player = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        dealer = dealer.stand();
        player = player.add(new Card(Rank.TEN, Suit.CLUBS));

        assertThat(dealer.isFinished()).isTrue();
        assertThat(player.isFinished()).isTrue();
        assertThat(dealer.compareWith(player)).isSameAs(Result.WIN);
    }

    @Test
    @DisplayName("딜러가 스탠드이고 플레이어가 블랙잭이면 딜러가 패배한다")
    void win() {
        State dealer = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        final State player = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.ACE, Suit.CLUBS));
        dealer = dealer.stand();

        assertThat(dealer.isFinished()).isTrue();
        assertThat(player.isFinished()).isTrue();
        assertThat(dealer.compareWith(player)).isSameAs(Result.LOSE);
    }

    @Test
    @DisplayName("딜러, 플레이어가 스탠드이고 딜러 점수가 더 크면 딜러가 승리한다")
    void compareWin() {
        State dealer = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        State player = Started.ofTwoCard(new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        dealer = dealer.stand();
        player = player.stand();

        assertThat(dealer.isFinished()).isTrue();
        assertThat(player.isFinished()).isTrue();
        assertThat(dealer.compareWith(player)).isSameAs(Result.WIN);
    }

    @Test
    @DisplayName("딜러, 플레이어가 스탠드이고 딜러 점수가 더 작으면 딜러가 패배한다")
    void compareLose() {
        State dealer = Started.ofTwoCard(new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        State player = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        dealer = dealer.stand();
        player = player.stand();

        assertThat(dealer.isFinished()).isTrue();
        assertThat(player.isFinished()).isTrue();
        assertThat(dealer.compareWith(player)).isSameAs(Result.LOSE);
    }

    @Test
    @DisplayName("딜러, 플레이어가 스탠드이고 딜러 점수가 같으면 무승부다")
    void lose() {
        State dealer = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        State player = Started.ofTwoCard(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        dealer = dealer.stand();
        player = player.stand();

        assertThat(dealer.isFinished()).isTrue();
        assertThat(player.isFinished()).isTrue();
        assertThat(dealer.compareWith(player)).isSameAs(Result.TIE);
    }
}
