package blackjack.domain.player;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_JACK;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.state.BlackJack;
import blackjack.domain.state.Bust;
import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 카드 추가 시 정상적으로 반영된다.")
    public void dealerAddCardTest() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);

        Dealer dealer = Dealer.of(cardDeck);
        System.out.println(dealer.getPoint());
//        assertThat(dealer.getPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("딜러가 BlackJack이라면 isHit()가 거짓을 반환한다.")
    public void isHit() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);

        Dealer dealer = Dealer.of(cardDeck);

        assertThat(dealer.isHit()).isEqualTo(false);
    }

    @Test
    @DisplayName("딜러가 Hit라면 isHit()가 참을 반환한다.")
    public void isNonHit() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_TWO);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);

        Dealer dealer = Dealer.of(cardDeck);

        assertThat(dealer.isHit()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 BlackJack이라면 상태는 BlackJack 클래스가 된다.")
    public void isBlackJack() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);

        Dealer dealer = Dealer.of(cardDeck);

        assertThat(dealer.getState()).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("딜러가 Bust이라면 상태는 Bust 클래스가 된다.")
    public void isBustJack() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_TWO);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);

        Dealer dealer = Dealer.of(cardDeck);
        dealer.addCard(SPADE_JACK);

        assertThat(dealer.getState()).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("딜러가 BlackJack이라면 카드를 추가로 뽑을 때 예외가 발생한다.")
    public void isStayJack() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_JACK);
        cards.add(SPADE_ACE);
        CardDeck cardDeck = CardDeck.generate(cards);

        Dealer dealer = Dealer.of(cardDeck);

        assertThatThrownBy(() -> dealer.addCard(SPADE_JACK))
            .isInstanceOf(IllegalStateException.class);
    }
}
