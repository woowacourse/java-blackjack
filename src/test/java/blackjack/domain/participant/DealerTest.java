package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class DealerTest {
    
    @Test
    @DisplayName("카드 합이 조건값 이하라면 뽑아야 한다")
    void shouldReceive_LessThanOrEqualToThreshold_ShouldReceive() {
        
        // given
        Dealer dealer = Dealer.create();
        Card firstCard = new Card(Suit.CLOVER, Rank.TEN);
        Card secondCard = new Card(Suit.CLOVER, Rank.SIX);
        
        dealer.receive(firstCard);
        dealer.receive(secondCard);
        
        // when
        boolean shouldReceive = dealer.shouldReceive();
        
        // then
        assertThat(shouldReceive).isTrue();
    }
    
    @Test
    @DisplayName("카드 합이 조건값보다 크다면 뽑지 않아야 한다")
    void shouldReceive_LessThanThreshold_ShouldNotReceive() {
        
        // given
        Dealer dealer = Dealer.create();
        Card firstCard = new Card(Suit.CLOVER, Rank.TEN);
        Card secondCard = new Card(Suit.CLOVER, Rank.SEVEN);
        
        dealer.receive(firstCard);
        dealer.receive(secondCard);
        
        // when
        boolean shouldReceive = dealer.shouldReceive();
        
        // then
        assertThat(shouldReceive).isFalse();
    }
    
    @Test
    @DisplayName("게임 참가자에게 카드 나눠주기 테스트")
    void deal() {
        
        // given
        Dealer dealer = Dealer.create();
        Player player = Player.from("pobi");
        
        // when
        dealer.deal(player);
        
        // then
        assertThat(player.getCards()).hasSize(1);
    }
    
    @Test
    @DisplayName("참가자가 최고 합인 상황에서 카드를 더 받으려고 하면 예외 발생")
    void deal_IsBurstParticipant_ExceptionThrown() {
        
        // given
        Dealer dealer = Dealer.create();
        Player player = Player.from("pobi");
        player.receive(new Card(Suit.DIAMOND, Rank.TEN));
        player.receive(new Card(Suit.DIAMOND, Rank.JACK));
        player.receive(new Card(Suit.DIAMOND, Rank.ACE));
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> dealer.deal(player);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("최고 합이거나 버스트되어 카드를 받을 수 없습니다.");
    }
}
