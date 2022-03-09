package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class DealerTest {
    
    @Test
    void 딜러가_이기는_경우_판별_테스트() {
        Dealer dealer = new Dealer(new Cards("8다이아몬드", "J하트"));
        assertThat(dealer.judge(new Cards("7클로버", "8하트"))).isEqualTo(Result.WIN);
    }
}
