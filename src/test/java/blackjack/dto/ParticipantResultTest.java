package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Amount;
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
        Player player = new Player(Nickname.from("boye"), Role.PLAYER, Amount.from("100000"));
        Hand hand = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE)
        ));
        player.receiveCard(hand.getCards());

        // when
        ParticipantResult participantResult = ParticipantResult.from(player);

        // then
        assertAll(
            () -> assertThat(participantResult.nickname()).isEqualTo("boye"),
            () -> assertThat(participantResult.cardStatus()).containsExactly("10스페이드"),
            () -> assertThat(participantResult.totalScore()).isEqualTo(10)
        );
    }
}
