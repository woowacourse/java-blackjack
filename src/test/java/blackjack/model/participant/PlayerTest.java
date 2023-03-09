package blackjack.model.participant;

import blackjack.model.card.HandCard;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("플레이어 처음 카드 분배 - 성공 : 두 장의 카드를 지급받는다.")
    void player_initial_state_draw() {
        //given
        Player player = new Player(new Name("도치"));

        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.JACK);
        List<Card> cards = List.of(card1, card2);
        CardDeck cardDeck = new CardDeck(cards);

        // when
        player.drawFirstTurnCards(cardDeck);

        //then
        assertThat(player.getCards()).containsExactly(card2, card1);
    }

    @Test
    @DisplayName("플레이어의 처음 분배 카드를 조회 - 실패 : 받은 카드가 없을 경우 예외 발생")
    void player_firstDistributed_card_fail() {
        //given
        Player player = new Player(new Name("도치"));

        //then
        assertThatThrownBy(player::firstDistributedCard).isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 분배 받지 않은 상태입니다.");
    }

    @Test
    @DisplayName("플레이어의 처음 분배 카드를 조회 - 성공 : 분배된 순으로 2장을 노출한다.")
    void player_firstDistributed_card_success() {
        //given
        Player player = new Player(new Name("도치"));

        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.NINE);
        Card card4 = Card.of(CardSuit.HEART, CardNumber.EIGHT);
        List<Card> cards = List.of(card1, card2, card3, card4);
        CardDeck cardDeck = new CardDeck(cards);

        // when
        player.drawFirstTurnCards(cardDeck);
        Map<String, List<Card>> firstDistributedCard = player.firstDistributedCard();

        //then
        assertThat(firstDistributedCard.get("도치")).containsExactly(card4, card3);
    }

    @Test
    @DisplayName("플레이어 Bust 상태에서 draw - 실패 : 버스트 상태에서 draw 하면 예외발생")
    void player_bust_draw_fail() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.NINE);
        Card card4 = Card.of(CardSuit.HEART, CardNumber.EIGHT);

        List<Card> cards = List.of(card1, card2, card3, card4);
        Player player = new Player(new Name("도치"), new HandCard(new ArrayList<>(cards)));
        CardDeck cardDeck = new CardDeck(cards);

        //then
        assertThatThrownBy(() -> player.draw(cardDeck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 더 뽑을 수 없는 상태입니다.");
    }

    @Test
    @DisplayName("플레이어 blackjack 상태 도달 - 성공 : 첫 분배에서 점수합이 21이면 blackjack 상태가 된다.")
    void player_change_to_blackjack_success() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.JACK);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.ACE);

        List<Card> cards = List.of(card1, card2);
        Player player = new Player(new Name("도치"));
        CardDeck cardDeck = new CardDeck(cards);

        // when
        player.drawFirstTurnCards(cardDeck);

        //then
        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("플레이어 blackjack 상태 draw - 실패 : blackjack 상태에서 draw 하면 예외발생")
    void player_when_blackjack_draw_fail() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.JACK);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.ACE);

        List<Card> cards = List.of(card1, card2);
        Player player = new Player(new Name("도치"), new HandCard(cards));
        Card card3 = Card.of(CardSuit.HEART, CardNumber.EIGHT);
        CardDeck cardDeck = new CardDeck(List.of(card3));

        //then
        assertThatThrownBy(() -> player.draw(cardDeck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 더 뽑을 수 없는 상태입니다.");
    }
}
