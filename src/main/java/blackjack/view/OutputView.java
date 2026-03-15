package blackjack.view;

import blackjack.domain.vo.GameResult;
import blackjack.dto.CardDto;
import blackjack.dto.DealResultDto;
import blackjack.dto.DealerScoreDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.PlayerHandDto;
import blackjack.dto.PlayerScoreDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printDealResult(DealResultDto dealResultDto) {
        String names = joinPlayerNames(dealResultDto);
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
        System.out.println("딜러카드: " + dealResultDto.dealerOpenCard().display());
        for (PlayerHandDto playerHand : dealResultDto.playerHands()) {
            printCurrentPlayerHand(playerHand);
        }
        System.out.println();
    }

    private String joinPlayerNames(DealResultDto dealResultDto) {
        return dealResultDto.playerHands().stream()
                .map(PlayerHandDto::name)
                .collect(Collectors.joining(", "));
    }

    public void printCurrentPlayerHand(PlayerHandDto playerHand) {
        String cards = formatCards(playerHand.cards());
        System.out.println(playerHand.name() + "카드: " + cards);
    }

    public void printPlayerHand(String name, List<String> cards) {
        String cardDisplay = String.join(", ", cards);
        System.out.println(name + "카드: " + cardDisplay);
    }

    public void printGameResult(GameResultDto gameResultDto) {
        DealerScoreDto dealer = gameResultDto.dealerResult();
        System.out.println("딜러카드: " + formatCards(dealer.cards())
                + " - 결과: " + dealer.score());

        for (PlayerScoreDto player : gameResultDto.playerResults()) {
            System.out.println(player.name() + "카드: " + formatCards(player.cards())
                    + " - 결과: " + player.score());
        }
        System.out.println();
    }

    private String formatCards(List<CardDto> cards) {
        return cards.stream()
                .map(CardDto::display)
                .collect(Collectors.joining(", "));
    }

    public void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printFinalResult(List<GameResult> gameResults, BigDecimal dealerProfit) {
        System.out.println("## 최종 승패");
        printDealerFinalResult(dealerProfit);
        gameResults.forEach(result ->
                System.out.println(result.name() + ": " + result.profit()));
    }

    private void printDealerFinalResult(BigDecimal dealerProfit) {
        System.out.println("딜러: " + dealerProfit);
    }

    public void printEmptyLine(){
        System.out.println();
    }
}
