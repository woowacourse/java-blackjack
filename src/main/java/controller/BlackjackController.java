package controller;

import domain.game.BlackjackGame;
import domain.game.GameResult;
import domain.strategy.RandomNumberGenerator;
import domain.user.Dealer;
import domain.user.People;
import domain.user.Player;
import view.InputView;
import view.OutputView;
import view.dto.PlayerDTO;
import view.mapper.GameResultMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private BlackjackGame blackjackGame;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        initializeGame();
        blackjackGame.startHit();

        gameStarted();

        hitPlayers();
        hitDealer();

        printGameResult();
    }

    private void printGameResult() {
        People people = blackjackGame.getPeople();
        Dealer dealer = people.getDealer();
        List<Player> players = people.getPlayers();

        outputView.printGameScore(PlayerDTO.of(dealer, dealer.sumHand()),
                makePlayersParameterWithResult(players)
        );

        outputView.printDealerRecord(PlayerDTO.from(dealer), makeDealerRecord());
        outputView.printPlayerRecord(makeAllPlayerRecordMap());
    }

    private void hitDealer() {
        blackjackGame.letDealerHitUntilThreshold(outputView::printDealerHitMessage);
    }

    private void hitPlayers() {
        blackjackGame.hitAllPlayersByCommand(
                inputView::inputCardCommand,
                outputView::printNameAndCard);
    }

    private void gameStarted() {
        outputView.printGameStarted(
                PlayerDTO.from(blackjackGame.getPeople().getDealer()),
                makePlayersParameter(blackjackGame.getPeople().getPlayers()));
    }

    private Map<String, Integer> makeDealerRecord() {
        Map<String, Integer> dealerRecord = new HashMap<>();
        blackjackGame.getDealerRecord().forEach((key, value) -> dealerRecord.put(GameResultMapper.getGameResult(key), value));
        return dealerRecord;
    }

    private Map<String, String> makeAllPlayerRecordMap() {
        Map<Player, GameResult> gameResultMap = blackjackGame.getGameResultForAllPlayer();
        Map<String, String> strMap = new HashMap<>();
        gameResultMap.forEach((key, value) -> strMap.put(key.getPlayerName().getValue(), GameResultMapper.getGameResult(value)));
        return strMap;
    }

    private List<PlayerDTO> makePlayersParameterWithResult(List<Player> players) {
        return players.stream()
                .map(it -> PlayerDTO.of(it, it.sumHand()))
                .collect(Collectors.toList());
    }

    private List<PlayerDTO> makePlayersParameter(List<Player> players) {
        return players.stream()
                .map(PlayerDTO::from)
                .collect(Collectors.toList());
    }

    private void initializeGame() {
        List<String> playersName = inputView.inputParticipantsName();
        blackjackGame = new BlackjackGame(playersName, new RandomNumberGenerator());
    }

}
