package blackjack.domain.player;

import blackjack.domain.Status;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import blackjack.exception.CardDuplicateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("카드를 추가할 때 중복되면 예외를 발생한다.")
    void addCardToDeck_CardDuplicateException() {
        Player player = new Gamer("player", 1);
        Card card1 = new Card(Symbol.CLOVER, CardNumber.EIGHT);
        Card card2 = new Card(Symbol.CLOVER, CardNumber.EIGHT);

        player.addCardToDeck(card1);
        Assertions.assertThatThrownBy(
                () -> player.addCardToDeck(card2)
        ).isInstanceOf(CardDuplicateException.class);
    }

    @Test
    @DisplayName("덱의 점수를 계산한다")
    void getScore() {
        Player player = new Gamer("player", 1);

        player.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.FIVE));
        player.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.SIX));

        assertThat(player.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("각 플레이어 덱에 카드를 추가한다.")
    void addCardToDeck() {
        Player player = new Gamer("player", 1);
        Card card = new Card(Symbol.CLOVER, CardNumber.EIGHT);

        player.addCardToDeck(card);
        List<Card> deck = player.getDeckAsList();

        assertThat(deck).containsExactly(card);
    }


    @Test
    @DisplayName("플레이어의 덱을 리스트로 반환")
    void getDeckAsList() {
        Player player = new Gamer("player", 1);
        Card card = new Card(Symbol.CLOVER, CardNumber.EIGHT);

        player.addCardToDeck(card);

        assertThat(
                player.getDeckAsList().stream().map(Card::getName).collect(toList())
        ).containsExactly(card.getName());
    }

    @Test
    @DisplayName("플레이어의환 덱을 보고 상태를 반환")
    void getStatus() {
        Player player = new Gamer("player", 1);
        Card card = new Card(Symbol.CLOVER, CardNumber.JACK);

        player.addCardToDeck(card);
        assertThat(player.getStatus()).isEqualTo(Status.HIT);

        card = new Card(Symbol.CLOVER, CardNumber.KING);
        player.addCardToDeck(card);
        player.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.ACE));
        assertThat(player.getStatus()).isEqualTo(Status.BLACKJACK);

        card = new Card(Symbol.CLOVER, CardNumber.QUEEN);
        player.addCardToDeck(card);
        assertThat(player.getStatus()).isEqualTo(Status.BURST);
    }

    @Test
    @DisplayName("이름이 같으면 true")
    void isSameName() {
        Player player = new Gamer("pobi", 1);
        assertThat(player.isSameName("pobi")).isTrue();
    }

    @Test
    @DisplayName("자신의 덱을 보고 상태 boolean 반환")
    void isDrawable() {
        Player player = new Gamer("pobi", 1);

        player.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.EIGHT));
        assertThat(player.isDrawable()).isTrue();

        player.addCardToDeck(new Card(Symbol.HEART, CardNumber.KING));
        player.addCardToDeck(new Card(Symbol.HEART, CardNumber.QUEEN));
        assertThat(player.isDrawable()).isFalse();

        player = new Dealer();
        player.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.EIGHT));
        assertThat(player.isDrawable()).isTrue();

        player.addCardToDeck(new Card(Symbol.HEART, CardNumber.EIGHT));
        assertThat(player.isDrawable()).isFalse();

    }
}