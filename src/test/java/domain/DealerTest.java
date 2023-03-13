package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DealerTest {

    @Test
    @DisplayName("초기 카드 두장을 파라미터로 입력하여 딜러 객체가 정상적으로 생성된다")
    void generateDealer() {
        Name name = Name.generateDealerName();
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.JACK), new Card(CardType.HEART, CardValue.EIGHT)));

        assertDoesNotThrow(() -> new Dealer(name, cards));
    }

    @Test
    @DisplayName("딜러 카드의 합이 16이하일 경우 true를 리턴한다")
    void checkToGetOneMoreCard() {
        Name name = Name.generateDealerName();
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.SEVEN), new Card(CardType.HEART, CardValue.EIGHT)));

        Dealer dealer = new Dealer(name, cards);

        assertThat(dealer.canReceiveOneMoreCard()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드의 합이 16을 초과할 경우 false를 리턴한다")
    void checkNotToGetOneMoreCard() {
        Name name = Name.generateDealerName();
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.TEN), new Card(CardType.HEART, CardValue.EIGHT)));

        Dealer dealer = new Dealer(name, cards);

        assertThat(dealer.canReceiveOneMoreCard()).isFalse();
    }

    @Test
    @DisplayName("기존의 카드 그룹에 새로운 카드를 추가하면 카드의 개수가 1만큼 증가한다")
    void pickCard() {
        Name name = Name.generateDealerName();
        Cards cards = new Cards(new ArrayList<>(List.of(new Card(CardType.SPADE, CardValue.SEVEN), new Card(CardType.HEART, CardValue.EIGHT))));
        Dealer dealer = new Dealer(name, cards);
        CardDeck cardDeck = CardDeck.generateCardDeck();
        int expectedCardSize = 3;

        dealer.pickCard(cardDeck);

        assertThat(dealer.getCards().size()).isEqualTo(expectedCardSize);
    }
}



