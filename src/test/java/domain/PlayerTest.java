package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import domain.player.Status;
import domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @Test
    void player가_카드를_뽑으면_cards_size가_1_증가한다() {
        //given
        Cards cards = new Cards();
        Player player = new Player(new PlayerName("judi"));
        //when
        int prevSize = cards.getCards().size();
        player.drawCard(new Card(Suit.HEART, Denomination.NINE));
        int nowSize = player.getCards().size();
        //then
        assertThat(nowSize).isEqualTo(prevSize + 1);
    }

    @Test
    @DisplayName("player getScore 메서드 테스트")
    void playerGetScoreTest() {
        //given
        Player player = new Player(new PlayerName("jude"));
        Card card = new Card(Suit.HEART, Denomination.NINE);
        //when
        player.drawCard(card);
        Score score = player.getScore();
        //then
        assertThat(score.getValue()).isEqualTo(9);
    }

    @Test
    @DisplayName("player와 dealer 모두 blackjack이라면 비긴다.")
    void dealerCompareWithPlayersTest2() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player(new PlayerName("jude"));
        //when
        dealer.drawCard(new Card(Suit.HEART, Denomination.ACE));
        dealer.drawCard(new Card(Suit.HEART, Denomination.JACK));
        player.drawCard(new Card(Suit.HEART, Denomination.ACE));
        player.drawCard(new Card(Suit.HEART, Denomination.JACK));
        Status status = player.compareWithDealer(dealer);
        //then
        assertThat(status).isSameAs(Status.DRAW);
    }

    @Test
    @DisplayName("player와 dealer가 둘다 버스트라면 dealer가 이긴다.")
    void dealerCompareWithPlayersTest3() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player(new PlayerName("jude"));
        //when
        dealer.drawCard(new Card(Suit.HEART, Denomination.JACK));
        dealer.drawCard(new Card(Suit.HEART, Denomination.JACK));
        dealer.drawCard(new Card(Suit.HEART, Denomination.JACK));
        player.drawCard(new Card(Suit.HEART, Denomination.JACK));
        player.drawCard(new Card(Suit.HEART, Denomination.JACK));
        player.drawCard(new Card(Suit.HEART, Denomination.JACK));
        Status status = player.compareWithDealer(dealer);
        //then
        assertThat(status).isSameAs(Status.LOSE);
    }

    @Test
    @DisplayName("player가 blackjack이라면 BLACKJACK_LOSE를 반환한다")
    void blackjackLoseTest() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player(new PlayerName("jude"));
        player.drawCard(new Card(Suit.HEART, Denomination.ACE));
        player.drawCard(new Card(Suit.HEART, Denomination.JACK));
        //when
        Status status = player.compareWithDealer(dealer);
        //then
        assertThat(status).isSameAs(Status.BLACKJACK_WIN);
    }
}
