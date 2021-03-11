package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Deck;
import blackjack.domain.state.Hit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    private List<Participant> participantGroup;
    private List<Card> defaultInitialCard;

    @BeforeEach
    void setUp() {
        defaultInitialCard = new ArrayList<>();
        defaultInitialCard.add(new Card(CardType.DIAMOND, CardValue.TEN));
        defaultInitialCard.add(new Card(CardType.DIAMOND, CardValue.TWO));

        participantGroup = Arrays.asList(
                new Dealer(new Hit(defaultInitialCard)),
                new Player("suri", 10000, new Hit(defaultInitialCard)),
                new Player("roki", 10000, new Hit(defaultInitialCard
                ))
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
                new Player("suri", 10000, new Hit(defaultInitialCard)),
                new Player("roki", 10000, new Hit(defaultInitialCard))
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

    @DisplayName("모든 참가자들은 초기 패 2장을 가진다")
    @Test
    void test_deal_cards_all_participants() {
        //given
        List<Participant> participantGroup = Arrays.asList(new Dealer(new Hit(defaultInitialCard)),
                new Player("pobi", 10000, new Hit(defaultInitialCard)),
                new Player("jason", 10000, new Hit(defaultInitialCard)));

        Participants participants = new Participants(participantGroup);

        //then
        List<Participant> actualParticipants = participants.getParticipants();
        assertThat(actualParticipants.get(0).showCards()).hasSize(2);
        assertThat(actualParticipants.get(1).showCards()).hasSize(2);
        assertThat(actualParticipants.get(2).showCards()).hasSize(2);
    }
}
