package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    private List<Participant> participantGroup;


    @BeforeEach
    void setUp() {
        participantGroup = Arrays.asList(
                new Dealer(cards -> 0),
                new Player("suri", cards -> 0),
                new Player("roki", cards -> 0)
        );
    }

    @DisplayName("Participants를 생성한다.")
    @Test
    void test_instance_participants() {
        //when
        Participants participants = new Participants(participantGroup);

        //then
        assertThat(participants).isNotNull();
    }

    @DisplayName("딜러를 뽑아낸다.")
    @Test
    void test_extract_dealer() {
        //given
        Participants participants = new Participants(participantGroup);

        //when
        Dealer dealer = participants.extractDealer();

        //then
        assertThat(dealer).isNotNull();
    }

    @DisplayName("딜러가 없는 Participants에서 딜러를 뽑아내면 예외를 발생시킨다")
    @Test
    void test_extract_dealer_if_dealer_is_not_exist() {
        //given
        List<Participant> participantGroup = Arrays.asList(
                new Player("suri", cards -> 0),
                new Player("roki", cards -> 0)
        );
        Participants participants = new Participants(participantGroup);

        //when //then
        assertThatThrownBy(() -> participants.extractDealer())
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어들를 뽑아낸다")
    @Test
    void test_extract_players() {
        //given
        Participants participants = new Participants(participantGroup);

        //when
        List<Player> players = participants.extractPlayers();

        //then
        assertThat(players).hasSize(2);
    }

    @DisplayName("모든 참가자들에게 패를 돌린다")
    @Test
    void test_deal_cards_all_participants() {
        //given
        List<Participant> participantGroup = Arrays.asList(new Dealer(cards -> 16),
                new Player("pobi", cards -> 18),
                new Player("jason", cards -> 15));

        Participants participants = new Participants(participantGroup);
        Deck deck = Deck.generate();

        //when
        participants.dealCardsAllParticipants(deck);

        //then
        List<Participant> actualParticipants = participants.getParticipants();
        assertThat(actualParticipants.get(0).showCards()).hasSize(2);
        assertThat(actualParticipants.get(1).showCards()).hasSize(2);
        assertThat(actualParticipants.get(2).showCards()).hasSize(2);
    }
}
