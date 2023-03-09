package domain;

import domain.Card.Card;
import domain.Card.CardNumber;
import domain.Card.CardShape;
import domain.user.ParticipantStatus;
import domain.user.Participants;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    
    @DisplayName("참자자들 생성 테스트")
    @Test
    void create() {
        Participants participants = Participants.of("echo,split");
        Assertions.assertThat(participants.getPlayers())
                .extracting("name")
                .containsExactly("echo", "split");
        Assertions.assertThat(participants.getDealer())
                .extracting("name")
                .isEqualTo("딜러");
    }
    
    @DisplayName("참가자의 게임 상태 업데이트 기능 구현")
    @Test
    void updateTest() {
        Participants players = Participants.of("echo,split");
        Player player = players.getPlayers().get(0);
        player.addCard(new Card(CardNumber.KING, CardShape.SPADE));
        Assertions.assertThat(player.getStatus()).isEqualTo(ParticipantStatus.NOT_BUST);
        player.addCard(new Card(CardNumber.ACE, CardShape.HEART));
        Assertions.assertThat(player.getStatus()).isEqualTo(ParticipantStatus.BLACKJACK);
        player.addCard(new Card(CardNumber.KING, CardShape.DIAMOND));
        player.addCard(new Card(CardNumber.KING, CardShape.DIAMOND));
        Assertions.assertThat(player.getStatus()).isEqualTo(ParticipantStatus.BUST);
        
    }
}
