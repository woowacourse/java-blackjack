package domain.player;

import domain.blackjack.Result;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private static final Card DIAMOND_ACE = Card.of(CardShape.DIAMOND, CardRank.ACE);
    private static final Card DIAMOND_TEN = Card.of(CardShape.DIAMOND, CardRank.TEN);
    private static final Card DIAMOND_NINE = Card.of(CardShape.DIAMOND, CardRank.NINE);
    private static final Card DIAMOND_EIGHT = Card.of(CardShape.DIAMOND, CardRank.EIGHT);
    private static final Card DIAMOND_FOUR = Card.of(CardShape.DIAMOND, CardRank.FOUR);

    @DisplayName("딜러의 카드 합을 기준으로 플레이어의 승패를 결정한다.")
    @Test
    void decideResultSuccessTest() {
        Dealer dealer = new Dealer();
        Players players = Players.from(
                List.of(
                        Player.of(Name.from("pobi"), BettingMoney.from(1000)),
                        Player.of(Name.from("crong"), BettingMoney.from(1000))
                )
        );
        Player pobi = players.getPlayers().get(0);
        Player crong = players.getPlayers().get(1);

        giveCardsTo(dealer, List.of(DIAMOND_TEN, DIAMOND_NINE)); // 19

        giveCardsTo(pobi, List.of(DIAMOND_TEN, DIAMOND_TEN)); // 20
        giveCardsTo(crong, List.of(DIAMOND_TEN, DIAMOND_EIGHT)); // 18

        dealer.decideResults(players);

        assertThat(dealer.getGameResult().values())
                .containsExactly(Result.LOSE, Result.WIN);
    }

    @DisplayName("카드의 합이 같더라도, 딜러가 블랙잭일 경우, 딜러가 승리한다. ")
    @Test
    void decideResultWithBlackjackTest() {
        Dealer dealer = new Dealer();
        Players players = Players.from(
                List.of(Player.of(Name.from("pobi"), BettingMoney.from(1000)))
        );
        Player pobi = players.getPlayers().get(0);

        giveCardsTo(dealer, List.of(DIAMOND_ACE, DIAMOND_TEN));
        giveCardsTo(pobi, List.of(DIAMOND_NINE, DIAMOND_EIGHT, DIAMOND_FOUR));

        dealer.decideResults(players);

        assertThat(dealer.getGameResult().values())
                .containsExactly(Result.WIN);
    }

    public void giveCardsTo(Participant participant, List<Card> cards) {
        for (Card card : cards) {
            participant.receive(card);
        }
    }
}
