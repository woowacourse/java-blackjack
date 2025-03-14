package domain;

import domain.card.*;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackTest {
    private final Deck deck = new Deck(new RandomCardsGenerator());

    @DisplayName("플레이어들의 승패를 결정한다")
    @Test
    void test() {
        //given
        Cards dealerCards = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.THREE, CardShape.CLOVER)
                )
        );
        Dealer dealer = new Dealer(dealerCards, deck);

        Cards player1Cards = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.TWO, CardShape.CLOVER)
                )
        );
        Player player1 = Player.from(new Nickname("플레이어1"), player1Cards);

        Cards player2Cards = Cards.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.FOUR, CardShape.CLOVER)
                )
        );
        Player player2 = Player.from(new Nickname("플레이어2"), player2Cards);

        Map<Player, GameResult> expected = Map.of(
                player1, GameResult.LOSE,
                player2, GameResult.WIN
        );

        BlackJack blackJack = new BlackJack(dealer, Players.withoutBetting(Set.of(player1, player2)));

        //when
        Map<Player, GameResult> actual = blackJack.getPlayersResult();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 블랙잭으로 승리한 경우, 수익을 구할 수 있다.")
    @Test
    void blackjackWinRevenue() {
        // given
        final int bettingCost = 10000;

        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.A, CardShape.SPADE),
                new Card(CardNumber.TEN, CardShape.HEART)
        ));
        Player player = Player.from(
                new Nickname("player"),
                playerCards
        );

        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.SEVEN, CardShape.DIAMOND),
                new Card(CardNumber.QUEEN, CardShape.HEART)
        ));
        Dealer dealer = new Dealer(dealerCards, deck);
        Map<Player, Money> bettingPlayers = Map.of(
                player, new Money(bettingCost)
        );

        BlackJack blackJack = new BlackJack(dealer, new Players(bettingPlayers));

        // when
        Map<Participant, Integer> revenueResult = blackJack.getRevenueResult();

        // then
        SoftAssertions.assertSoftly((softly) -> {
            softly.assertThat(revenueResult.get(player)).isEqualTo((int) (bettingCost * 1.5));
            softly.assertThat(revenueResult.get(dealer)).isEqualTo((int) (bettingCost * 1.5) * -1);
        });
    }
}
