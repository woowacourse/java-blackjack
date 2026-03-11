package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.PlayerGameResultDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitDraw(Players players, Dealer dealer) {
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n",
                String.join(", ", players.getNames()));

        List<Card> dealerCards = dealer.getCards();

        System.out.printf("딜러카드: %s%n", dealerCards.getFirst().getName());

        //플레이어 카드 출력
        for (Player player : players.getPlayers()) {
            String displayPlayerCards = displayPlayerCards(player.getCards());
            System.out.printf("%s카드: %s%n", player.getName(), displayPlayerCards);
        }

    }

    private String displayPlayerCards(List<Card> playerCards) {
        return playerCards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public void printCard(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), displayPlayerCards(player.getCards()));
    }

    public void printDealerDraw() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardResult(Dealer dealer, Players players) {
        System.out.printf("%n딜러카드: %s - 결과: %d%n",
                String.join(", ", displayPlayerCards(dealer.getCards())), dealer.getTotalPoint());
        for (Player player : players.getPlayers()) {
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), displayPlayerCards(player.getCards()),
                    player.getTotalPoint());
        }


    }

    public void printFinalGameResult(List<PlayerGameResultDto> playerGameResultDtos) {
        System.out.println("\n## 최종 승패");

        int winCount = 0;
        int tieCount = 0;
        int loseCount = 0;
        for (PlayerGameResultDto dto : playerGameResultDtos) {
            if (dto.gameResult().equals(GameResult.WIN.getName())) {
                loseCount++;
            }
            if (dto.gameResult().equals(GameResult.LOSE.getName())) {
                winCount++;
            }
            if (dto.gameResult().equals(GameResult.TIE.getName())) {
                tieCount++;
            }
        }
        System.out.printf("딜러: %d승 %d무 %d패%n", winCount, tieCount, loseCount);
        for (PlayerGameResultDto dto : playerGameResultDtos) {
            System.out.printf("%s: %s%n", dto.name(), dto.gameResult());
        }
    }

}
