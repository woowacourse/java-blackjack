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

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("플레이어 처음 카드 분배 - 성공 : 두 장의 카드를 지급받는다.")
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
    @DisplayName("플레이어의 처음 분배 카드를 조회 - 실패 : 받은 카드가 없을 경우 예외 발생")
    void player_firstDistributed_card_fail() {
        //given
        Player player = new Player(new Name("도치"), new InitialState(new HandCard()));

        //then
        assertThatThrownBy(player::firstDistributedCard).isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 분배 받지 않은 상태입니다.");
    }

    @Test
    @DisplayName("플레이어의 처음 분배 카드를 조회 - 성공 : 분배된 순으로 2장을 노출한다.")
    void player_firstDistributed_card_success() {
        //given
        Player player = new Player(new Name("도치"), new InitialState(new HandCard()));

        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.NINE);
        Card card4 = Card.of(CardSuit.HEART, CardNumber.EIGHT);
        List<Card> cards = List.of(card1, card2, card3, card4);
        CardDeck cardDeck = new CardDeck(cards);

        // when
        player.draw(cardDeck);
        List<Card> firstDistributedCard = player.firstDistributedCard();
        //then
        Assertions.assertThat(firstDistributedCard).containsExactly(card4, card3);
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