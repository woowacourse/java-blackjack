package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.CardDeckFactory;
import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsToParticipantTest() {
        // given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);
        //when-then
        assertThat(dealer.hitCard()).isInstanceOf(Card.class);

    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCardsTest() {
        // given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);
        // when-then
        assertDoesNotThrow(dealer::addCards);

    }
}
