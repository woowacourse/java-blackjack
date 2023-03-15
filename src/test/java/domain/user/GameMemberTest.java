package domain.user;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameMemberTest {
    
    @DisplayName("참자자들 생성 테스트")
    @Test
    void create() {
        GameMember gameMember = GameMember.of(List.of("echo", "split"));
        Assertions.assertThat(gameMember.getPlayers())
                .extracting("name")
                .containsExactly("echo", "split");
        Assertions.assertThat(gameMember.getDealer())
                .extracting("name")
                .isEqualTo("딜러");
    }
    
    
    @DisplayName("참가자의 게임 상태 업데이트 기능 구현")
    @Test
    void updateTest() {
        GameMember gameMember = GameMember.of(List.of("echo", "split"));
        Player player = gameMember.getPlayers().get(0);
        player.addCard(new Card(CardNumber.KING, CardShape.SPADE));
        Assertions.assertThat(player.getStatus()).isEqualTo(MemberStatus.NOT_BUST);
        player.addCard(new Card(CardNumber.ACE, CardShape.HEART));
        Assertions.assertThat(player.getStatus()).isEqualTo(MemberStatus.BLACKJACK);
        player.addCard(new Card(CardNumber.KING, CardShape.DIAMOND));
        player.addCard(new Card(CardNumber.KING, CardShape.DIAMOND));
        Assertions.assertThat(player.getStatus()).isEqualTo(MemberStatus.BUST);
        
    }
}
