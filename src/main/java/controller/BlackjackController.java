package controller;

import domain.game.BlackjackGame;
import domain.game.HitCommand;
import domain.game.GameResult;
import domain.strategy.RandomNumberGenerator;
import domain.user.People;
import domain.user.Player;
import view.InputView;
import view.OutputView;
import view.dto.PlayerParameter;
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

        People people = blackjackGame.getPeople();

        outputView.printPlayersInfoWhenGameStarted(
                PlayerParameter.from(people.getDealer()),
                makePlayersParameter(people));


        blackjackGame.hitAllPlayersByCommand((name) -> HitCommand.findCommand(inputView.inputCardCommand(name)),
                outputView::printPlayerCardWithName);
        hitByDealer();


        outputView.printGameScore(
                PlayerParameter.of(people.getDealer(), people.getDealer().sumHand()),
                makePlayersParameterWithResult(people)
        );

        outputView.printDealerRecord(PlayerParameter.from(people.getDealer()), makeDealerRecord());
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

    private List<PlayerParameter> makePlayersParameterWithResult(People people) {
        return people.getPlayers().stream()
                .map(it -> PlayerParameter.of(it, it.sumHand()))
                .collect(Collectors.toList());
    }

    private List<PlayerParameter> makePlayersParameter(People people) {
        return people.getPlayers().stream()
                .map(PlayerParameter::from)
                .collect(Collectors.toList());
    }

    private void initializeGame() {
        List<String> playersName = inputView.inputParticipantsName();
        blackjackGame = new BlackjackGame(playersName, new RandomNumberGenerator());
    }

    private void hitByDealer() {
        if (blackjackGame.dealerNeedsHit()) {
            outputView.printDealerHitMessage();
            blackjackGame.letDealerHitUntilThreshold();
        }
    }
}
