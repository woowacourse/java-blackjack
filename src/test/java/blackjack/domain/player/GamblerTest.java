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

class GamblerTest {

    @Test
    @DisplayName("Gambler 객체 정상적으로 생성된다.")
    public void createGambler() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);

        assertThat(gambler.getName()).isEqualTo("test");
        assertThat(gambler.getMoney()).isEqualTo(1000);
    }

    @Test
    @DisplayName("Gambler 카드 추가 시 정상적으로 반영된다.")
    public void addCardGambler() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);

        assertThat(gambler.getPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("Gambler가 BlackJack일 때 isHit()를 호출하면 거짓을 반환한다.")
    public void isNonHitTest() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);

        assertThat(gambler.isHit()).isEqualTo(false);
    }

    @Test
    @DisplayName("Gambler가 21점보다 낮을 때 isHit()를 호출하면 참을 반환한다.")
    public void isHitTest() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_TWO);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);

        assertThat(gambler.isHit()).isEqualTo(true);
    }

    @Test
    @DisplayName("Gambler가 초기 지급된 카드 목록 사이즈라면 isFirstQuestion()은 참을 반환한다.")
    public void isFirstQuestion() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_TWO);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);

        assertThat(gambler.isFirstQuestion()).isEqualTo(true);
    }

    @Test
    @DisplayName("Gambler가 카드를 추가로 지급받았다면 isFirstQuestion()은 거짓을 반환한다.")
    public void isNonFirstQuestion() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_TWO);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);
        gambler.addCard(SPADE_ACE);

        assertThat(gambler.isFirstQuestion()).isEqualTo(false);
    }

    @Test
    @DisplayName("Gambler가 BlackJack이라면 State는 BlackJack 클래스가 된다.")
    public void isBlackJack() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);

        assertThat(gambler.getState()).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("Gambler가 Bust라면 State는 Bust 클래스가 된다.")
    public void isBustJack() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_TWO);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);
        gambler.addCard(SPADE_JACK);

        assertThat(gambler.getState()).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Gambler가 BlackJack일 때 카드를 추가로 지급받는다면 예외가 발생한다.")
    public void isStayJack() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);

        assertThatThrownBy(() -> gambler.addCard(SPADE_JACK))
            .isInstanceOf(IllegalStateException.class);
    }
}
