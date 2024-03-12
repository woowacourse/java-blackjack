package domain.blackjack;

import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackTest {

    @DisplayName("카드를 두장씩 나눠준다.")
    @Test
    void beginDealing() {
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(participants);
        blackJack.beginDealing(testParticipants -> {});

        Assertions.assertAll(
                () -> assertThat(participants.getDealer().getCardCount()).isEqualTo(2),
                () -> assertThat(participants.getNotDealerParticipant().get(0).getCardCount()).isEqualTo(2),
                () -> assertThat(participants.getNotDealerParticipant().get(1).getCardCount()).isEqualTo(2)
        );
    }

    @Test
    void name() {
        Participants participants = new Participants(List.of("one", "two"));
        BlackJack blackJack = new BlackJack(participants);

        Function<Name, String> testFunction = new Function<Name, String>() {
            int i = 0;
            @Override
            public String apply(Name name) {
                if (i % 3 != 2) {
                    i++;
                    return "y";
                }
                return "n";
            }
        };

        blackJack.play(testFunction, Participant::getName);

        List<Participant> notDealerParticipants = participants.getNotDealerParticipant();

        assertThat(notDealerParticipants.get(0).getCardCount()).isEqualTo(2);
    }
}
