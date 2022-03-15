package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.getCards;

import blackjack.domain.card.Number;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    private List<Name> names;
    private Participants participants;

    @BeforeEach
    void init() {
        names = Arrays.asList(new Name("pobi"), new Name("jason"));

        participants = new Participants(toPlayerList(names),
                new Dealer(new Name("딜러"), getCards(Number.SEVEN, Number.QUEEN)));
    }

    @Test
    @DisplayName("플레이어들 가져오기")
    void find_players() {
        // given
        List<Player> expectPlayers = toPlayerList(names);

        // when
        List<Player> playerNames = participants.getPlayers();

        // then
        assertThat(playerNames).containsAll(expectPlayers);
    }

    private List<Player> toPlayerList(List<Name> names) {
        List<Player> playerList = new ArrayList<>();
        for (Name name : names) {
            playerList.add(new Player(name, getCards(Number.SEVEN, Number.QUEEN)));
        }
        return playerList;
    }

    @Test
    @DisplayName("딜러 가져오기")
    void find_dealer() {
        // then
        assertThat(participants
                .getDealer())
                .isInstanceOf(Dealer.class);
    }
}