package domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.OutputView;

public class ResultsTest {
    @Test
    @DisplayName("대결 시 승패 여부가 올바르게 저장된다.")
    void shouldSuccessSaveFightWinOrLoseResult() {
        BettingResults bettingResults = new BettingResults();
        Player dino = new Player(new Name("dino"));
        Dealer dealer = new Dealer();
        bettingResults.initParticipantBet(dino, new Money("10000"));
        bettingResults.initParticipantBet(dealer, new Money());
        Results result = new Results(bettingResults, new Players(List.of(dino)), dealer);

        dino.drawCard(new Card("5다이아몬드", 5));
        dino.drawCard(new Card("10다이아몬드", 10));
        dealer.drawCard(new Card("3하트", 3));
        dealer.drawCard(new Card("8클로버", 8));
        result.fight(dino, dealer);

        List<Integer> playerResults = OutputView.getResultsByName(result.getResults(), "dino");
        List<Integer> dealerResults = OutputView.getResultsByName(result.getResults(), "딜러");

        assertAll(
                () -> assertThat(playerResults).isEqualTo(List.of(1, 0, 0)),
                () -> assertThat(dealerResults).isEqualTo(List.of(0, 0, 1))
        );
    }

    @Test
    @DisplayName("대결 시 무승부 여부가 올바르게 저장된다.")
    void shouldSuccessSaveFightDrawResult() {
        BettingResults bettingResults = new BettingResults();
        Player dino = new Player(new Name("dino"));
        Dealer dealer = new Dealer();
        bettingResults.initParticipantBet(dino, new Money("10000"));
        bettingResults.initParticipantBet(dealer, new Money());
        Results result = new Results(bettingResults, new Players(List.of(dino)), dealer);

        dino.drawCard(new Card("5다이아몬드", 5));
        dino.drawCard(new Card("10다이아몬드", 10));
        dealer.drawCard(new Card("10하트", 10));
        dealer.drawCard(new Card("5클로버", 5));
        result.fight(dino, dealer);

        List<Integer> playerResults = OutputView.getResultsByName(result.getResults(), "dino");
        List<Integer> dealerResults = OutputView.getResultsByName(result.getResults(), "딜러");

        assertSoftly(softly -> {
            softly.assertThat(playerResults).isEqualTo(List.of(0, 1, 0));
            softly.assertThat(dealerResults).isEqualTo(List.of(0, 1, 0));
        });
    }

    @Test
    @DisplayName("대결 시 딜러가 Bust면 모든 참가자가 승리한다.")
    void everyPlayerWinWhenDealerBust() {
        BettingResults bettingResults = new BettingResults();
        Player dino = new Player(new Name("dino"));
        Player sungha = new Player(new Name("sungha"));
        Player pobi = new Player(new Name("pobi"));
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(dino, sungha, pobi));
        bettingResults.initParticipantBet(dino, new Money("10000"));
        bettingResults.initParticipantBet(sungha, new Money("10000"));
        bettingResults.initParticipantBet(pobi, new Money("10000"));
        bettingResults.initParticipantBet(dealer, new Money());
        Results result = new Results(bettingResults, players, dealer);

        dino.drawCard(new Card("5다이아몬드", 5));
        dino.drawCard(new Card("10다이아몬드", 10));
        dino.drawCard(new Card("10스페이드", 10));

        dealer.drawCard(new Card("10하트", 10));
        dealer.drawCard(new Card("5클로버", 5));
        dealer.drawCard(new Card("7다이아몬드", 7));

        sungha.drawCard(new Card("2하트", 2));
        sungha.drawCard(new Card("7스페이드", 7));

        pobi.drawCard(new Card("6다이아몬드", 6));
        pobi.drawCard(new Card("8클로버", 8));

        result.executeGame(players, dealer);

        List<Integer> dinoResult = OutputView.getResultsByName(result.getResults(), "dino");
        List<Integer> sunghaResult = OutputView.getResultsByName(result.getResults(), "sungha");
        List<Integer> pobiResult = OutputView.getResultsByName(result.getResults(), "pobi");
        List<Integer> dealerResults = OutputView.getResultsByName(result.getResults(), "딜러");

        assertSoftly(softly -> {
            softly.assertThat(dinoResult).isEqualTo(List.of(1, 0, 0));
            softly.assertThat(sunghaResult).isEqualTo(List.of(1, 0, 0));
            softly.assertThat(pobiResult).isEqualTo(List.of(1, 0, 0));
            softly.assertThat(dealerResults).isEqualTo(List.of(0, 0, 3));
        });
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우")
    void playerGetBlackJack() {
        BettingResults bettingResults = new BettingResults();
        Player dino = new Player(new Name("dino"));
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(dino));
        bettingResults.initParticipantBet(dino, new Money("10000"));
        bettingResults.initParticipantBet(dealer, new Money());
        Results result = new Results(bettingResults, players, dealer);

        dino.drawCard(new Card("A다이아몬드", 1));
        dino.drawCard(new Card("10다이아몬드", 10));

        dealer.drawCard(new Card("10하트", 10));
        dealer.drawCard(new Card("7클로버", 7));

        result.executeGame(players, dealer);

        assertSoftly(softly -> {
            softly.assertThat(bettingResults.getParticipantBet(dino).getAmount()).isEqualTo(15000);
            softly.assertThat(bettingResults.getParticipantBet(dealer).getAmount()).isEqualTo(-15000);
        });
    }

    @Test
    @DisplayName("플레이어와 딜러가 동점인 경우")
    void playerDraw() {
        BettingResults bettingResults = new BettingResults();
        Player dino = new Player(new Name("dino"));
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(dino));
        bettingResults.initParticipantBet(dino, new Money("10000"));
        bettingResults.initParticipantBet(dealer, new Money());
        Results result = new Results(bettingResults, players, dealer);

        dino.drawCard(new Card("7다이아몬드", 7));
        dino.drawCard(new Card("10다이아몬드", 10));

        dealer.drawCard(new Card("10하트", 10));
        dealer.drawCard(new Card("7클로버", 7));

        result.executeGame(players, dealer);

        assertSoftly(softly -> {
            softly.assertThat(bettingResults.getParticipantBet(dino).getAmount()).isEqualTo(0);
            softly.assertThat(bettingResults.getParticipantBet(dealer).getAmount()).isEqualTo(0);
        });
    }
}
