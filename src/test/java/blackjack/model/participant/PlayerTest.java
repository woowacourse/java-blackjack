package blackjack.model.participant;

import blackjack.Hand;
import blackjack.model.Name;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.state.InitialState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("게임을 시작하면 플레이어는 두 장씩의 카드를 지급받는다.")
    void player_initial_state_draw() {
        //given
        Player player = new Player(new Name("도치"), new InitialState(new Hand()));

        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.JACK);
        List<Card> cards = List.of(card1, card2);
        CardDeck cardDeck = new CardDeck(cards);

        // when
        player.play(cardDeck);

        //then
        Assertions.assertThat(player.getCards()).containsExactly(card2, card1);
    }
}