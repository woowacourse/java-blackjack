package blackjack.model;

import blackjack.model.dto.CardDTO;
import blackjack.model.dto.PlayerDTO;
import blackjack.model.dto.PlayersDTO;
import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;
import blackjack.model.player.Gamers;
import blackjack.model.player.Player;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private Gamers gamers;

    public BlackjackGame() {
        cardDeck = new CardDeck();
        dealer = new Dealer(List.of(cardDeck.selectCard(), cardDeck.selectCard()));
    }

    public void createGamers(Map<String, Integer> inputGamerInfo) throws IllegalStateException {
        Map<String, Betting> gamerInfo = new LinkedHashMap<>();
        for (String money : inputGamerInfo.keySet()) {
            gamerInfo.put(money, new Betting(inputGamerInfo.get(money)));
        }
        gamers = new Gamers(gamerInfo, cardDeck);
    }


    public PlayerDTO createDealerDto() {
        return createPlayerDto(dealer, dealer.openCards());
    }

    private PlayerDTO createPlayerDto(Player player, Cards cards) {
        return new PlayerDTO(player.getName(), player.score().getValue(), createCardDto(cards));
    }

    private List<CardDTO> createCardDto(Cards cards) {
        return cards.getEachCard().stream()
                .map(card -> new CardDTO(card.getRank().name(), card.getSuit().name()))
                .collect(Collectors.toList());
    }

    public PlayersDTO createPlayersDto() {
        List<PlayerDTO> players = gamers.getGamers().stream()
                .map(gamer -> createPlayerDto(gamer, gamer.openCards()))
                .collect(Collectors.toList());
        return new PlayersDTO(players);
    }

    public List<String> getGamerNames() {
        return gamers.getGamers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public boolean isHittable(String gamerName) {
        return gamers.findGamerByName(gamerName).isHittable();
    }

    public PlayerDTO takeGamerCard(String gamerName) {
        Gamer gamer = gamers.findGamerByName(gamerName);
        gamer.take(cardDeck.selectCard());
        return createPlayerDto(gamer, gamer.getCards());
    }

    public boolean takeDealerCard() {
        if (dealer.isHittable()) {
            dealer.take(cardDeck.selectCard());
            return true;
        }
        return false;
    }

    public Map<String, Integer> matchAndCreateResult() {
        Map<Player, Profit> matchResult = new LinkedHashMap<>();
        for (Gamer gamer : gamers.getGamers()) {
            Result result = dealer.match(gamer);
            matchResult.put(gamer, GameResult.calculateProfit(gamer, result.opposite()));
        }
        matchResult.put(dealer, GameResult.calculateDealerResult(new ArrayList<>(matchResult.values())));
        return createBettingResult(new GameResult(matchResult));
    }

    public Map<String, Integer> createBettingResult(GameResult gameResult) {
        Map<String, Integer> playerBettingResult = new LinkedHashMap<>();
        Map<Player, Profit> temp = gameResult.getPlayersResult();
        for (Player gamer : temp.keySet()) {
            playerBettingResult.put(gamer.getName(), temp.get(gamer).getAmount());
        }
        return playerBettingResult;
    }
}
