package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.*;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private final Deck deck = new Deck(new RandomCardsGenerator());

    @DisplayName("플레이어들의 승패를 결정한다")
    @Test
    void test() {
        //given
        Cards cards1 = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.THREE, CardShape.CLOVER)
                )
        );
        Cards cards2 = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.TWO, CardShape.CLOVER)
                )
        );
        Cards cards3 = Cards.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.FOUR, CardShape.CLOVER)
                )
        );
        Dealer dealer = new Dealer(cards1, deck);
        Player player1 = Player.from("플레이어", cards2);
        Player player2 = Player.from("플레이어2", cards3);
        Map<Player, GameResult> expected = Map.of(
                player1, GameResult.LOSE,
                player2, GameResult.WIN
        );

        //when
        Map<Player, GameResult> actual = dealer.getGameResult(new Players(List.of(player1, player2)));

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어에게 카드를 할당할 수 있다.")
    @Test
    void 플레이어에게_카드_할당() {
        //given
        Card card = new Card(CardNumber.A, CardShape.CLOVER);
        Deck deck = new Deck(() -> new ArrayList<>(List.of(card)));

        Dealer dealer = new Dealer(Cards.empty(), deck);
        Player player = Player.init("플레이어");

        //when
        dealer.giveOneCardTo(player);
        Cards cards = player.getCards();

        //then
        assertThat(cards.getValues()).contains(card);
    }

    @DisplayName("딜러 카드의 합이 17 이상이 될 때까지 뽑은 횟수를 반환한다")
    @Test
    void 딜러가_뽑은_횟수_검증() {
        //given
        Card card1 = new Card(CardNumber.A, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.A, CardShape.HEART);
        Card card3 = new Card(CardNumber.A, CardShape.CLOVER);
        Deck deck = new Deck(() -> new ArrayList<>(List.of(card1, card2, card3)));

        Cards dealerCards = Cards.of(new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        )));
        Dealer dealer = new Dealer(dealerCards, deck);

        //when
        final int count = dealer.drawCards();

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(count).isEqualTo(2);
            softly.assertThat(dealer.getCardScore()).isEqualTo(17);
        });
    }
}
