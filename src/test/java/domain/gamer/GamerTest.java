package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class GamerTest {
    @Test
    @DisplayName("점수가 제대로 들어가는지 확인하는 테스트" )
    public void calculateTest() {
        Player player = new Player("pobi" );
        player.addCard(Arrays.asList(
                new Card(CardSuit.CLOVER, CardNumber.SEVEN),
                new Card(CardSuit.CLOVER, CardNumber.TEN))
        );
        assertThat(player.result.getScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("에이스를 11로 계산했을때 21이 넘지 않은 경우 에이스를 11로 계산하는 테스트" )
    void isAceHiddenCardTest() {
        Player player = new Player("pobi" );
        player.addCard(Arrays.asList(
                new Card(CardSuit.CLOVER, CardNumber.ACE),
                new Card(CardSuit.CLOVER, CardNumber.JACK))
        );
        assertThat(player.result.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("에이스를 11로 계산했을때 21이 넘어서 에이스를 1로 계산하는 테스트" )
    void name() {
        Player player = new Player("pobi" );
        player.addCard(Arrays.asList(
                new Card(CardSuit.CLOVER, CardNumber.ACE),
                new Card(CardSuit.CLOVER, CardNumber.JACK),
                new Card(CardSuit.HEART, CardNumber.SIX))
        );
        assertThat(player.result.getScore()).isEqualTo(17);
    }
}
