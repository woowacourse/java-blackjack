package model.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import model.card.Deck;
import model.card.RandomShuffleMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private Participants participants;
    private Dealer dealer;
    private Player bebe;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        participants = Participants.of(List.of("bebe"), dealer);
        bebe = participants.getPlayers().get(0);
    }

    @DisplayName("receiveInitialCards가 두 장의 카드를 주는지 확인한다.")
    @Test
    void receiveInitialCards() {
        // given
        participants.receiveInitialCards(Deck.create(new RandomShuffleMaker()));

        // when, then
        assertAll(
                () -> assertThat(bebe.getHand().getCards()).hasSize(2),
                () -> assertThat(dealer.getHand().getCards()).hasSize(2)
        );
    }
}
