package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        Dealer dealer = new Dealer();
        this.dealer = dealer;
    }

    @DisplayName("딜러가 받은 카드의 점수 합이 16이하이면 추가로 받는다.")
    @Test
    void 딜러_카드_추가_배부_필요_정상_테스트() {
        Dealer dealer = createDealerFromCards(List.of(
                new Card(CardShape.HEART, CardRank.THREE),
                new Card(CardShape.HEART, CardRank.TWO)
        ));

        assertThat(dealer.needAdditionalCard()).isEqualTo(true);
    }

    @DisplayName("딜러가 받은 카드의 점수 합이 17 이상이면 추가로 받지 않는다.")
    @Test
    void 딜러_카드_추가_배부_불필요_정상_테스트() {
        Dealer dealer = createDealerFromCards(List.of(
                new Card(CardShape.HEART, CardRank.TEN),
                new Card(CardShape.HEART, CardRank.SEVEN)
        ));

        assertThat(dealer.needAdditionalCard()).isEqualTo(false);
    }

    private Dealer createDealerFromCards(List<Card> cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.add(card);
        }
        return dealer;
    }

}