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
    @DisplayName("플레이어 생성 테스트")
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
    @DisplayName("플레이어 카드 추가 테스트")
    public void addCardGambler() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);
        assertThat(gambler.getPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 점수가 21점일 때 Hit가 아닌지 테스트")
    public void isNonHitTest() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);

        assertThat(gambler.isHit()).isEqualTo(false);
    }

    @Test
    @DisplayName("카드 점수가 21점보다 적을 때 Hit인지 테스트")
    public void isHitTest() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_TWO);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);
        assertThat(gambler.isHit()).isEqualTo(true);
    }

    @Test
    @DisplayName("카드 지급 여부 질문이 첫번째인지")
    public void isFirstQuestion() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_TWO);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);
        assertThat(gambler.isFirstQuestion()).isEqualTo(true);
    }

    @Test
    @DisplayName("카드 지급 여부 질문이 첫번째가 아닌지 ")
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
    @DisplayName("참여자 Hit일 경우 테스트")
    public void isHit() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);
        assertThat(gambler.isHit()).isEqualTo(false);
    }

    @Test
    @DisplayName("참여자 Hit가 아닐 경우 테스트")
    public void isNonHit() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_TWO);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);
        assertThat(gambler.isHit()).isEqualTo(true);
    }

    @Test
    @DisplayName("참여자 BlackJack 테스트")
    public void isBlackJack() {
        Stack<Card> cards = new Stack<>();
        cards.add(SPADE_ACE);
        cards.add(SPADE_JACK);
        CardDeck cardDeck = CardDeck.generate(cards);
        Gambler gambler = Gambler.of(Name.of("test"), 1000, cardDeck);
        assertThat(gambler.getState()).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("참여자 Bust 테스트")
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
    @DisplayName("참여자 BlackJack일 때 카드 뽑을 경우 예외 발생 테스트")
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
