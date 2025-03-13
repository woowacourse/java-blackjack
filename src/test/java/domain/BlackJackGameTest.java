package domain;

import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    void 블랙잭_게임_생성() {
        //given
        List<String> nicknames = List.of("우가","베루스");

        //when
        BlackJackGame blackJackGame = new BlackJackGame(nicknames);

        //then
        Assertions.assertThat(blackJackGame).isInstanceOf(BlackJackGame.class);
    }

    @Test
    void 참가자_등록_확인() {
        //given
        List<String> nicknames = List.of("우가","베루스");

        BlackJackGame blackJackGame = new BlackJackGame(nicknames);

        Participants participants = blackJackGame.getParticipants();
        List<Participant> originParticipants = participants.getParticipants();
        //when & then
        Assertions.assertThat(originParticipants.getFirst().getNickname()).isEqualTo("딜러");
        Assertions.assertThat(originParticipants.get(1).getNickname()).isEqualTo("우가");
        Assertions.assertThat(originParticipants.getLast().getNickname()).isEqualTo("베루스");
    }

}