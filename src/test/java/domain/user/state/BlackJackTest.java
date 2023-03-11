package domain.user.state;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.QUEEN;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.TEN;
import static domain.card.Suits.DIAMOND;
import static domain.game.Winning.BLACKJACK;
import static domain.game.Winning.PUSH;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    State state;

    @BeforeEach
    void setUpBlackJackState() {
        this.state = new Ready()
            .draw(Card.of(ACE, DIAMOND))
            .draw(Card.of(JACK, DIAMOND)); // Ready -> BlackJack
    }

    @DisplayName("딜러가 Bust일시 블랙잭으로 승리한다.")
    @Test
    void match_Bust_BlackJack() {
        State dealer = new Ready()
            .draw(Card.of(TEN, DIAMOND))
            .draw(Card.of(JACK, DIAMOND)) // Ready -> Running
            .draw(Card.of(QUEEN, DIAMOND)); // Running -> Terminated(Bust)

        assertThat(state.match(dealer)).isSameAs(BLACKJACK);
    }

    @DisplayName("딜러가 Stay일시 블랙잭으로 승리한다.")
    @Test
    void match_Stay_BlackJack() {
        State dealer = new Ready()
            .draw(Card.of(TEN, DIAMOND))
            .draw(Card.of(SIX, DIAMOND)) // Ready -> Running
            .draw(Card.of(FOUR, DIAMOND))
            .stay(); // Running -> Terminated(Stay)

        assertThat(state.match(dealer)).isSameAs(BLACKJACK);
    }

    @DisplayName("딜러가 BlackJack일시 무승부다.")
    @Test
    void match_BlackJack_Push() {
        State dealer = new Ready()
            .draw(Card.of(ACE, DIAMOND))
            .draw(Card.of(JACK, DIAMOND)); // Ready -> BlackJack

        assertThat(state.match(dealer)).isSameAs(PUSH);
    }
}
