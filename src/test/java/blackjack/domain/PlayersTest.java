package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    private static final CardPack CARD_PACK = new CardPack(new ReversedSortShuffle());

    @Test
    @DisplayName("플레이어 목록을 추가한다")
    void player_List() {
        Players players = new Players(
                List.of(new Gambler("두리"), new Gambler("비타")),
                CARD_PACK
        );

        assertThat(players.getGamblers().size())
                .isEqualTo(2);
    }

    @DisplayName("참가자 마다 기본 카드 2장을 발급한다")
    @Test
    void give_two_cards() {
        Players players = new Players(List.of(new Gambler("두리")), CARD_PACK);

        List<Card> result = players
                .getGamblers()
                .getFirst()
                .getCards();

        assertThat(result.size())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("딜러에게 카드 2장을 발급한다")
    void deal_card_to_dealer() {
        Players players = new Players(List.of(new Gambler("두리")), CARD_PACK);

        List<Card> result = players
                .getDealer()
                .getCards();

        assertThat(result.size())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 카드가 16이하인 경우 TRUE 를 반환한다")
    void the_dealers_card_is_less_than_16_or_less() {
        Players players = new Players(List.of(new Gambler("두리")), CARD_PACK);

        boolean result = players.isDealerHit();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어의 이름은 중복 될 수 없다")
    void the_player_s_name_is_duplicate___no() {
        assertThatThrownBy(() -> new Players(List.of(new Gambler("비타"), new Gambler("비타")), CARD_PACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복 될 수 없습니다.");
    }
}
