package blackjack;

import blackjack.domain.GameScoreBoard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(final String... args) {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();

        initiallySetCard(dealer, players);

        // 플레이어의 카드 더받냐 안받냐 + 버스트 미구현
        takeMoreCardPlayerTurn(dealer, players);
        //딜러가 더 받냐 안받냐
        takeMoreCardDealerTurn(dealer);

        // 참가자들의 카드 스코어 결과
        OutputView.printParticipantResult(dealer, players);

        // 최종 승패 1. 승패 구하는 로직이 없다
        GameScoreBoard result = new GameScoreBoard(dealer, players);
        OutputView.printBlackjackGameResult(result);
    }

    private static List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        String participantsNames = InputView.inputParticipantsNames();
        for (String participantName : participantsNames.split(",")) {
            players.add(new Player(participantName));
        }
        return players;
    }

    private static void initiallySetCard(Dealer dealer, List<Player> players) {
        dealer.drawCardHandFirstTurn();
        dealer.drawCardToPlayers(players);
        OutputView.showParticipantsHand(dealer, players);
    }

    private static void takeMoreCardPlayerTurn(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            String takeCardAnswer = InputView.inputOneMoreCard(player);

            if (takeCardAnswer.equalsIgnoreCase("Y")) {
                do {
                    dealer.giveCard(player);
                    OutputView.showPlayerHand(player);
                    takeCardAnswer = InputView.inputOneMoreCard(player);
                } while (takeCardAnswer.equalsIgnoreCase("Y"));
            }

            if (takeCardAnswer.equalsIgnoreCase("N")) {
                OutputView.showPlayerHand(player);
            }
        }
    }

    private static void takeMoreCardDealerTurn(Dealer dealer) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerHandDrawMessage();
            dealer.selfDraw();
        }
    }
}
