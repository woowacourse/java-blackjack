package blackjack.controller;

import blackjack.domain.BettingMoney;
import blackjack.domain.BlackjackGame;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Player;
import blackjack.dto.CardInfoDto;
import blackjack.dto.FinalResultDto;
import blackjack.util.RetryUtil;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CardDeck cardDeck;

    public BlackjackController(InputView inputView, OutputView outputView, CardDeck cardDeck) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardDeck = cardDeck;
    }

    public void run() {
        Dealer dealer = new Dealer(new CardHand());
        List<ParticipantName> playerNames = createPlayerNames();
        List<Player> players = createPlayers(playerNames);

        BlackjackGame game = new BlackjackGame(players, dealer, cardDeck);

        game.distributeInitialCards();
        displayParticipantStartCards(game, dealer);

        for (Player player : game.getPlayers()) {
            processPlayerTurn(player, game);
        }
        processDealerTurn(game);

        displayFinalCardsInfo(dealer, game);
        displayAllFinalProfitsInfo(game);
    }

    private List<ParticipantName> createPlayerNames() {
        return RetryUtil.getReturnWithRetry(() -> {
            String[] playerNames = inputView.readPlayerName();
            List<ParticipantName> participantNames = new ArrayList<>();
            for (String playerName : playerNames) {
                participantNames.add(new ParticipantName(playerName));
            }
            return participantNames;
        });
    }

    private List<Player> createPlayers(List<ParticipantName> playerNames) {
        return RetryUtil.getReturnWithRetry(() -> {
            List<Player> players = new ArrayList<>();
            for (ParticipantName playerName : playerNames) {
                int money = inputView.readBettingMoney(playerName.getValue());
                BettingMoney bettingMoney = new BettingMoney(money);
                players.add(new Player(playerName, new CardHand(), bettingMoney));
            }
            return players;
        });
    }

    private void displayFinalCardsInfo(Dealer dealer, BlackjackGame game) {
        outputView.displayFinalCardStatus(
                FinalResultDto.from(dealer),
                FinalResultDto.fromPlayers(game.getPlayers()));
    }

    private void displayParticipantStartCards(BlackjackGame game, Dealer dealer) {
        List<CardInfoDto> playerStartCardInfos = game.getPlayers().stream()
                .map(player -> CardInfoDto.from(player.getName(), player.getCardDeck()))
                .toList();
        CardInfoDto dealerStartCardInfo = CardInfoDto.from(dealer.getName(), dealer.getCardDeck());
        outputView.displayCardDistribution(
                dealerStartCardInfo,
                playerStartCardInfos
        );
    }

    private void processPlayerTurn(Player player, BlackjackGame game) {
        while (game.canHit(player)) {
            HitOption wantHit = getHitOption(player);
            if (wantHit.isNo()) {
                break;
            }
            game.playerHit(player);
            outputView.displayCardInfo(CardInfoDto.from(player.getName(), player.getCardDeck()));
            if (player.isBust()) {
                outputView.displayBustNotice();
                break;
            }
        }
    }

    private void processDealerTurn(BlackjackGame game) {
        int beforeDealerCardSize = game.getDealer().getCardSize();
        game.dealerTurn();
        int afterDealerCardSize = game.getDealer().getCardSize();

        int numberOfHit = afterDealerCardSize - beforeDealerCardSize;
        outputView.displayDealerTurnResult(numberOfHit);
    }

    private HitOption getHitOption(Player player) {
        return RetryUtil.getReturnWithRetry(() -> {
            String answer = inputView.readOneMoreCard(player.getName());
            return HitOption.from(answer);
        });
    }

    private void displayAllFinalProfitsInfo(BlackjackGame game) {
        int dealerProfit = game.calculateDealerProfit();
        Map<Player, Integer> playersProfit = game.calculatePlayersProfit();

        outputView.displayFinalProfits(dealerProfit, playersProfit);
    }
}
