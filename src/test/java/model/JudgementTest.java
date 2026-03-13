package model;

import static fixture.CardsTestFixture.createDealer;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.CardShape;
import model.card.CardValue;
import model.judgement.Judgement;
import model.judgement.PlayerResult;
import model.judgement.ResultStatus;
import model.paticipant.Dealer;
import model.paticipant.BettingPlayer;
import model.paticipant.Player;
import model.paticipant.Players;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class JudgementTest {

    @ParameterizedTest(name = "플레이어 {0}, 딜러 {1}일 때 결과는 {2}이다")
    @MethodSource("fixture.PlayerResultTestFixture#플레이어의_상황별_승_패_정보제공")
    void 다양한_게임_상황에서_승패를_올바르게_판정한다(
            List<Card> playerCards,
            List<Card> dealerCards,
            ResultStatus status
    ) {
        // given
        Dealer dealer = createDealer();
        dealerCards.forEach(dealer::addCard);

        Player player = new BettingPlayer("pobi", 10000);
        playerCards.forEach(player::addCard);

        // when
        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, new Players(List.of(player)));

        // then
        assertThat(playerResult.countByStatus(status)).isEqualTo(1);
    }

    @Test
    void 플레이어가_블랙잭이고_딜러가_블랙잭이_아니면_BLACKJACK_결과이다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardValue.TEN));
        dealer.addCard(new Card(CardShape.HEART, CardValue.NINE));

        Player player = new BettingPlayer("pobi", 10000);
        player.addCard(new Card(CardShape.HEART, CardValue.ACE));
        player.addCard(new Card(CardShape.HEART, CardValue.KING));

        // when
        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, new Players(List.of(player)));

        // then
        assertThat(playerResult.countByStatus(ResultStatus.BLACKJACK)).isEqualTo(1);
    }

    @Test
    void 플레이어와_딜러_모두_블랙잭이면_DRAW_결과이다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardValue.ACE));
        dealer.addCard(new Card(CardShape.HEART, CardValue.KING));

        Player player = new BettingPlayer("pobi", 10000);
        player.addCard(new Card(CardShape.DIAMOND, CardValue.ACE));
        player.addCard(new Card(CardShape.DIAMOND, CardValue.KING));

        // when
        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, new Players(List.of(player)));

        // then
        assertThat(playerResult.countByStatus(ResultStatus.DRAW)).isEqualTo(1);
    }

    @Test
    void 딜러가_블랙잭이고_플레이어가_블랙잭이_아니면_LOSE_결과이다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardValue.ACE));
        dealer.addCard(new Card(CardShape.HEART, CardValue.KING));

        Player player = new BettingPlayer("pobi", 10000);
        player.addCard(new Card(CardShape.HEART, CardValue.TEN));
        player.addCard(new Card(CardShape.HEART, CardValue.FIVE));
        player.addCard(new Card(CardShape.HEART, CardValue.SIX));

        // when
        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, new Players(List.of(player)));

        // then
        assertThat(playerResult.countByStatus(ResultStatus.LOSE)).isEqualTo(1);
    }
}
