package domain;

import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;
import static domain.card.Number.SEVEN;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.CardDeckFactory;
import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsToParticipantTest(){
        //given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Dealer dealer = new Dealer(cardDeckFactory.create());
        Players players = Players.from(List.of("pobi", "lisa"));

        //when
        BlackJack blackJack = new BlackJack(players, dealer);

        // then
        assertDoesNotThrow(blackJack::hitCardsToParticipant);
    }
}
