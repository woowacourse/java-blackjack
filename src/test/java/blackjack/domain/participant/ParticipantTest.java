package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.BETTING_1000;
import static utils.TestUtil.CLOVER_QUEEN;
import static utils.TestUtil.CLOVER_SEVEN;
import static utils.TestUtil.getCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    private List<Name> names;
    private Participants participants;

    @BeforeEach
    void init() {
        names = Arrays.asList(new Name("pobi"), new Name("jason"));

        Map<Name, BettingAmount> participantInfos = new HashMap<>();
        for (Name name : names) {
            participantInfos.put(name, BETTING_1000);
        }
        participants = new Participants(participantInfos);
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
            playerList.add(new Player(name, getCards(CLOVER_SEVEN, CLOVER_QUEEN), BETTING_1000));
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
