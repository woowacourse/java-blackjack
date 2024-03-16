package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어 이름으로 플레이어를 생성한다.")
    @Test
    void createPlayer() {
        //given
        String name = "ted";

        //when
        Player player = new Player(name);
        String playerName = player.getName();

        //then
        assertThat(playerName).isEqualTo(name);
    }

    @DisplayName("플레이어가 카드를 받는다.")
    @Test
    void receiveCard() {
        //given
        Player player = new Player("ted");
        Card card = new Card(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        player.receiveCard(card);
        List<Card> playerDeck = player.getCards();

        //then
        assertThat(playerDeck).containsExactly(card);
    }

    @DisplayName("플레이어의 카드 개수를 확인한다.")
    @Test
    void deckSize() {
        //given
        Player player = new Player("ted");
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.FIVE));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.SEVEN));

        //when
        int deckSize = player.deckSize();

        //then
        assertThat(deckSize).isEqualTo(2);
    }

    @DisplayName("플레이어의 점수를 계산한다.")
    @Test
    void calculateScore() {
        //given
        Player player = new Player("ted");
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.FIVE));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.SEVEN));

        //when
        int totalScore = player.calculateScore().getScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }

    @DisplayName("덱이 버스트인 경우 덱에 있는 Ace 카드의 값을 1로 계산한다.")
    @Test
    void calculateScoreByBust() {
        //given
        Player player = new Player("ted");
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.NINE));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TWO));

        //when
        int score = player.calculateScore().getScore();

        //then
        assertThat(score).isEqualTo(12);
    }

    @DisplayName("덱이 버스트 이고 Ace 카드가 여러장일 때, 버스트가 아니게 되는 최소한의 Ace의 카드만 값을 1로 계산한다.")
    @Test
    void calculateScoreByTwoAce() {
        //given
        Player player = new Player("ted");
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TWO));

        //when
        int score = player.calculateScore().getScore();

        //then
        assertThat(score).isEqualTo(21);
    }

    @DisplayName("플레이어의 스코어가 21 미만이라면 히트할 수 있다.")
    @Test
    void canHitByExpectedTrue() {
        //given
        Player player = new Player("ted");
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));

        //when, then
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("플레이어의 스코어가 21 이상이라면 히트할 수 없다.")
    @Test
    void canHitByExpectedFalse() {
        //given
        Player player = new Player("ted");
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));

        //when, then
        assertThat(player.canHit()).isFalse();
    }
}
