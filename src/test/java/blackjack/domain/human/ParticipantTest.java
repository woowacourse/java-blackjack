package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.human.Dealer;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ParticipantTest {
    private final Players players = Players.fromNames(List.of("pobi"));
    private Participant participant;

    @BeforeEach
    void setup() {
        participant = Participant.from(players);
    }

    @Test
    @DisplayName("참여자 초기 카드 나눠주기 기능 테스트")
    void initCardTest() {
        CardDeck cardDeck = CardDeck.newInstance();
        participant.initCard(cardDeck);
        assertAll(
                () -> assertThat(participant.getPlayers().get().size())
                        .isEqualTo(1),
                () -> assertThat(participant.getPlayers().get().get(0).getRawCards().size())
                        .isEqualTo(2),
                () -> assertThat(participant.getDealer().getRawCards().size())
                        .isEqualTo(2)
        );
    }

    @Test
    @DisplayName("참여자 getPlayers() 기능 테스트")
    void getPlayersTest() {
        assertThat(participant.getPlayers())
                .isEqualTo(players);
    }

    @Test
    @DisplayName("참여자 getRawPlayers() 기능 테스트")
    void getRawPlayersTest() {
        assertThat(participant.getRawPlayers())
                .contains(players.get().get(0));
    }

    @Test
    @DisplayName("getDealer() 기능 테스트")
    void getDealer() {
        assertThat(participant.getDealer())
                .isInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("getPlayers() 기능 테스트")
    void getPlayers() {
        assertThat(participant.getPlayers().getPlayerNames())
                .containsOnly("pobi");
    }
}
