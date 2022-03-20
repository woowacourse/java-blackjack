package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    @DisplayName("참가자가 카드를 더 받을 수 있는 점수일 때 확인")
    void canAddParticipantCardTest() {
        Player player = new Participant("배카라");

        player.addCard(new Card(Denomination.ACE, Suit.DIAMOND));
        player.addCard(new Card(Denomination.THREE, Suit.DIAMOND));

        Score score = new Score(player);

        assertThat(score.CanAddPlayerCard()).isTrue();
    }

    @Test
    @DisplayName("참가자가 카드를 더 받을 수 없는 점수일 때 확인")
    void cantAddParticipantCardTest() {
        Player player = new Participant("배카라");

        player.addCard(new Card(Denomination.TEN, Suit.HEART));
        player.addCard(new Card(Denomination.THREE, Suit.DIAMOND));
        player.addCard(new Card(Denomination.TEN, Suit.DIAMOND));

        Score score = new Score(player);

        assertThat(score.CanAddPlayerCard()).isFalse();
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는 점수일 때 확인")
    void canAddDealerCardTest() {
        Player dealer = new Dealer();

        dealer.addCard(new Card(Denomination.TEN, Suit.HEART));
        dealer.addCard(new Card(Denomination.THREE, Suit.DIAMOND));

        Score score = new Score(dealer);

        assertThat(score.canAddDealerCard()).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 없는 점수일 때 확인")
    void cantAddDealerCardTest() {
        Player dealer = new Dealer();

        dealer.addCard(new Card(Denomination.TEN, Suit.HEART));
        dealer.addCard(new Card(Denomination.THREE, Suit.DIAMOND));
        dealer.addCard(new Card(Denomination.TEN, Suit.DIAMOND));

        Score score = new Score(dealer);

        assertThat(score.canAddDealerCard()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아닐 때 확인")
    void isNotBlackJackScoreTest() {
        Player player = new Player("배카라") {
            @Override
            public boolean canAddCard() {
                return false;
            }
        };

        player.addCard(new Card(Denomination.ACE, Suit.HEART));
        player.addCard(new Card(Denomination.ACE, Suit.DIAMOND));

        Score score = new Score(player);

        assertThat(score.isBlackJack()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 블랙잭인지 확인")
    void isBlackJackTest() {
        Player player = new Player("배카라") {
            @Override
            public boolean canAddCard() {
                return false;
            }
        };

        player.addCard(new Card(Denomination.ACE, Suit.HEART));
        player.addCard(new Card(Denomination.TEN, Suit.DIAMOND));

        Score score = new Score(player);

        assertThat(score.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 블랙잭 카드 사이즈가 아닐 때 확인")
    void isNotBlackJackSizeTest() {
        Player player = new Player("배카라") {
            @Override
            public boolean canAddCard() {
                return false;
            }
        };

        player.addCard(new Card(Denomination.TEN, Suit.HEART));
        player.addCard(new Card(Denomination.THREE, Suit.HEART));
        player.addCard(new Card(Denomination.EIGHT, Suit.HEART));

        Score score = new Score(player);

        assertThat(score.isBlackJack()).isFalse();
    }
}
