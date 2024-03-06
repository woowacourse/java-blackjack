package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("이름과 카드 일급 컬렉션을 통해서 딜러를 생성 한다.")
    public void Dealer_Instance_create_with_name_and_cards() {
        Name name = new Name("딜러");
        Cards cards = 카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));

        assertThatCode(() -> new Dealer(name, cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("기본 이름(딜러)을 가진 딜러를 생성 한다.")
    public void Dealer_Instance_create_with_default_name_and_cards() {
        Cards cards = 카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));

        assertThatCode(() -> {
                    var sut = Dealer.createDefaultDealer(cards);
                    assertThat(sut.getName()).isEqualTo("딜러");
                }
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러는 숫자의 합이 16이하면 카드를 받아야 한다.")
    public void Dealer_Can_receive_additional_card() {
        Name name = new Name("딜러");
        Cards cards = 카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        var sut = new Dealer(name, cards);

        assertTrue(sut.isReceivable());
    }

    @Test
    @DisplayName("딜러는 숫자의 합이 16이하면 카드를 받아야 한다.")
    public void Dealer_Can_not_receive_additional_card() {
        Name name = new Name("딜러");
        Cards cards = 카드_목록_생성(List.of(CardValue.JACK, CardValue.FOUR));
        var sut = new Dealer(name, cards);

        assertTrue(sut.isReceivable());
    }

    private Cards 카드_목록_생성(List<CardValue> cardValues) {
        return new Cards(cardValues.stream()
                                   .map(cardValue -> new Card(cardValue, CardSymbol.DIAMOND))
                                   .toList());
    }
}
