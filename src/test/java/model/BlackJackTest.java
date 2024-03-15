package model;

import model.card.*;
import model.player.Dealer;
import model.player.Name;
import model.player.Participant;
import model.player.Participants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackJackTest {

    private Dealer dealer;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.TWO))));
        cardDeck = new CardDeck(Card.createCardDeck());
    }

    @DisplayName("참가자가 null일 시 예외가 발생한다.")
    @Test
    void validateParticipantIsNotNull() {
        Assertions.assertThatThrownBy(() -> new BlackJack(null, dealer, cardDeck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러가 null일 시 예외가 발생한다.")
    @Test
    void validateDealerIsNotNull() {
        Cards participantCards = new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.FIVE)));
        Assertions.assertThatThrownBy(
                        () -> new BlackJack(new Participants(List.of(
                                new Participant(participantCards, new Name("배키"), new GameMoney(1000))))
                                , null
                                , cardDeck))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
