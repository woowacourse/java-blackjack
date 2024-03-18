package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.fixture.CardFixture;
import blackjack.fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DealerTest {

    @Test
    @DisplayName("기본 이름(딜러)을 가진 딜러를 생성 한다.")
    public void Dealer_Instance_create_with_default_name_and_cards() {
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));

        assertThatCode(() -> {
                    var sut = Dealer.createDefaultDealer(cards);
                    assertThat(sut.getName()
                                  .getValue()).isEqualTo("딜러");
                }
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러는 점수의 합이 16이하면 카드를 받아야 한다.")
    public void Dealer_Can_receive_additional_card() {
        var sut = PlayerFixture.딜러_생성(List.of(CardValue.EIGHT, CardValue.FOUR));

        assertThat(sut.isReceivable()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"JACK, KING", "ACE, KING"})
    @DisplayName("딜러는 점수의 합이 17이상이면 카드를 받아야 한다.")
    public void Dealer_Can_not_receive_additional_card(CardValue value1, CardValue value2) {
        var sut = PlayerFixture.딜러_생성(List.of(value1, value2));

        assertThat(sut.isReceivable()).isFalse();
    }
}
