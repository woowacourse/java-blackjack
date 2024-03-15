package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import blackjack.domain.game.PlayersResult;
import blackjack.domain.game.Result2;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Players2Test {

    @DisplayName("주어진 플레이어 이름 리스트로 플레이어들을 생성한다.")
    @Test
    void testCreateByNameList() {
        // given
        List<String> playerNames = List.of("pobi", "jason");

        // when
        Players2 players = Players2.create(playerNames);

        // then
        assertThat(players.getPlayers()).containsExactly(
                new Player2(new Name("pobi")),
                new Player2(new Name("jason"))
        );
    }

    @DisplayName("모든 플레이어의 승패를 결정한다.")
    @Test
    void judge() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(CardRank.THREE, CardSuit.DIAMOND),
                new Card(CardRank.NINE, CardSuit.CLUB),
                new Card(CardRank.EIGHT, CardSuit.DIAMOND)
        ));
        Dealer2 dealer = new Dealer2(dealerHand);

        Hand pobiHand = new Hand(List.of(
                new Card(CardRank.TWO, CardSuit.HEART),
                new Card(CardRank.EIGHT, CardSuit.SPADE),
                new Card(CardRank.ACE, CardSuit.CLUB)
        ));
        Player2 pobi = new Player2(new Name("pobi"), pobiHand);

        Hand jasonHand = new Hand(List.of(
                new Card(CardRank.SEVEN, CardSuit.CLUB),
                new Card(CardRank.KING, CardSuit.SPADE)
        ));
        Player2 jason = new Player2(new Name("jason"), jasonHand);

        Players2 players = new Players2(List.of(pobi, jason));

        // when
        PlayersResult playersResult = players.judge(dealer);

        // then
        assertThat(playersResult.getResults()).containsExactlyEntriesOf(
                Map.of(
                        pobi, Result2.WIN,
                        jason, Result2.LOSE
                )
        );
    }
}
