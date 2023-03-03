package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.name.PlayerName;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ResultRefereeTest {

    @Test
    void 스코어보드를_받아서_각플레이어의_승_패_무_를_결정한다() {
        // given
        final Player player1 = new Player(new PlayerName("player1"));
        final Player player2 = new Player(new PlayerName("player2"));
        final Player player3 = new Player(new PlayerName("player3"));

        final Players players = new Players(
                List.of(player1,
                        player2,
                        player3));

        final Dealer dealer = new Dealer();

        ScoreBoard scoreBoard = new ScoreBoard(dealer, players);

        CardPack cardPack1 = new CardPack(
                List.of(new Card(CardNumber.QUEEN, CardShape.CLOVER), new Card(CardNumber.ACE, CardShape.CLOVER)));
        CardPack cardPack2 = new CardPack(
                List.of(new Card(CardNumber.QUEEN, CardShape.CLOVER), new Card(CardNumber.QUEEN, CardShape.CLOVER)));
        CardPack cardPack3 = new CardPack(
                List.of(new Card(CardNumber.QUEEN, CardShape.CLOVER), new Card(CardNumber.QUEEN, CardShape.CLOVER),
                        new Card(CardNumber.QUEEN, CardShape.CLOVER)));

        CardPack dealerPack = new CardPack(
                List.of(new Card(CardNumber.QUEEN, CardShape.CLOVER), new Card(CardNumber.QUEEN, CardShape.CLOVER)));

        // when
        player1.drawCard(cardPack1);
        player1.drawCard(cardPack1);

        player2.drawCard(cardPack2);
        player2.drawCard(cardPack2);

        player3.drawCard(cardPack3);
        player3.drawCard(cardPack3);
        player3.drawCard(cardPack3);

        dealer.drawCard(dealerPack);
        dealer.drawCard(dealerPack);

        final int player1Score = ScoreReferee.calculateScore(player1.showCards());
        final int player2Score = ScoreReferee.calculateScore(player2.showCards());
        final int player3Score = ScoreReferee.calculateScore(player3.showCards());
        final int dealerScore = ScoreReferee.calculateScore(dealer.showCards());

        scoreBoard.writeScore(player1, player1Score);
        scoreBoard.writeScore(player2, player2Score);
        scoreBoard.writeScore(player3, player3Score);
        scoreBoard.writeScore(dealer, dealerScore);

        ResultReferee referee = new ResultReferee(scoreBoard);

        // then
        Assertions.assertThat(referee.askResultByUserName(player1.getName())).isEqualTo(Score.WIN);
        Assertions.assertThat(referee.askResultByUserName(player2.getName())).isEqualTo(Score.DRAW);
        Assertions.assertThat(referee.askResultByUserName(player3.getName())).isEqualTo(Score.LOSE);
    }

    @Test
    void 딜러의_승부_결과를_가져올_수_있다() {
        Players players = new Players(List.of(new Player(new PlayerName("dummy"))));
        ScoreBoard scoreBoard = new ScoreBoard(new Dealer(), players);

        ResultReferee referee = new ResultReferee(scoreBoard);

        Assertions.assertThat(referee.getDealerScore()).isNotEmpty();
    }
}
