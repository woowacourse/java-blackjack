package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    CardDeck mustPickTen = CardDeck.of(cards -> Card.createOpenedCard(Rank.TEN, Suit.CLOVER));
    CardDeck mustPickAce = CardDeck.of(cards -> Card.createOpenedCard(Rank.ACE, Suit.CLOVER));
    CardDeck mustPickFive = CardDeck.of(cards -> Card.createOpenedCard(Rank.FIVE, Suit.CLOVER));

    Players players = Players.of(
            List.of("p1", "p2", "p3"),
            name -> Player.of(name, 1000)
    );

    @Test
    @DisplayName("입력받은 이름들에 대해 function을 수행해 Player로 변환하여 Players를 반환한다.")
    void of() {
        //given
        List<String> names = List.of(
                "p1",
                "p2",
                "p3"
        );
        Function<String, Player> function = name -> Player.of(name, 1000);

        //when
        Players players = Players.of(names, function);

        //then
        assertThat(players.getNames())
                .containsExactlyElementsOf(names);

        players.perform(player ->
                assertThat(player.getPrize())
                        .isEqualTo(1000)
        );
    }

    @Test
    @DisplayName("플레이어 이름이 공백으로 시작하거나 끝나면 예외가 발생한다.")
    void of_space_in_name() {
        //given
        List<String> names = List.of(
                " player",
                "player ",
                " player "
        );
        Function<String, Player> function = name -> Player.of(name, 1000);

        // when & then
        assertThatThrownBy(() -> Players.of(names, function))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름이 공백으로 시작하거나 끝납니다.");
    }

    @Test
    @DisplayName("중복 이름이 존재하면 예외가 발생한다.")
    void of_duplicated_names() {
        // given
        List<String> names = List.of(
                "pobi",
                "jun",
                "pobi"
        );
        Function<String, Player> function = name -> Player.of(name, 1000);

        // when & then
        assertThatThrownBy(() -> Players.of(names, function))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 플레이어 이름이 존재합니다.");
    }


    @Test
    @DisplayName("각 플레이어들을 카드덱에서 카드를 2 장씩 뽑는다.")
    void pickInitialCards() {
        //when
        players.pickInitialCards(mustPickTen);

        // then
        players.perform(player ->
                assertThat(player.getAllCard().size())
                        .isEqualTo(2));
    }

    @Test
    @DisplayName("블랙잭인 플레이어들은 최종 수익을 배당 금액의 1.5배 처리한다.")
    void applyBlackjack_blackjack_player() {
        //given
        players.perform(player -> player.pickAdditionalCard(mustPickAce));
        players.perform(player -> player.pickAdditionalCard(mustPickTen));

        //when
        players = players.applyBlackjack();

        //then
        players.perform(player ->
                assertThat(player.getPrize())
                        .isEqualTo(1500)
        );
    }

    @Test
    @DisplayName("블랙잭이 아닌 플레이어들은 최종 수익은 배당 금액과 같다.")
    void _applyBlackjack_not_blackjack_player() {
        //given
       players.pickInitialCards(mustPickTen);

        //when
        players = players.applyBlackjack();

        //then
        players.perform(player ->
                assertThat(player.getPrize())
                        .isEqualTo(1000)
        );
    }

    @Test
    @DisplayName("버스트인 플레이어들은 최종 수익을 -1 * 배당금액으로 처리한다.")
    void applyBust_lose_players() {
        //given
        players.pickInitialCards(mustPickTen);
        players.perform(player -> player.pickAdditionalCard(mustPickTen));

        //when
        players = players.applyBust();

        //then
        players.perform(player ->
                assertThat(player.getPrize()).
                        isEqualTo(-1000)
        );
    }

    @Test
    @DisplayName("버스트이 아닌 플레이어들은 최종 수익을 배당금액으로 처리한다.")
    void applyBust_not_lose_players() {
        //given
        players.pickInitialCards(mustPickTen);

        //when
        players = players.applyBust();

        //then
        players.perform(player ->
                assertThat(player.getPrize()).
                        isEqualTo(1000)
        );
    }

    @Test
    @DisplayName("딜러가 버스트이면, 버스트가 아닌 플레이어의 최종 수익은 배팅 금액으로 처리한다.")
    void award_prize_when_dealer_lose() {
        //given
        Dealer dealer =  new Dealer();
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickTen); // 10 + 10 + 10 = 30

        //when
        players = players.award(dealer);

        //then
        players.perform(player ->
                assertThat(player.getPrize())
                        .isEqualTo(1000)
        );
    }


    @Test
    @DisplayName("딜러와 플레이어가 버스트가 아니고 플레이어 점수가 딜러 점수 이상이면(단 플레이어는 블랙잭이 아니다), 최종 수익을 배팅 금액으로 처리한다.")
    void award_prize_when_dealer_player_are_not_lose_win() {
        //given
        players.perform(winner -> {
            winner.pickAdditionalCard(mustPickTen);
            winner.pickAdditionalCard(mustPickFive);
            winner.pickAdditionalCard(mustPickAce);   //10 + 5 + 1 = 16점
        });

        Dealer dealer =  new Dealer();
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickFive); // 10 + 5 = 15점

        //when
        players = players.award(dealer);

        //then
        players.perform(winner ->
                assertThat(winner.getPrize())
                        .isEqualTo(1000)
        );
    }

    @Test
    @DisplayName("딜러와 플레이어가 버스트가 아니고 플레이어 점수가 딜러 점수 미만이면, 최종 수익을 -1 * 배당 금액으로 처리한다.")
    void award_prize_when_dealer_player_are_not_lose_lose() {
        //given
        players.perform(loser -> {
            loser.pickAdditionalCard(mustPickFive);
            loser.pickAdditionalCard(mustPickFive); // 5 + 5 = 10점
        });

        Dealer dealer =  new Dealer();
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickFive); // 10 + 5 = 15점

        //when
        players = players.award(dealer);

        //then
        players.perform(loser ->
                assertThat(loser.getPrize())
                        .isEqualTo(-1000)
        );
    }

    @Test
    @DisplayName("각 플레이어의 최종 수익의 합에 -1을 곱해서 반환한다.")
    void getDealerProfit() {
        assertThat(players.getDealerProfit())
                .isEqualTo(-3000);
    }
}