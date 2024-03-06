package blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.CardNumber.ACE;
import static blackjack.domain.CardNumber.KING;
import static blackjack.domain.CardShape.HEART;
import static blackjack.domain.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class CardGameTest {
    @Test
    void 카드_한_장을_플레이어에게_지급한다() {
        Player player = new Player(new Hand());
        Card card = new Card(ACE, SPADE);

        CardGame cardGame = new CardGame();
        cardGame.giveCard(player, card);

        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    void 모든_플레이어에게_카드_2장을_지급한다() {
        Player mangcho = new Player(new Hand());
        Player ddang = new Player(new Hand());
        List<Player> players = List.of(mangcho, ddang);

        CardGame cardGame = new CardGame();
        cardGame.giveTwoCardsEachPlayer(players);

        assertSoftly(softly -> {
            softly.assertThat(mangcho.getCards().size()).isEqualTo(2);
            softly.assertThat(ddang.getCards().size()).isEqualTo(2);
        });
    }

    @Test
    void 딜러와_모든_플레이어의_숫자가_21_이하인_경우_숫자가_큰_사람이_이긴다() {
        CardGameJudge cardGameJudge = new CardGameJudge();

        Hand mangchoHand = new Hand();
        mangchoHand.add(new Card(ACE, HEART));
        mangchoHand.add(new Card(KING, HEART));
        mangchoHand.add(new Card(KING, SPADE));
        Player mangcho = new Player(mangchoHand);

        Hand dealerHand = new Hand();
        dealerHand.add(new Card(ACE, HEART));
        dealerHand.add(new Card(ACE, HEART));
        dealerHand.add(new Card(KING, SPADE));
        Player dealer = new Player(dealerHand);

        var result = cardGameJudge.judge(dealer, mangcho).getTotalResult();

        assertThat(result.get(mangcho)).isEqualTo(WinningStatus.WIN);
    }

}
