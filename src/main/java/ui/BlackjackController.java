package ui;

import domain.BlackjackGame;
import domain.card.Deck;
import domain.dto.PlayerCreateInfo;
import domain.dto.PlayerResult;
import domain.dto.Profit;
import domain.participant.BetMoney;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import ui.dto.ParticipantCardsDto;
import ui.dto.ParticipantResultDto;
import ui.dto.PlayerCreateDto;
import ui.dto.PlayerDto;
import ui.dto.ProfitsDto;
import ui.view.InputView;
import ui.view.ResultView;

public class BlackjackController {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        List<PlayerCreateInfo> playerInfos = readPlayerInfo();

        BlackjackGame blackjackGame = BlackjackGame.createNewGame(playerInfos);

        Players players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();
        Deck deck = blackjackGame.getDeck();

        resultView.printParticipantsCards(ParticipantCardsDto.from(players, dealer));

        hitStandPlayers(players, deck);
        hitStandDealer(dealer, deck);

        List<PlayerResult> playerResults = blackjackGame.collectPlayerResults();

        List<Profit> profits = blackjackGame.calculatePlayerProfits(playerResults);

        BetMoney dealerProfit = blackjackGame.calculateDealerResult(profits);

        resultView.printCardsWithResult(ParticipantResultDto.toDto(players, dealer));
        resultView.printProfits(ProfitsDto.toDto(profits, dealerProfit));
    }

    private List<PlayerCreateInfo> readPlayerInfo() {
        return inputView.readPlayersInfo().stream()
                .map(PlayerCreateDto::toDomain)
                .toList();
    }

    private void hitStandPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitStandPlayer(deck, player);
        }
    }

    private void hitStandPlayer(Deck deck, Player player) {
        while (!player.isFinished()) {
            hitByDecision(deck, player);
            resultView.printCards(PlayerDto.toDto(player));
        }
    }

    private void hitByDecision(Deck deck, Player player) {
        if (inputView.readHitStand(player.getName().getValue())) {
            player.draw(deck.draw());
            return;
        }
        player.stay();
    }

    private void hitStandDealer(Dealer dealer, Deck deck) {
        while (!dealer.isFinished()) {
            hitDealerByCondition(deck, dealer);
        }
        resultView.printDealerHitStand(false);
    }

    private void hitDealerByCondition(Deck deck, Dealer dealer) {
        if (dealer.isHittable()) {
            dealer.draw(deck.draw());
            resultView.printDealerHitStand(true);
            return;
        }
        dealer.stay();
    }
}
