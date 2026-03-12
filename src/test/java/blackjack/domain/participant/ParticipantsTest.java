package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    
    @Test
    @DisplayName("카드 분배 시 딜러와 모든 플레이어에게 정확히 2장씩 지급한다.")
    void distributeCards_GiveTwoCardsToAllParticipants() {
        List<String> names = List.of("pobi", "jason");
        List<Integer> bettingAmounts = List.of(10000, 20000);
        Players players = Players.fromNameAndBettingAmounts(names, bettingAmounts);
        
        Dealer dealer = new Dealer();
        Participants participants = new Participants(players, dealer);
        Deck deck = new Deck();
        
        participants.distributeCards(deck);
        
        assertThat(dealer.getCards()).hasSize(2);
        for (Player player : players.getPlayers()) {
            assertThat(player.getCards()).hasSize(2);
        }
    }
    
    @Test
    @DisplayName("전체 참가자 목록 조회 시 딜러가 첫 번째에 위치하고 플레이어들이 뒤따른다.")
    void getParticipants_ReturnDealerAndPlayersInOrder() {
        List<String> names = List.of("pobi", "jason");
        List<Integer> bettingAmounts = List.of(10000, 20000);
        Players players = Players.fromNameAndBettingAmounts(names, bettingAmounts);
        
        Dealer dealer = new Dealer();
        Participants participants = new Participants(players, dealer);
        
        List<Participant> allParticipants = participants.getParticipants();
        
        assertThat(allParticipants).hasSize(3);
        assertThat(allParticipants.get(0)).isInstanceOf(Dealer.class);
        assertThat(allParticipants.get(1).getNickname()).isEqualTo("pobi");
        assertThat(allParticipants.get(2).getNickname()).isEqualTo("jason");
    }
}
