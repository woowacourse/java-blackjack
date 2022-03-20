package blackjack.controller;

import blackjack.domain.bet.Money;
import blackjack.domain.bet.PlayersBet;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.PlayersDto;
import blackjack.view.dto.ReceiveDecision;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    public void run() {
        CardDeck cardDeck = CardDeck.initShuffled();
        Dealer dealer = Dealer.createDefaultNameDealer();
        List<Player> players = createPlayersWithName(InputView.requestPlayerNames());

        PlayersBet playersBet = createPlayersBetMoney(players);
        initDealerCards(cardDeck, dealer);
        initPlayerCards(cardDeck, players);
        OutputView.printInitCardHandStatus(ParticipantDto.of(dealer), PlayersDto.of(players));

        playBlackJack(cardDeck, dealer, players);
        Map<Player, Result> judgeTable = Result.createJudgeTable(dealer, players);
        Map<Participant, Money> participantBetTable = playersBet
                .calculateProfit(judgeTable, dealer);

        OutputView.printFinalStatus(ParticipantDto.of(dealer), PlayersDto.of(players));
        OutputView.printFinalResult(participantBetTable);
    }

    private List<Player> createPlayersWithName(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::of)
                .collect(Collectors.toList());
    }

    private PlayersBet createPlayersBetMoney(List<Player> players) {
        PlayersBet playersBet = new PlayersBet();
        for (Player player : players) {
            Money money = new Money(InputView.requestMoney(player.getName().getValue()));
            playersBet.add(player, money);
        }
        return playersBet;
    }

    private void initDealerCards(CardDeck cardDeck, Dealer dealer) {
        dealer.receive(cardDeck.startCards());
    }

    private void initPlayerCards(CardDeck cardDeck, List<Player> players) {
        for (Player player : players) {
            player.receive(cardDeck.startCards());
        }
    }

    private void playBlackJack(CardDeck cardDeck, Dealer dealer, List<Player> players) {
        playPlayersTurn(cardDeck, players);
        playDealerTurn(cardDeck, dealer);
    }

    private void playPlayersTurn(CardDeck cardDeck, List<Player> players) {
        for (Player player : players) {
            while (isPlayable(player)) {
                player.receive(cardDeck.draw());
                OutputView.printCardHandStatus(ParticipantDto.of(player));
            }
        }
    }

    private void playDealerTurn(CardDeck cardDeck, Dealer dealer) {
        while (dealer.isReceivable()) {
            OutputView.printDealerStatus();
            dealer.receive(cardDeck.draw());
        }
    }

    private boolean isPlayable(Player player) {
        return player.isReceivable() && ReceiveDecision.wantMore(InputView.requestDecision(player));
    }
}
