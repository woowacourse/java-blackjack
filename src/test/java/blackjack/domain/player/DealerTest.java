package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.player.info.Name;
import blackjack.fixture.CardFixture;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("기본 이름(딜러)을 가진 딜러를 생성 한다.")
    void create_with_default_name_and_cards() {
        assertThatCode(() -> {
                    var sut = Dealer.createDefaultNameDealer();
                    assertThat(sut.getNameAsString()).isEqualTo("딜러");
                }
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러는 숫자의 합이 16이하면 카드를 받아야 한다.")
    void can_receive_additional_card() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        var sut = new Dealer(name, cards);

        assertTrue(sut.isReceivable());
    }

    @Test
    @DisplayName("딜러는 숫자의 합이 17이상이면 카드를 받아야 한다.")
    void can_not_receive_additional_card() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.JACK, CardValue.KING));
        var sut = new Dealer(name, cards);

        assertFalse(sut.isReceivable());
    }
}
