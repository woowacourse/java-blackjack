package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.GameResult;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    public static final int POSSIBLE_MAX_VALUE = 21;
    private static final int INIT_DISTRIBUTION_COUNT = 2;
    private static final int ADDITIONAL_DISTRIBUTE_STANDARD = 16;

    private final CardFactory cardFactory;
    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(List<String> names) {
        this.cardFactory = new CardFactory(Card.getCards());
        this.dealer = new Dealer();
        this.players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void distributeFirstCards() {
        for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
            distributeCard(dealer);
            players.forEach(this::distributeCard);
        }
    }

    public int distributeAdditionalToDealer() {
        int count = 0;
        while (!dealer.isOverThan(ADDITIONAL_DISTRIBUTE_STANDARD)) {
            distributeCard(dealer);
            count++;
        }
        return count;
    }

    private void distributeCard(Gamer gamer) {
        gamer.addCard(cardFactory.draw());
    }

    public void distributeCardToPlayer(String name) {
        findPlayerByName(name).addCard(cardFactory.draw());
    }

    public GamerDto findPlayerDtoByName(String name) {
        return new GamerDto(findPlayerByName(name));
    }

    private Player findPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("플레이어가 존재하지 않습니다."));
    }

    public GameResult createResult(Dealer dealer, List<Player> players) {
        return new GameResult(players, dealer);
    }

    public GamerDto getDealerDto() {
        return new GamerDto(dealer);
    }

    public List<GamerDto> getPlayerDtos() {
        return players.stream()
                .map(GamerDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .map(Name::getValue)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isBurst(String name) {
        Player player = findPlayerByName(name);
        return player.getCardsNumberSum() > POSSIBLE_MAX_VALUE;
    }
}
