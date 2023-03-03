import java.util.List;

import blackjackGame.BlackjackGame;
import player.DealerFirstOpenDto;
import player.DealerWinningDto;
import player.Name;
import player.Player;
import player.PlayerOpenDto;
import player.PlayerResultDto;
import player.PlayerWinningDto;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackGame blackjackGame;

    public Controller(InputView inputView, OutputView outputView, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGame = blackjackGame;
    }

    void run() {

        // 참여자 이름 받아 플레이어 생성
        List<String> names = inputView.readPlayerNames();
        for (String name : names) {
            blackjackGame.addPlayer(new Player(new Name(name)));
        }
        // 첫번째 draw
        blackjackGame.supplyCardsToDealer();
        blackjackGame.supplyCardsToPlayers();
        outputView.printFirstDrawMessage(names);

        DealerFirstOpenDto dealerFirstOpen = blackjackGame.getDealerFirstOpen();
        List<PlayerOpenDto> playersCards = blackjackGame.getPlayersCards();
        outputView.printFirstOpenCards(dealerFirstOpen, playersCards);

        // 추가 카드 draw
        for (int i = 0; i < blackjackGame.countPlayer(); i++) {
            Name userName = blackjackGame.findUserNameByIndex(i);
            String hitCommand = inputView.readHitCommand(userName);
            while (hitCommand.equals("y") && !blackjackGame.isBust(i)) {
                blackjackGame.supplyAdditionalCard(i);
                PlayerOpenDto playerCard = blackjackGame.getPlayerCardsByIndex(i);
                outputView.printPlayerCard(playerCard);
                if (blackjackGame.isBust(i)) {
                    break;
                }
                hitCommand = inputView.readHitCommand(userName);
            }
        }
        // 딜러 추가 카드 draw
        while (blackjackGame.canDealerHit()) {
//            아웃풋 뷰 출력 어쩌고 저쩌고
            blackjackGame.supplyAdditionalCardToDealer();
            outputView.printDealerHitMessage();
        }
        // 결과 출력
        PlayerResultDto dealerResult = blackjackGame.getDealerResult();
        List<PlayerResultDto> playerResults = blackjackGame.getPlayerResults();

        outputView.printFinalResults(dealerResult, playerResults);

//        출력따리
        // 승패 출력
        blackjackGame.calculateWinning();
        DealerWinningDto dealerWinningResult = blackjackGame.getDealerWinningResult();
        List<PlayerWinningDto> playerWinningResults = blackjackGame.getPlayerWinningResults();
        outputView.printWinningResults(dealerWinningResult, playerWinningResults);
    }
}
