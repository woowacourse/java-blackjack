package participant;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.BlackjackParticipant;
import domain.participant.BlackjackParticipantsManager;
import domain.participant.Dealer;
import domain.participant.Player;
import exception.BlackJackException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BlackjackParticipantsManagerTest {

    @Test
    void 중복된_닉네임을_가질수_없다() {

        List<BlackjackParticipant> players = new ArrayList<>();
        players.add(new Player("투다", new ArrayList<>()));
        players.add(new Player("투다", new ArrayList<>()));
        Dealer dealer = new Dealer(new ArrayList<>());
        assertThatThrownBy(() -> new BlackjackParticipantsManager(players, dealer))
                .isInstanceOf(BlackJackException.class);
    }
}

