package service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static model.participator.Dealer.DEALER_NAME;

import dto.AllCardsAndSumDto;
import dto.AllParticipatorsDto;
import dto.ParticipatorDto;
import dto.TotalProfitDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Players;
import model.Result;
import model.matchplayerselect.SelectBlackJackAndCanMatchStrategy;
import model.matchplayerselect.SelectCanMatchStrategy;
import model.Status;
import model.card.Card;
import model.card.CardDeck;
import model.participator.Dealer;
import model.participator.Participator;
import model.participator.Player;
import util.CardConvertor;

public class BlackJackService {
    private static final int INIT_CARD_COUNT = 2;

    private Dealer dealer;
    private Players players;
    private CardDeck cardDeck;

    public AllParticipatorsDto initGame(final List<String> names, final List<Long> bettingAmounts) {
        initParticipatorsAndCardDeck(names, bettingAmounts);
        drawFirstTurn();
        return new AllParticipatorsDto(getPlayersDto(), toParticipatorDto(dealer));
    }

    private void initParticipatorsAndCardDeck(List<String> names, List<Long> bettingAmounts) {
        cardDeck = new CardDeck();
        players = Players.of(names, bettingAmounts);
        dealer = new Dealer();
    }

    private void drawFirstTurn() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            players.receiveCardsAll(cardDeck);
            dealer.receiveCard(cardDeck.drawCard());
        }
    }

    private List<ParticipatorDto> getPlayersDto() {
        return players.getPlayers().stream()
                .map(this::toParticipatorDto)
                .collect(toList());
    }

    private ParticipatorDto toParticipatorDto(Participator participator) {
        return new ParticipatorDto(participator.getPlayerName(), toCardsDto(participator.getCards()));
    }

    private List<String> toCardsDto(List<Card> cards) {
        return cards.stream()
                .map(CardConvertor::getCardString)
                .collect(toList());
    }

    public List<String> getPlayerNames() {
        return players.getPlayers().stream()
                .map(Participator::getPlayerName)
                .collect(toList());
    }

    public void matchFirstTurn() {
        if(dealer.getStatus().equals(Status.BLACKJACK) || players.anyHasBlackJack()) {
            executeBetting(players.matchWith(dealer, new SelectBlackJackAndCanMatchStrategy()));
        }
    }

    public boolean canReceiveCard(String name) {
        if (name.equals(DEALER_NAME)) {
            return dealer.canReceiveCard();
        }
        return players.canReceiveCard(name);
    }

    public ParticipatorDto hitPlayerOf(String name) {
        players.receiveCardTo(name, cardDeck);
        return toParticipatorDto(players.findByName(name));
    }

    public ParticipatorDto hitDealer() {
        dealer.receiveCard(cardDeck.drawCard());
        return toParticipatorDto(dealer);
    }

    public TotalProfitDto matchLastTurn() {
        Map<Player, Result> playerResultMap = players.matchWith(dealer, new SelectCanMatchStrategy());
        executeBetting(playerResultMap);
        return getTotalProfitDto(playerResultMap);
    }

    private TotalProfitDto getTotalProfitDto(Map<Player, Result> playerResultMap) {
        Map<String, Long> playerProfits = playerResultMap.keySet().stream()
                .collect(toMap(participator -> participator.getPlayerName(), participator -> participator.getProfit()));
        return new TotalProfitDto(playerProfits, dealer.getProfit());
    }

    public AllCardsAndSumDto getAllCardsAndSums() {
        dealer.setEveryCardGettable();
        return new AllCardsAndSumDto(getPlayerCardsAndSum(), toParticipatorDto(dealer), dealer.getSum());
    }

    private LinkedHashMap<ParticipatorDto, Integer> getPlayerCardsAndSum() {
        return players.getPlayers().stream().collect(
                toMap(this::toParticipatorDto, Participator::getSum, (participator, sum) -> sum, LinkedHashMap::new));
    }

    private void executeBetting(Map<Player, Result> participatorResults) {
        for (Player player : participatorResults.keySet()) {
            participatorResults.get(player).executeBetting(player, dealer);
        }
    }
}
