package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 플레이어가_카드를_받는다() {
        Player player = new Player("모카");
        player.receiveCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        assertThat(player.getCardCount()).isEqualTo(1);
    }

    @Test
    void 플레이어_버스트로_카드를_받을_수_없다() {
        Player player = new Player("이산");
        player.receiveCard(new Card(CardPoint.KING, CardPattern.DIAMOND));
        player.receiveCard(new Card(CardPoint.EIGHT, CardPattern.CLUB));
        player.receiveCard(new Card(CardPoint.FOUR, CardPattern.HEART));
        player.receiveCard(new Card(CardPoint.THREE, CardPattern.SPADE));
        assertThat(player.getCardCount()).isEqualTo(3);
    }

    @Test
    void 플레이어는_버스트로_패이다() {
        Player player = new Player("이산");
        player.receiveCard(new Card(CardPoint.KING, CardPattern.DIAMOND));
        player.receiveCard(new Card(CardPoint.QUEEN, CardPattern.CLUB));
        player.receiveCard(new Card(CardPoint.TWO, CardPattern.SPADE));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPoint.EIGHT, CardPattern.SPADE));
        dealer.receiveCard(new Card(CardPoint.TEN, CardPattern.HEART));

        GameResult result = player.compareResult(dealer);
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러의_버스트로_플레이어_승이다() {
        Player player = new Player("이산");
        player.receiveCard(new Card(CardPoint.KING, CardPattern.DIAMOND));
        player.receiveCard(new Card(CardPoint.QUEEN, CardPattern.CLUB));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPoint.SIX, CardPattern.SPADE));
        dealer.receiveCard(new Card(CardPoint.SEVEN, CardPattern.HEART));
        dealer.receiveCard(new Card(CardPoint.NINE, CardPattern.CLUB));

        GameResult result = player.compareResult(dealer);
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    void 딜러와_플레이어의_포인트를_비교한다_플레이어_승() {
        Player player = new Player("이산");
        player.receiveCard(new Card(CardPoint.KING, CardPattern.DIAMOND));
        player.receiveCard(new Card(CardPoint.QUEEN, CardPattern.CLUB));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPoint.SEVEN, CardPattern.HEART));
        dealer.receiveCard(new Card(CardPoint.ACE, CardPattern.CLUB));

        GameResult result = player.compareResult(dealer);
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    void 딜러와_플레이어의_포인트를_비교한다_무승부() {
        Player player = new Player("이산");
        player.receiveCard(new Card(CardPoint.KING, CardPattern.DIAMOND));
        player.receiveCard(new Card(CardPoint.QUEEN, CardPattern.CLUB));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPoint.SEVEN, CardPattern.HEART));
        dealer.receiveCard(new Card(CardPoint.NINE, CardPattern.CLUB));
        dealer.receiveCard(new Card(CardPoint.FOUR, CardPattern.DIAMOND));

        GameResult result = player.compareResult(dealer);
        assertThat(result).isEqualTo(GameResult.TIE);
    }

    @Test
    void 딜러와_플레이어의_포인트를_비교한다_플레이어_패() {
        Player player = new Player("이산");
        player.receiveCard(new Card(CardPoint.KING, CardPattern.DIAMOND));
        player.receiveCard(new Card(CardPoint.QUEEN, CardPattern.CLUB));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPoint.ACE, CardPattern.CLUB));
        dealer.receiveCard(new Card(CardPoint.JACK, CardPattern.DIAMOND));

        GameResult result = player.compareResult(dealer);
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러와_플레이어의_포인트를_비교한다_플레이어_블랙잭승() {
        Player player = new Player("이산");
        player.receiveCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        player.receiveCard(new Card(CardPoint.KING, CardPattern.CLUB));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPoint.ACE, CardPattern.CLUB));
        dealer.receiveCard(new Card(CardPoint.NINE, CardPattern.DIAMOND));

        GameResult result = player.compareResult(dealer);
        assertThat(result).isEqualTo(GameResult.BLACKJACK_WIN);
    }

    @Test
    void 딜러와_플레이어의_포인트를_비교한다_플레이어_블랙잭무승부() {
        Player player = new Player("이산");
        player.receiveCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        player.receiveCard(new Card(CardPoint.KING, CardPattern.CLUB));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPoint.ACE, CardPattern.CLUB));
        dealer.receiveCard(new Card(CardPoint.JACK, CardPattern.DIAMOND));

        GameResult result = player.compareResult(dealer);
        assertThat(result).isEqualTo(GameResult.TIE);
    }
}