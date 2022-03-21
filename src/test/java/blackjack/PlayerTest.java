package blackjack;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Denomination;
import blackjack.domain.State;
import blackjack.domain.Suit;
import blackjack.domain.user.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름 없이 생성 안되는지 확인")
    @Test
    void generateTest() {
        assertThatThrownBy(() -> Player.generate("")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름 공백 생성 안되는지 확인하는 테스트")
    @Test
    void generateTest2() {
        assertThatThrownBy(() -> Player.generate(" ")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("초기 state가 true인지 확인하는 테스트")
    @Test
    void isHitTest() {
        assertThat(Player.generate("pobi").isStateSame(State.HIT)).isTrue();
    }

    @DisplayName("state가 stay로 설정되었을때 false인지 확인하는 테스트")
    @Test
    void isHitTest2() {
        Player player = Player.generate("pobi");
        player.setStateStayIfSatisfied(true);
        assertThat(player.isStateSame(State.HIT)).isFalse();
    }

    @DisplayName("블랙잭 조건 만족했을때 true인지 확인하는 테스트")
    @Test
    void isBlackjackTest() {
        Player player = Player.generate("pobi");
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.ACE),
                        Card.generate(Suit.DIAMOND, Denomination.TEN)));
        player.drawInitialCards(deck);
        assertThat(player.isStateSame(State.BLACKJACK)).isTrue();
    }

    @DisplayName("합은 21이지만 카트가 3장일때 false인지 확인하는 테스트")
    @Test
    void isBlackjackTest2() {
        Player player = Player.generate("pobi");
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.EIGHT),
                        Card.generate(Suit.DIAMOND, Denomination.SEVEN),
                        Card.generate(Suit.DIAMOND, Denomination.SIX)));
        player.drawInitialCards(deck);
        player.drawAdditionalCard(deck);
        assertThat(player.isStateSame(State.BLACKJACK)).isFalse();
    }

    @Test
    void isBurstTest_true() {
        Player player = Player.generate("pobi");
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.TEN),
                        Card.generate(Suit.DIAMOND, Denomination.NINE),
                        Card.generate(Suit.DIAMOND, Denomination.EIGHT)));
        player.drawInitialCards(deck);
        player.drawAdditionalCard(deck);
        assertThat(player.isStateSame(State.BUST)).isTrue();
    }

    @Test
    void isBurstTest_false() {
        Player player = Player.generate("pobi");
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.TWO),
                        Card.generate(Suit.DIAMOND, Denomination.THREE),
                        Card.generate(Suit.DIAMOND, Denomination.FOUR)));
        player.drawInitialCards(deck);
        player.drawAdditionalCard(deck);
        assertThat(player.isStateSame(State.BUST)).isFalse();
    }

    @Test
    void pickOpenCardsTest() {
        Player player = Player.generate("pobi");
        Deck deck = Deck.makeIntendedShuffledDeck(
                List.of(Card.generate(Suit.DIAMOND, Denomination.TWO),
                        Card.generate(Suit.DIAMOND, Denomination.THREE)));
        player.drawInitialCards(deck);
        assertThat(player.pickOpenCards().numberOfCards()).isEqualTo(2);
    }
}
