package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerGroup;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Match;
import blackjack.domain.result.PlayerResult;
import blackjack.dto.GamerCardsResultDto;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {
    @Test
    @DisplayName("카드를 2장씩 나눈다.")
    void divideCard() {
        Player pepper = new Player("페퍼", initializeBettingMoney());
        Player ash = new Player("애쉬", initializeBettingMoney());
        PlayerGroup playerGroup = new PlayerGroup(Arrays.asList(pepper, ash));
        BlackJack blackJack = new BlackJack(playerGroup, new Dealer());
        blackJack.divideCards();

        List<Integer> cardSizes = List.of(pepper.getCardsSize(),
                ash.getCardsSize(),
                blackJack.getDealer().getCardsSize());

        assertThat(cardSizes)
                .containsExactly(2, 2, 2)
                .doesNotContain(0);
    }

    @Test
    @DisplayName("카드가 1장 더 추가되는지 테스트 한다.")
    void addCard() {
        Player pepper = new Player("페퍼", initializeBettingMoney());
        PlayerGroup playerGroup = new PlayerGroup(List.of(pepper));
        BlackJack blackJack = new BlackJack(playerGroup, new Dealer());
        int pepperCardsSize = pepper.getCardsSize();
        blackJack.addCardTo(pepper);

        assertThat(pepper.getCardsSize()).isEqualTo(pepperCardsSize + 1);
    }

    @Test
    @DisplayName("딜러의 게임 결과가 1승 1패 0무가 나와야 한다.")
    void getDealerResult() {
        GameResult gameResult = initializeGameResult();
        DealerResult dealerResult = gameResult.getDealerResult();
        Map<Match, Integer> dealerResults = dealerResult.getMatchResult();

        assertThat(dealerResults)
                .containsExactly(entry(Match.WIN, 1), entry(Match.LOSE, 1), entry(Match.DRAW, 0));
    }

    @Test
    @DisplayName("페퍼는 승리, 애쉬는 패배해야 한다.")
    void getPlayerResult() {
        GameResult gameResult = initializeGameResult();
        PlayerResult playerResult = gameResult.getPlayerResult();
        Map<String, Match> playerResults = playerResult.get();
        assertThat(playerResults)
                .containsExactly(entry("페퍼", Match.WIN), entry("애쉬", Match.LOSE));
    }

    @Test
    @DisplayName("페퍼는 bust로 패배, 애쉬도 패배해야 한다.")
    void getBustPlayerResult() {
        GameResult gameResult = initializeBustGameResult();
        PlayerResult playerResult = gameResult.getPlayerResult();
        Map<String, Match> playerResults = playerResult.get();
        assertThat(playerResults)
                .containsExactly(entry("페퍼", Match.LOSE), entry("애쉬", Match.LOSE));
    }

    private GameResult initializeGameResult() {
        Player pepper = new Player("페퍼", initializeBettingMoney());
        pepper.addCard(Card.of(CardShape.HEART, CardNumber.THREE));
        pepper.addCard(Card.of(CardShape.SPADE, CardNumber.EIGHT));
        pepper.addCard(Card.of(CardShape.CLUB, CardNumber.QUEEN));

        Player ash = new Player("애쉬", initializeBettingMoney());
        ash.addCard(Card.of(CardShape.CLUB, CardNumber.SEVEN));
        ash.addCard(Card.of(CardShape.SPADE, CardNumber.KING));

        PlayerGroup playerGroup = new PlayerGroup(Arrays.asList(pepper, ash));

        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(Card.of(CardShape.CLUB, CardNumber.NINE));
        dealer.addCard(Card.of(CardShape.DIAMOND, CardNumber.EIGHT));

        BlackJack blackJack = new BlackJack(playerGroup, dealer);
        return blackJack.getGameResult();
    }

    private GameResult initializeBustGameResult() {
        Player pepper = new Player("페퍼", initializeBettingMoney());
        pepper.addCard(Card.of(CardShape.CLUB, CardNumber.KING));
        pepper.addCard(Card.of(CardShape.SPADE, CardNumber.JACK));
        pepper.addCard(Card.of(CardShape.DIAMOND, CardNumber.JACK));

        Player ash = new Player("애쉬", initializeBettingMoney());
        ash.addCard(Card.of(CardShape.CLUB, CardNumber.SEVEN));
        ash.addCard(Card.of(CardShape.SPADE, CardNumber.KING));

        PlayerGroup playerGroup = new PlayerGroup(Arrays.asList(pepper, ash));

        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(Card.of(CardShape.CLUB, CardNumber.NINE));
        dealer.addCard(Card.of(CardShape.DIAMOND, CardNumber.EIGHT));
        BlackJack blackJack = new BlackJack(playerGroup, dealer);

        OutputView.printGamersCardAndSum(GamerCardsResultDto.of(blackJack.getGamers()));
        return blackJack.getGameResult();
    }

    private BettingMoney initializeBettingMoney() {
        return BettingMoney.of(10);
    }
}
