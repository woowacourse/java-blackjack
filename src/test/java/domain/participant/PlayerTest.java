package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.assertj.core.api.InstanceOfAssertFactories.type;

class PlayerTest {

    @Test
    @DisplayName("create()를 호출하면, Player가 생성된다")
    void create_whenCall_thenSuccess() {
        // given
        final String name = "pobi";

        // when, then
        assertThatCode(() -> Player.create(name))
                .doesNotThrowAnyException();

        assertThat(Player.create(name))
                .isExactlyInstanceOf(Player.class);
    }

    @Test
    @DisplayName("isDealerName()은 파라미터로 입력된 name이 '딜러'인지 판단한다")
    void create_givenInvalidName_thenFail() {
        assertThatThrownBy(() -> Player.create("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_thenSuccess() {
        // given
        final String playerName = "pobi";
        final Card card = Card.create(CardPattern.HEART, CardNumber.ACE);
        final Player player = Player.create(playerName);

        // when
        player.addCard(card);

        // then
        assertThat(player)
                .extracting("participantCard", type(ParticipantCard.class))
                .extracting("cards", as(list(Card.class)))
                .hasSize(1);
    }
}
