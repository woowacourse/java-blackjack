package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    private List<Player> players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(cards -> 0);
        players = Arrays.asList(
                new Player("suri", cards -> 0),
                new Player("roki", cards -> 0)
        );
    }

    @DisplayName("Participants를 생성한다.")
    @Test
    void test_instance_participants() {
        //when
        Participants participants = new Participants(players, dealer);

        //then
        assertThat(participants).isNotNull();
    }

    @DisplayName("Participants에 딜러가 존재하지 않으면 예외를 발생시킨다")
    @ParameterizedTest
    @NullSource
    void test_extract_dealer_if_dealer_is_not_exist(Dealer dealer) {
        //when //then
        assertThatThrownBy(() -> new Participants(players, dealer))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Participants에 딜러가 존재하지 않으면 예외를 발생시킨다")
    @ParameterizedTest
    @NullSource
    @EmptySource
    void test_extract_dealer_if_dealer_is_not_exist(List<Player> players) {
        //when //then
        assertThatThrownBy(() -> new Participants(players, dealer))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("딜러를 뽑아낸다.")
    @Test
    void test_extract_dealer() {
        //given
        Participants participants = new Participants(players, dealer);

        //when
        Dealer dealer = participants.extractDealer();

        //then
        assertThat(dealer).isNotNull();
    }

    @DisplayName("플레이어들를 뽑아낸다")
    @Test
    void test_extract_players() {
        //given
        Participants participants = new Participants(players, dealer);

        //when
        List<Player> players = participants.extractPlayers();

        //then
        assertThat(players).hasSize(2);
    }

    @DisplayName("모든 참가자들에게 패를 돌린다")
    @Test
    void test_deal_cards_all_participants() {
        //given
        Participants participants = new Participants(players, dealer);
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
