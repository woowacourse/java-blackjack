package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    
    @DisplayName("초기 분배 시 딜러와 모든 플레이어에게 카드가 정상적으로 돌아간다.")
    @Test
    void distributeCardsSuccessfully() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));
        Dealer dealer = new Dealer();
        Participants participants = new Participants(players, dealer);
        Deck deck = new Deck();
        
        participants.distributeCards(deck);
        
        assertThat(participants.getPlayersStatus()).size().isEqualTo(2);
    }
    
    @DisplayName("덱에 남은 카드가 부족할 때 초기 분배를 시도하면 예외가 발생한다.")
    @Test
    void distributeCardsThrowsExceptionOnShortDeck() {
        Players players = Players.makePlayers(List.of(
                "pobi", "jason", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"
                , "a10", "a11", "a12", "a13", "a14", "a15", "a16", "a17", "a1a", "a1s", "a1d", "a1f", "a1g", "a1h",
                "a1j", "a1k", "a1l", "a1q"));
        Participants participants = new Participants(players, new Dealer());
        Deck shortDeck = new Deck();
        
        assertThatThrownBy(() -> participants.distributeCards(shortDeck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("남은 카드가 없습니다.");
    }
    
    @DisplayName("딜러가 카드를 더 받아야 하는 상황인지 정확히 판단한다.")
    @Test
    void isDealerDrawWorksCorrectly() {
        Players players = Players.makePlayers(List.of("pobi"));
        Dealer dealer = new Dealer();
        dealer.receiveCard(List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.SIX, Suit.HEART)
        ));
        Participants participants = new Participants(players, dealer);
        
        assertThat(participants.isDealerDraw()).isTrue();
    }
}
