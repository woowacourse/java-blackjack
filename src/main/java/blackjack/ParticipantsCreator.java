package blackjack;

import blackjack.domain.Amount;
import blackjack.domain.Nicknames;
import blackjack.domain.Participants;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.util.PlayerNameParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class ParticipantsCreator {

    private final InputView inputView;
    private final OutputView outputView;

    public ParticipantsCreator(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Participants create() {
        Nicknames nicknames = readPlayerNicknames();
        Players players = createPlayers(nicknames);
        return new Participants(players, Dealer.from());
    }

    private Nicknames readPlayerNicknames() {
        try {
            outputView.askGameMembers();
            String playerNamesInput = inputView.readLine();
            List<String> playerNames = PlayerNameParser.parsePlayerNames(playerNamesInput);
            return Nicknames.from(playerNames);
        } catch (IllegalArgumentException e) {
            outputView.printLine(e.getMessage());
            return readPlayerNicknames();
        }
    }

    private Players createPlayers(Nicknames nicknames) {
        List<String> playerNicknames = nicknames.getRawNicknames();
        Players players = Players.makeEmptyPlayers();
        for (String nickname : playerNicknames) {
            players = addPlayerWithValidBet(nicknames, nickname, players);
        }
        return players;
    }

    private Players addPlayerWithValidBet(Nicknames nicknames, String nickname, Players players) {
        try {
            Amount playerRequest = readBettingRequest(nickname);
            return players.addPlayer(nicknames.findByNickname(nickname), playerRequest);
        } catch (IllegalArgumentException e) {
            outputView.printLine(e.getMessage());
            return addPlayerWithValidBet(nicknames, nickname, players);
        }
    }

    private Amount readBettingRequest(String nickname) {
        outputView.askBetAmount(nickname);
        String amount = inputView.readLine();
        return Amount.from(amount);
    }
}
