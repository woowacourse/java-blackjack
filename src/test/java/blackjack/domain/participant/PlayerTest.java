package blackjack.domain.participant;

import blackjack.domain.ScoreState;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @Test
    @DisplayName("이름을 받아 플레이어를 생성한다")
    void createPlayerTest() {
        Player player = Player.from("boxster");

        assertThat(player.getName()).isEqualTo("boxster");
    }

    @Test
    @DisplayName("이름이 딜러라면 플레이어 생성 시 예외를 반환한다")
    void exceptionPlayerTest() {
        assertThatThrownBy(() -> Player.from("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러라는 이름은 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("카드를 받아 플레이어 카드에 추가한다")
    void addPlayerCardsTest() {
        Player player = Player.from("jamie");
        Card card = new Card(CardSuit.HEART, CardNumber.ACE);

        player.addCard(card);

        assertThat(player.getCards()).containsExactly(card);
    }

    @Test
    @DisplayName("카드 두 장의 합이 21이라면 블랙잭을 반환한다")
    void getBlackjackStateTest() {
        Player player = Player.from("jamie");
        List<Card> cards = List.of(
                new Card(CardSuit.SPADE, CardNumber.KING),
                new Card(CardSuit.HEART, CardNumber.ACE)
        );

        player.addCards(cards);

        assertThat(player.getState()).isEqualTo(ScoreState.BLACKJACK);
    }

    @Test
    @DisplayName("카드 세 장 이상의 합이 21이라면 스테이를 반환한다")
    void getHitStateTest() {
        Player player = Player.from("jamie");
        List<Card> cards = List.of(
                new Card(CardSuit.SPADE, CardNumber.KING),
                new Card(CardSuit.HEART, CardNumber.ACE),
                new Card(CardSuit.HEART, CardNumber.NINE)
        );

        player.addCards(cards);

        assertThat(player.getState()).isEqualTo(ScoreState.STAY);
    }
}
