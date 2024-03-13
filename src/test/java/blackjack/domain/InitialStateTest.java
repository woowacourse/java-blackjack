package blackjack.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialStateTest {

    @DisplayName("초기 상태에서 블랙잭이 아닌 핸드를 받으면 HitState가 반횐된다")
    @Test
    public void hitState() {
        InitialState initialState = new InitialState();
        Deck deck = Deck.of(new CardFactory(), cards -> cards);

        State newState = initialState.draw(deck);

        assertThat(newState).isInstanceOf(HitState.class);
    }

    @DisplayName("초기 상태에서 블랙잭인 핸드를 받으면 BlackJackState가 반환된다")
    @Test
    public void blackJackState() {
        InitialState initialState = new InitialState();
        Deck deck = Deck.of(new CardFactory(),
                cards -> cards.stream().filter(card -> card.isAce() || card.getDenomination().equals(Denomination.KING))
                        .toList());

        State newState = initialState.draw(deck);

        assertThat(newState).isInstanceOf(BlackJackState.class);
    }
}
