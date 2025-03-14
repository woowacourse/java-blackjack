package domain;

import domain.card.GameCardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    void 카드_두장_드로우() {
        //given
        List<Participant> originParticipants = List.of(new Dealer(), new Player("우가"), new Player("베루스"));

        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        Participants participants = new Participants(originParticipants);

        //when
        participants.drawTwoCards(gameCardDeck);

        //then
        for (Participant participant : participants.getParticipants()) {
            Assertions.assertThat(participant.getCardDeck().getCards().size()).isEqualTo(2);
        }

    }

    @Test
    void 딜러_찾기_성공() {
        //given
        List<Participant> originParticipants = List.of(new Dealer(), new Player("우가"), new Player("베루스"));

        Participants participants = new Participants(originParticipants);

        //when
        Participant dealer = participants.findDealer();

        //then
        Assertions.assertThat(dealer.getNickname()).isEqualTo("딜러");
    }

    @Test
    void 오직_플레이어만_찾기_성공() {
        //given
        List<Participant> originParticipants = List.of(new Dealer(), new Player("우가"), new Player("베루스"));

        Participants participants = new Participants(originParticipants);

        //when
        Participants onlyParticipants = participants.findPlayers();
        for (Participant participant : onlyParticipants.getParticipants()) {
            System.out.println(participant.getNickname());
        }

        //then
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(onlyParticipants.getParticipants().getFirst().getNickname()).isEqualTo("우가"),
                () -> Assertions.assertThat(onlyParticipants.getParticipants().getLast().getNickname()).isEqualTo("베루스")
        );
    }
}