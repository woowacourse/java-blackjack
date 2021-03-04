package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run() {
        List<Player> players = setPlayer();
        Dealer dealer = setDealer();

        setParticipantCardHand(dealer, players);

        progressPlayersTurn(dealer, players);
        progressDealerTurn(dealer);

        printResult(new Result());
    }

    private List<Player> setPlayer() {
        String playerNames = InputView.getPlayerName();


        return Arrays.stream(playerNames.split(","))
                .map(Player::from)
                .collect(Collectors.toList());
    }

    private Dealer setDealer() {
        return new Dealer();
    }

    private void setParticipantCardHand(Dealer dealer, List<Player> players) {
        dealer.setPlayerBaseCard(players);
        dealer.drawBaseCard();
        OutputView.printPlayersCardHandStatus(dealer, players);
    }

    private void progressPlayersTurn(Dealer dealer, List<Player> players) {
/*
        pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
        y
        pobi카드: 2하트, 8스페이드, A클로버
        pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
        n
        jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
        n
        jason카드: 7클로버, K스페이드
*/
        for (Player player : players) {
            if (player.lowerThanThreshold() && InputView.wantsReceive(player)) {
                dealer.deal(player);
            }
        }
    }

    private void progressDealerTurn(Dealer dealer) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerDrewMessage();
            dealer.pickAnotherCard();
        }
    }

    private void printResult(Result result) {
        /*
        딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
        pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
        jason카드: 7클로버, K스페이드 - 결과: 17

        ## 최종 승패
        딜러: 1승 1패
        pobi: 승
        jason: 패
*/
        OutputView.printResult(result);
    }
}
