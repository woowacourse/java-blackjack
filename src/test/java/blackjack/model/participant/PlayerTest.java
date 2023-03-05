package blackjack.model.participant;

import blackjack.model.card.HandCard;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.state.DrawState;
import blackjack.model.state.InitialState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PlayerTest {

    @Test
    @DisplayName("게임을 시작하면 플레이어는 두 장씩의 카드를 지급받는다.")
    void player_initial_state_draw() {
        //given
        Player player = new Player(new Name("도치"), new InitialState(new HandCard()));

        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.JACK);
        List<Card> cards = List.of(card1, card2);
        CardDeck cardDeck = new CardDeck(cards);

        // when
        player.draw(cardDeck);

        //then
        Assertions.assertThat(player.getCards()).containsExactly(card2, card1);
    }

    @Test
    @DisplayName("draw상태의 플레이어는 버스트가 될 때까지 카드를 뽑을 수 있다.")
    void player_can_draw_until_bust() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.NINE);
        Card card4 = Card.of(CardSuit.HEART, CardNumber.EIGHT);

        List<Card> cards = List.of(card4, card3);
        Player player = new Player(new Name("도치"), new DrawState(new HandCard(new ArrayList<>(List.of(card1, card2)))));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        player.draw(cardDeck);

        //then
        Assertions.assertThatThrownBy(() -> player.draw(cardDeck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("버스트 상태에서는 카드를 더 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("blackjack 상태의 플레이어는 카드를 뽑을 수 없다.")
    void player_when_blackjack_can_not_draw() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.JACK);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.ACE);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.KING);

        List<Card> cards = List.of(card1, card2, card3);
        Player player = new Player(new Name("도치"), new InitialState(new HandCard()));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        player.draw(cardDeck);

        //then
        Assertions.assertThatThrownBy(() -> player.draw(cardDeck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("블랙잭 상태에서는 카드를 더 뽑을 수 없습니다.");
    }
}