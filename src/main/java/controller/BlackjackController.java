package controller;

import domain.game.BlackjackGame;
import domain.game.GameResult;
import domain.strategy.RandomNumberGenerator;
import domain.user.Player;
import view.InputView;
import view.OutputView;
import view.dto.PlayerDto;
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

    private void initializeGame() {
        List<String> playersName = inputView.inputParticipantsName();
        blackjackGame = new BlackjackGame(playersName, new RandomNumberGenerator());
    }

    private void gameStarted() {
        outputView.printGameStarted(
                PlayerDto.from(blackjackGame.getDealer()),
                makePlayersDto(blackjackGame.getPlayers()));
    }

    private void hitPlayers() {
        blackjackGame.hitAllPlayersByCommand(
                inputView::inputCardCommand,
                outputView::printNameAndCard);
    }

    private void hitDealer() {
        blackjackGame.letDealerHitUntilThreshold(outputView::printDealerHitMessage);
    }

    private void printGameResult() {
        outputView.printGameScore(PlayerDto.of(blackjackGame.getDealer(), blackjackGame.sumDealerHand()),
                makePlayersDtoWithResult(blackjackGame.getPlayers())
        );

        outputView.printDealerRecord(PlayerDto.from(blackjackGame.getDealer()), makeDealerRecord());
        outputView.printPlayerRecord(makeAllPlayerRecordMap());
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

    private List<PlayerDto> makePlayersDtoWithResult(List<Player> players) {
        return players.stream()
                .map(it -> PlayerDto.of(it, it.sumHand()))
                .collect(Collectors.toList());
    }

    private List<PlayerDto> makePlayersDto(List<Player> players) {
        return players.stream()
                .map(PlayerDto::from)
                .collect(Collectors.toList());
    }

}
