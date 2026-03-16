package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Card;
import blackjack.domain.Hand;
import blackjack.domain.Nickname;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Role;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantResultTest {

    @Test
    @DisplayName("참가자 결과 dto 생성을 확인한다.")
    void makeParticipantResult() {
        // given
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, 100000);
        Hand hand = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE)
        ));
        player.receiveCard(hand.getCards());

        // when
        ParticipantResult participantResult = ParticipantResult.from(player);

        // then
        assertAll(
            () -> assertThat(participantResult.nickname()).isEqualTo("boye"),
            () -> assertThat(participantResult.cardStatus()).isEqualTo("10스페이드"),
            () -> assertThat(participantResult.totalScore()).isEqualTo(10)
        );
    }

    @Test
    @DisplayName("카드 결과 string 생성을 확인한다.")
    void makeParticipantResultToString() {
        // given
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, 100000);
        Hand hand = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE)
        ));
        player.receiveCard(hand.getCards());
        ParticipantResult participantResult = ParticipantResult.from(player);

        // when
        String participantResultString = participantResult.toString();

        // then
        assertThat(participantResultString).isEqualTo("boye카드: 10스페이드");
    }

    @Test
    @DisplayName("결과값을 포함한 카드 결과 string 생성을 확인한다.")
    void participantResultFullString() {
        // given
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, 100000);
        Hand hand = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE)
        ));
        player.receiveCard(hand.getCards());
        ParticipantResult participantResult = ParticipantResult.from(player);

        // when
        String participantResultString = participantResult.toFullString();

        // then
        assertThat(participantResultString).isEqualTo("boye카드: 10스페이드 - 결과: 10");
    }
}
