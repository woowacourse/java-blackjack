package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printPlayersCardHandStatus(Dealer dealer, List<Player> players) {
        /*
        딜러와 pobi, jason에게 2장의 나누었습니다.
        딜러: 3다이아몬드
        pobi카드: 2하트, 8스페이드
        jason카드: 7클로버, K스페이드
*/
        String s1 = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        String s = String.format("딜러와 %s에게 2장의 나누었습니다.", s1);

        System.out.printf("%s 카드: %s", dealer.getName(), cardHands(dealer));
        for (Player player : players) {
            System.out.printf("%s 카드: %s", player.getName(), cardHands(player));
        }
    }

    public static void printDealerDrewMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(Result result) {
        /*
        딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
        pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
        jason카드: 7클로버, K스페이드 - 결과: 17

        ## 최종 승패
        딜러: 1승 1패
        pobi: 승
        jason: 패
        */
        String resultFormat = "%s 카드: %s - 결과: %d";

//        Dealer de
//        List<Card> dealerCardHand = result.getDealerCardHand();//3다이아몬드, 9클로버, 8다이아몬드
//        int dealerSum = result.getDealerSum();
//        System.out.printf(resultFormat, result.dealerName(), cardHands(de));
//
//        for (Player player : result.getPlayers()) {
//
//        }


//        System.out.printf(resultFormat, dealer.getName(), cardHands(dealer), );
//        for (Player player : players) {
//            System.out.printf(resultFormat, player.getName(), cardHands(player), );
//        }
    }

    public static String cardHands(Participant participant) {
        return participant.getCardHand().stream()
                .map(card -> card.getRankInitial() + card.getSuitName())
                .collect(Collectors.joining(", "));
    }
}
