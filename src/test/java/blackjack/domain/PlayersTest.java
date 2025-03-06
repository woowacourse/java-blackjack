package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PlayersTest {

    @Test
    @DisplayName("플레이어 목록을 추가한다")
    void 플레이어_목록을_추가한다() {
        Players players = new Players();

        players.addGamblers(List.of(new Player("두리"), new Player("비타")));

        assertThat(players.getGamblers().size()).isEqualTo(2);
    }

    @DisplayName("각 참가자 마다 기본 카드 2장을 발급한다")
    @Test
    void give_two_cards() {
        Players players = new Players();
        CardPack cardPack = new CardPack(new SortShuffle());
        players.addGamblers(List.of(new Player("두리")));

        players.initPlayers(cardPack);
        List<Card> result = players.getGamblers().getFirst().getCards();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("참가자의 목록으로 객체를 생성한다")
    void make_players() {
        assertThatNoException()
                .isThrownBy(Players::new);
    }

    @Test
    @DisplayName("딜러에게 카드 2장을 발급한다")
    void deal_card_to_dealer() {
        Players players = new Players();
        players.initPlayers(new CardPack(new SortShuffle()));

        Player dealer = players.getDealer();

        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어의 카드가 버스트면 TRUE를 반환한다")
    void 플레이어의_카드가_버스트면_TRUE를_반환한다() {
        // given
        Players players = new Players();
        CardPack cardPack = new CardPack(new SortShuffle());

        Player player = new Player("두리");
        players.addGamblers(List.of(player));

        players.dealAddCard(cardPack, player);
        players.dealAddCard(cardPack, player);
        players.dealAddCard(cardPack, player);

        // when
        boolean result = players.isPlayerBust(player);

        // then
        assertThat(result).isTrue();
    }

    private static class SortShuffle implements BlackjackShuffle {

        @Override
        public void shuffle(List<Card> cards) {
            cards.sort(Comparator
                    .comparing(Card::getNumber)
                    .thenComparing(Card::getShape));
        }
    }
}
