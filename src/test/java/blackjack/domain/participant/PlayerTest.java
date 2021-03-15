package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import blackjack.domain.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("배팅 금액이 입력 되었을 때, 베팅금을 가진 새로운 객체를 확인한다.")
    void bettingTest() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        Player player = new Player(new Cards(cardList), "pobi");
        player = player.changeBetting((double) 30000);

        assertThat(player.getBetting()).isEqualTo(30000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 승리했을 때, 2.5배의 배당을 가졌는지 확인한다.")
    void blackJackWinProfitTest() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        Player player = new Player(new Cards(cardList), "pobi");
        player = player.changeBetting((double) 30000);
        player.changeProfit(Result.BLACKJACK_WIN);

        assertThat(player.getProfit()).isEqualTo(30000 * 1.5);
    }

    @Test
    @DisplayName("플레이어가 승리했을 때, 2배의 배당을 가졌는지 확인한다.")
    void winProfitTest() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        Player player = new Player(new Cards(cardList), "pobi");
        player = player.changeBetting((double) 30000);
        player.changeProfit(Result.WIN);

        assertThat(player.getProfit()).isEqualTo(30000 * 1);
    }

    @Test
    @DisplayName("플레이어가 패배했을 때, -1배의 배당을 가졌는지 확인한다.")
    void loseProfitTest() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList.add(new Card(Suit.DIAMOND, Face.KING));
        Player player = new Player(new Cards(cardList), "pobi");
        player = player.changeBetting((double) 30000);
        player.changeProfit(Result.LOSE);

        assertThat(player.getProfit()).isEqualTo(30000 * -1);
    }
}
