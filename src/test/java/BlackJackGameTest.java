import game.BlackJackGame;
import participant.Participant;
import participant.Participants;
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

    @Test
    void 한장_나눠주기() {
        //given
        List<String> nicknames = List.of("우가","베루스");

        BlackJackGame blackJackGame = new BlackJackGame(nicknames);
        Participants participants = blackJackGame.getParticipants();
        Participant participant = participants.getParticipants().getLast();

        //when
        blackJackGame.drawOneCards(participant);

        //then
        Assertions.assertThat(participant.getCardDeck().getCards().size()).isEqualTo(1);
    }

    @Test
    void 두장_나눠주기() {
        //given
        List<String> nicknames = List.of("우가","베루스");

        BlackJackGame blackJackGame = new BlackJackGame(nicknames);

        //when
        blackJackGame.drawTwoCards();

        //then
        Assertions.assertThat(blackJackGame.getParticipants().getParticipants().getFirst().getCardDeck().getCards().size()).isEqualTo(2);
    }

}