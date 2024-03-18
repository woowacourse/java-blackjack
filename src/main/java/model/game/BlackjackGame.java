package model.game;

import java.math.BigDecimal;
import java.util.List;
import model.betting.Bet;
import model.card.Card;
import model.card.CardDeck;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;
import model.result.CardDto;
import model.result.CardsDto;
import model.result.ParticipantScores;
import model.result.ProfitDto;
import model.result.ProfitsDto;

public class BlackjackGame {

    private static final int DEQUE_COUNT = 4;
    private static final int INITIAL_CARD_COUNT = 2;

    private final CardDeck cardDeck;

    public BlackjackGame() {
        cardDeck = CardDeck.createShuffledDeck(DEQUE_COUNT);
    }

    public CardsDto dealInitialCards(Dealer dealer, Players players) {
        dealCards(dealer);
        CardDto dealerCard = new CardDto(dealer.getName(), List.of(dealer.getFirstCard()));
        List<CardDto> playerCards = players.getPlayers()
            .stream()
            .map(this::dealCards)
            .toList();
        return new CardsDto(dealerCard, playerCards);
    }

    private CardDto dealCards(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            Card card = cardDeck.drawCard();
            participant.hit(card);
        }
        return new CardDto(participant.getName(), participant.getCardsInfo());
    }

    public CardDto dealCardTo(Player player) {
        Card card = cardDeck.drawCard();
        player.hit(card);
        return new CardDto(player.getName(), player.getCardsInfo());
    }

    public boolean dealCardTo(Dealer dealer) {
        if (dealer.isPossibleHit()) {
            Card card = cardDeck.drawCard();
            dealer.hit(card);
            return true;
        }
        return false;
    }

    public ParticipantScores finish(Dealer dealer, Players players) {
        return ParticipantScores.of(dealer, players);
    }

    public ProfitsDto calculateProfit(Dealer dealer, Players players) {
        List<ProfitDto> playersProfits = calculatePlayerProfits(players, dealer);
        ProfitDto dealerProfit = calculateDealerProfit(playersProfits, dealer);
        return new ProfitsDto(dealerProfit, playersProfits);
    }

    private List<ProfitDto> calculatePlayerProfits(Players players, Dealer dealer) {
        return players.getPlayers()
            .stream()
            .map(player -> generatePlayerProfit(player, dealer))
            .toList();
    }

    private ProfitDto generatePlayerProfit(Player player, Dealer dealer) {
        Bet bet = player.getBet();
        GameResult resultStatus = dealer.judge(player);
        BigDecimal profit = resultStatus.calculateProfit(bet);
        return new ProfitDto(player.getName(), profit);
    }

    private ProfitDto calculateDealerProfit(List<ProfitDto> playerProfits, Dealer dealer) {
        BigDecimal profit = playerProfits.stream()
            .map(ProfitDto::profit)
            .reduce(BigDecimal.ZERO, BigDecimal::subtract);
        return new ProfitDto(dealer.getName(), profit);
    }
}
