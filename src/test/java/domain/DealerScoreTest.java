package domain;

import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.Cards;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Player;
import domain.result.DealerScore;
import domain.result.PlayerScore;
import domain.result.Profit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DealerScoreTest {

    @Test
    @DisplayName("플레이어 그룹의 스코어를 합산하여 딜러의 이익을 산출하여 DealerScore 객체를 생성한다.")
    void generateDealerScore() {
        Name name1 = Name.generatePlayerName("roy");
        Cards cards1 = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.KING)));
        Money money1 = new Money(1000);
        Player player1 = new Player(name1, cards1, money1);
        Profit profit1 = Profit.winnerProfit(player1);
        PlayerScore playerScore1 = new PlayerScore(player1.getName(), profit1);

        Name name2 = Name.generatePlayerName("roy");
        Cards cards2 = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TWO)));
        Money money2 = new Money(2000);
        Player player2 = new Player(name2, cards2, money2);
        Profit profit2 = Profit.looserProfit(player2);
        PlayerScore playerScore2 = new PlayerScore(player2.getName(), profit2);

        List<PlayerScore> playerScores = List.of(playerScore1, playerScore2);
        double expectedDealerProfit = 500;

        DealerScore dealerScore = DealerScore.generateDealerScore(playerScores);

        Assertions.assertThat(dealerScore.getProfit()).isEqualTo(expectedDealerProfit);

    }
}
