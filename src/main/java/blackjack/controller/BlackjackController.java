package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.CardManager;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        // 블랙잭 게임 구성하기
        final CardManager cardManager = new CardManager(new CardRandomGenerator());
        final Participants participants = makeParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(cardManager, participants);

        // 초기 분배하기
        blackjackGame.spreadInitialCards();
        resultView.printSpreadCard(participants);

        // 플레이어마다 카드 나눠주기
        spreadExtraCards(blackjackGame);
    }

    private void spreadExtraCards(final BlackjackGame blackjackGame) {
        for (int i = 0; i < blackjackGame.getPlayerSize(); i++) {
            while (blackjackGame.canSpread(i)) {
                final Player player = blackjackGame.getPlayer(i);
                if (!isMoreCard(player)) {
                    break;
                }
                blackjackGame.spreadOneCard(i);
            }
        }
    }

    private Participants makeParticipants() {
        final Dealer dealer = new Dealer(new ArrayList<>());
        final Players players = makePlayers();
        final Participants participants = new Participants(dealer, players);
        return participants;
    }

    private boolean isMoreCard(final Player player) {
        return inputView.askMoreCard(player).equals("y");
    }

    private Players makePlayers() {
        String names = inputView.readNames();
        List<String> parsedNames = StringParser.parseComma(names);
        return new Players(parsedNames.stream()
                .map(name -> new Player(name, new ArrayList<>()))
                .toList());
    }
}
