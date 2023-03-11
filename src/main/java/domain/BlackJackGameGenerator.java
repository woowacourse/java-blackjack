package domain;

import domain.card.CardDeck;
import domain.card.CardDeckGenerator;
import domain.participant.BettingMoney;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Names;
import domain.participant.ParticipantGenerator;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackGameGenerator {

    public BlackJackGame create(InputView inputView,
                                OutputView outputView) {
        Players players = createPlayers(inputView, outputView);
        Dealer dealer = ParticipantGenerator.createEmptyCardDealer();
        CardDeck cardDeck = CardDeckGenerator.create();

        return new BlackJackGame(players, dealer, cardDeck);
    }

    private Players createPlayers(InputView inputView, OutputView outputView) {
        Names names = createNames(inputView, outputView);

        List<Player> rawPlayers = new ArrayList<>();

        for (Name name : names.getNames()) {
            BettingMoney bettingMoney = createBettingMoney(name.getName(), inputView, outputView);
            Player player = ParticipantGenerator.createEmptyCardPlayer(name, bettingMoney);
            rawPlayers.add(player);
        }

        return new Players(rawPlayers);
    }

    private Names createNames(InputView inputView, OutputView outputView) {
        try {
            List<String> rawNames = inputView.readPlayerNames();

            return Names.from(rawNames);

        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return createNames(inputView, outputView);
        }
    }

    private BettingMoney createBettingMoney(String playerName, InputView inputView, OutputView outputView) {
        try {
            int bettingMoney = inputView.readBettingMoneyByName(playerName);
            return new BettingMoney(bettingMoney);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return createBettingMoney(playerName, inputView, outputView);
        }
    }
}
