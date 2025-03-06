package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        // given
        Players players = new Players(List.of(new Player("두리")));
        CardPack cardPack = new CardPack(new SortShuffle());

        // when
        players.initPlayers(cardPack);
        List<Card> result = players.getGamblers().getFirst().getCards();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("참가자의 목록으로 객체를 생성한다")
    void make_players() {
        //given
        Player player1 = new Player("두리");
        Player player2 = new Player("비타");
        Players players = new Players(List.of(
                player1,
                player2
        ));


        //when & then
        assertThat(players.getGamblers()).contains(player1, player2);
    }

    @Test
    @DisplayName("딜러에게 카드 2장을 발급한다")
    void deal_card_to_dealer() {
        Players players = new Players(List.of(new Player("비타")));
        Player dealer = players.getDealer();
        players.initPlayers(new CardPack(new SortShuffle()));
        Assertions.assertThat(dealer.getCards().size()).isEqualTo(2);
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
