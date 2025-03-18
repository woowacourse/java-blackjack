package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPack;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;
import blackjack.domain.player.BetAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    @DisplayName("플레이어 목록을 추가한다")
    void player_List() {
        Players players = new Players(
                new Dealer(new Cards()),
                List.of(new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards()),
                        new Gambler(new PlayerName("비타"), new BetAmount(0), new Cards())));

        assertThat(players.getGamblers().size()).isEqualTo(2);
    }

    @DisplayName("각 참가자 마다 기본 카드 2장을 발급한다")
    @Test
    void give_two_cards() {
        Players players = new Players(
                new Dealer(new Cards()),
                List.of(new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards())));

        players.dealInitCardsToPlayers(CardPack.createShuffled());
        List<Card> result = players.getGamblers().getFirst().getCards();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러에게 카드 2장을 발급한다")
    void deal_card_to_dealer() {
        Players players = new Players(
                new Dealer(new Cards()),
                List.of());
        players.dealInitCardsToPlayers(CardPack.createShuffled());

        Player dealer = players.getDealer();

        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어의 카드가 버스트면 TRUE 를 반환한다")
    void if_the_players_card_is_bust_it_returns_true() {
        // given
        Gambler gambler = new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards());

        gambler.pushDealCards(
                List.of(
                        new Card(CardNumber.JACK, CardShape.DIAMOND),
                        new Card(CardNumber.QUEEN, CardShape.SPADE),
                        new Card(CardNumber.TWO, CardShape.HEART)
                )
        );

        // when
        boolean result = gambler.isPlayerBust();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드가 16이하인 경우 TRUE 를 반환한다")
    void the_dealers_card_is_less_than_16_or_less() {
        // given
        Players players = new Players(
                new Dealer(new Cards()),
                List.of());

        // when
        boolean result = players.getDealer().isDealerHit();

        // then
        assertThat(result)
                .isTrue();
    }

    @Test
    @DisplayName("플레이어의 이름은 중복 될 수 없다")
    void the_player_s_name_is_duplicate___no() {
        assertThatThrownBy(() ->
                new Players(
                        new Dealer(new Cards()),
                        List.of(
                                new Gambler(new PlayerName("비타"), new BetAmount(0), new Cards()),
                                new Gambler(new PlayerName("비타"), new BetAmount(0), new Cards()), new Gambler(new PlayerName("비타"), new BetAmount(0), new Cards())))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어의 이름은 길이가 공백이면 예외를 던진다")
    void 플레이어의_이름은_길이가_공백이면_예외를_던진다() {
        assertThatThrownBy(() ->
                new Players(
                        new Dealer(new Cards()),
                        List.of(new Gambler(new PlayerName(""), new BetAmount(0), new Cards())))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
