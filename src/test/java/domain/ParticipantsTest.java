package domain;

import static domain.CardInfo.A;
import static domain.CardInfo.TEN;
import static domain.Shape.HEART;
import static domain.fixture.PlayerFixture.빙봉;
import static domain.fixture.PlayerFixture.우가;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/06
 */
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @Test
    void 참가자들의_플레이어_이름들을_반환한다() {
        Dealer dealer = readDealer();
        List<Participant> participantsPlayers = readPlayers();
        participantsPlayers.add(0, dealer);
        Participants participants = new Participants(participantsPlayers);

        assertThat(participants.getPlayerNames()).containsExactly("우가", "빙봉");
    }

    @Test
    void 참가자들이_가진_카드뭉치를_반환한다() {
        Dealer dealer = readDealer();
        List<Participant> participantsPlayers = readPlayers();
        participantsPlayers.add(0, dealer);
        Participants participants = new Participants(participantsPlayers);

        assertThat(participants.getPlayerCards())
                .extracting(Cards::cardsToString)
                .containsExactly(List.of("A하트", "2하트"), List.of("A하트", "10하트"));
    }

    @Test
    void 참가자들중_플레이어를_반환한다() {
        Dealer dealer = readDealer();
        List<Participant> participantsPlayers = readPlayers();
        participantsPlayers.add(0, dealer);
        Participants participants = new Participants(participantsPlayers);

        assertThat(participants.getPlayersToList()).contains(우가, 빙봉);
    }

    @Test
    void 참가자들중_딜러를_반환한다() {
        Dealer dealer = readDealer();
        List<Participant> participantsPlayers = readPlayers();
        participantsPlayers.add(0, dealer);
        Participants participants = new Participants(participantsPlayers);

        assertThat(participants.getDealer()).isEqualTo(dealer);
    }

    @Test
    void 참가자들끼리의_우승결과를_반환한다() {
        Dealer dealer = readDealer();
        List<Participant> participantsPlayers = readPlayers();
        participantsPlayers.add(0, dealer);
        Participants participants = new Participants(participantsPlayers);

        assertThat(participants.getWinningResult()).containsExactly(1, 0);
    }

    Dealer readDealer() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(HEART, A));
        cards.add(new Card(HEART, TEN));

        return new Dealer(new CardBox(), new Cards(cards));
    }

    List<Participant> readPlayers() {
        List<Participant> participants = new ArrayList<>();
        participants.add(우가);
        participants.add(빙봉);
        return participants;
    }
}
