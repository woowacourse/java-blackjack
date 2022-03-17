package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.cards.CardDeck;
import blackjack.domain.human.humans.Participant;
import blackjack.domain.human.humans.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ParticipantTest {
    private final Players players = Players.fromText("pobi");
    private Participant participant;

    @BeforeEach
    void setup() {
        participant = Participant.from(players);
    }

    @Test
    @DisplayName("참여자 초기 카드 나눠주기 기능 검사")
    void initCardTest() {
        CardDeck cardDeck = CardDeck.newInstance();
        participant.initCard(cardDeck);
        assertAll(
                () -> assertThat(participant.getPlayers().get().size()).isEqualTo(1),
                () -> assertThat(participant.getPlayers().get().get(0).getCards().size()).isEqualTo(2),
                () -> assertThat(participant.getDealer().getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("참여자 getPlayers() 기능 검사")
    void getPlayersTest() {
        assertThat(participant.getPlayers()).isEqualTo(players);
    }

    @Test
    @DisplayName("참여자 getRawPlayers() 기능 검사")
    void getRawPlayersTest() {
        assertThat(participant.getRawPlayers()).contains(players.get().get(0));
    }
}
