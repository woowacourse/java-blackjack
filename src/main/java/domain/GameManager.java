package domain;

import dto.ParticipantCardsDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameManager {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public GameManager(Players players) {
        this.dealer = initDealer();
        this.players = players;
        this.deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    private Dealer initDealer() {
        Name name = new Name("딜러");
        Dealer dealer = new Dealer(name);
        return dealer;
    }

    public void distributeInitialCards() {
        distributeCardToDealer(dealer);
        distributeCardToPlayers(players);
    }

    private void distributeCardToDealer(Dealer dealer) {
        distributeInitialCards(dealer);
    }

    private void distributeCardToPlayers(Players players) {
        // ToDo: getPlayers를 쓰면 캡슐화가 약해진다. 바깥에서 내부구현 List<Player>에 의존하게 된다.
        // TODO: 그래서 Iterable이나 forEach 순회 메서드를 만드는 게 낫다.
        players.forEach(this::distributeInitialCards);
    }

    private void distributeInitialCards(Participant participant) {
        drawCardTo(participant);
        drawCardTo(participant);
    }

    public ParticipantCardsDto getDealerDto() {
        return dealer.getParticipantCardsDto();
    }

    public List<ParticipantCardsDto> getPlayerDtos() {
        // ToDo: 추후 책임 분리 하면 좋을 듯
        List<ParticipantCardsDto> participantCardsDtos = new ArrayList<>();
        players.forEach(player -> participantCardsDtos.add(player.getParticipantCardsDto()));
        return participantCardsDtos;
    }

    public void drawCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receiveCard(card);
    }

    public Map<String, GameResult> getGameResult() {
        Map<String, GameResult> gameResult = new HashMap<>();
        players.forEach(player -> {
            GameResult result = player.judgeResult(dealer);
            gameResult.put(player.getName(), result);
        });
        return gameResult;
    }
}
