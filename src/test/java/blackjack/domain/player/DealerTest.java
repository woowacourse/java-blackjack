package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.fixture.CardFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("기본 이름(딜러)을 가진 딜러를 생성 한다.")
    public void Dealer_Instance_create_with_default_name_and_cards() {
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));

        assertThatCode(() -> {
                    var sut = Dealer.createDefaultDealer(cards);
                    assertThat(sut.getName()
                                  .asString()).isEqualTo("딜러");
                }
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러는 숫자의 합이 16이하면 카드를 받아야 한다.")
    public void Dealer_Can_receive_additional_card() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        var sut = new Dealer(name, cards);

        assertThat(sut.isReceivable()).isTrue();
    }

    @Test
    @DisplayName("딜러는 숫자의 합이 17이상이면 카드를 받아야 한다.")
    public void Dealer_Can_not_receive_additional_card() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.JACK, CardValue.KING));
        var sut = new Dealer(name, cards);

        assertThat(sut.isReceivable()).isFalse();
    }

    @Test
    public void test() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.ACE, CardValue.KING));
        var sut = new Dealer(name, cards);
        assertThat(sut.isReceivable()).isFalse();
    }
}
