package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayerProfit;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.value.Nickname;
import java.util.List;

public class OutputView {

    public void printInitialHands(Dealer dealer, List<Player> players) {
        printCardDistributionHeader(dealer, players);
        printHiddenDealerHands(dealer);
        for (Player player : players) {
            String playerContent = makeCardContent(player.getNickname(), player.getHand());
            System.out.println(playerContent);
        }
        System.out.println();
    }

    public void printHitResult(Player player) {
        String content = makeCardContent(player.getNickname(), player.getHand());
        System.out.println(content);
    }

    public void printDealerDrawing(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
        System.out.println();
    }

    public void printFinalHands(Dealer dealer, List<Player> players) {
        String dealerContent = makeCardContent(dealer.getDealerName(), dealer.getHand());
        System.out.printf("%s - 결과: %s%n", dealerContent, dealer.getPoint());
        for (Player player : players) {
            String content = makeCardContent(player.getNickname(), player.getHand());
            System.out.printf("%s - 결과: %s%n", content, player.getPoint());
        }
        System.out.println();
    }

    public void printProfit(List<PlayerProfit> playerProfits) {
        System.out.println("## 최종 수익");
        String dealerProfitContent = makeDealerProfitContent(playerProfits);
        System.out.println(dealerProfitContent);

        for (PlayerProfit playerProfit : playerProfits) {
            String playerProfitContent = String.format("%s: %d",
                    playerProfit.getNickname().getValue(), playerProfit.getProfit());
            System.out.println(playerProfitContent);
        }
    }

    private void printCardDistributionHeader(Dealer dealer, List<Player> players) {
        String dealerNameContent = dealer.getDealerName().getValue();
        List<Nickname> playerNicknames = players.stream()
                .map(Player::getNickname)
                .toList();
        String playerNameContent = makeNicknamesContent(playerNicknames);
        String content = String.format("%s와 %s에게 2장을 나누었습니다.", dealerNameContent, playerNameContent);
        System.out.println(content);
    }

    private void printHiddenDealerHands(Dealer dealer) {
        String dealerContent = makeCardContent(dealer.getDealerName(), List.of(dealer.findFirstCard()));
        System.out.println(dealerContent);
    }

    private String makeCardContent(Nickname nickname, List<Card> cardsByPlayer) {
        List<String> cards = cardsByPlayer.stream()
                .map(card -> card.getValue().getDescription() + card.getShape())
                .toList();
        String cardContents = String.join(", ", cards);
        return String.format("%s카드: %s", nickname.getValue(), cardContents);
    }

    private String makeNicknamesContent(List<Nickname> nicknames) {
        List<String> nicknameContents = nicknames.stream().map(Nickname::getValue).toList();
        return String.join(", ", nicknameContents);
    }

    private String makeDealerProfitContent(List<PlayerProfit> playerProfits) {
        int totalPlayerProfit = playerProfits.stream().mapToInt(PlayerProfit::getProfit).sum();
        int dealerProfit = totalPlayerProfit * -1;
        return String.format("딜러: %d", dealerProfit);
    }
}
