package service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import dto.AllCardsAndSumDto;
import dto.AllParticipatorsDto;
import dto.ParticipatorDto;
import dto.TotalProfitDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardDeck;
import model.card.cardGettable.CardsGettable;
import model.card.cardGettable.EveryCardsGettable;
import model.card.cardGettable.FirstCardsGettable;
import model.participator.Dealer;
import model.participator.Participator;
import model.participator.Players;
import model.participator.matchplayerselect.SelectBlackJackAndNotMatchedPlayerStrategy;
import model.participator.matchplayerselect.SelectNotMatchedPlayerStrategy;
import util.CardConvertor;

public class BlackJackService {
    private static final int INIT_CARD_COUNT = 2;

    private Dealer dealer;
    private Players players;
    private CardDeck cardDeck;

    public AllParticipatorsDto initGame(final List<String> names, final List<Long> bettingAmounts) {
        initParticipatorsAndCardDeck(names, bettingAmounts);
        drawFirstTurn();
        return new AllParticipatorsDto(getPlayersDto(), toParticipatorDto(dealer, new FirstCardsGettable()));
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
                .map(player -> toParticipatorDto(player, new EveryCardsGettable()))
                .collect(toList());
    }

    private ParticipatorDto toParticipatorDto(Participator participator, CardsGettable cardsGettable) {
        return new ParticipatorDto(participator.getPlayerName(), toCardsDto(participator.getCards(cardsGettable)));
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
        if (dealer.isBlackJack() || players.anyHasBlackJack()) {
            players.matchWith(dealer, new SelectBlackJackAndNotMatchedPlayerStrategy());
        }
    }

    public boolean canReceiveCard(String name) {
        if (dealer.isSameName(name)) {
            return dealer.canReceiveCard();
        }
        return players.canReceiveCard(name);
    }

    public ParticipatorDto hitPlayerOf(String name) {
        players.receiveCardTo(name, cardDeck);
        return toParticipatorDto(players.findByName(name), new EveryCardsGettable());
    }

    public void hitDealer() {
        dealer.receiveCard(cardDeck.drawCard());
    }

    public TotalProfitDto matchLastTurn() {
        players.matchWith(dealer, new SelectNotMatchedPlayerStrategy());
        return getTotalProfitDto();
    }

    private TotalProfitDto getTotalProfitDto() {
        Map<String, Long> playerProfits = players.getPlayers().stream()
                .collect(toMap(Participator::getPlayerName, Participator::getProfit));
        return new TotalProfitDto(playerProfits, dealer.getProfit());
    }

    public AllCardsAndSumDto getAllCardsAndSums() {
        return new AllCardsAndSumDto(getPlayerCardsAndSum(), toParticipatorDto(dealer, new EveryCardsGettable()),
                dealer.getSum());
    }

    private LinkedHashMap<ParticipatorDto, Integer> getPlayerCardsAndSum() {
        return players.getPlayers().stream().collect(
                toMap(player -> toParticipatorDto(player, new EveryCardsGettable()), Participator::getSum,
                        (participator, sum) -> sum, LinkedHashMap::new));
    }
}
