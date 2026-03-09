package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {
    @DisplayName("딜러가 받은 카드의 점수 합이 16 미만이면 추가로 받는다.")
    @Test
    void 딜러_카드_16미만_추가_배부_필요() {
        Dealer dealer = createDealerFromCards(List.of(
                new Card(CardShape.HEART, CardRank.NINE),
                new Card(CardShape.HEART, CardRank.SIX)
        ));

        assertThat(dealer.needAdditionalCard()).isEqualTo(true);
    }

    @DisplayName("딜러가 받은 카드의 점수 합이 16이면 추가로 받는다.")
    @Test
    void 딜러_카드_16이면_추가_배부_필요() {
        Dealer dealer = createDealerFromCards(List.of(
                new Card(CardShape.HEART, CardRank.QUEEN),
                new Card(CardShape.HEART, CardRank.SIX)
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

    @DisplayName("딜러가 블랙잭 패를 받으면 패를 더 받지 않는다.")
    @Test
    void 딜러가_블랙잭이면_추가패_X() {
        Dealer dealer = createDealerFromCards(List.of(
                new Card(CardShape.HEART, CardRank.TEN),
                new Card(CardShape.HEART, CardRank.ACE)
        ));

        assertThat(dealer.needAdditionalCard()).isEqualTo(false);
    }

    private Dealer createDealerFromCards(List<Card> cards) {
        Dealer dealer = new Dealer("aaaa");
        for (Card card : cards) {
            dealer.add(card);
        }
        return dealer;
    }

}