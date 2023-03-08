package controller;

import model.user.Dealer;
import model.user.Hand;
import model.user.Participants;
import model.user.Player;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class BlackJackGameResponse {

    private final UserResponse dealerResponse;
    private final List<UserResponse> playerResponses;

    private BlackJackGameResponse(final UserResponse dealerResponse, final List<UserResponse> playerResponses) {
        this.dealerResponse = dealerResponse;
        this.playerResponses = playerResponses;
    }

    public static BlackJackGameResponse create(final Participants participants) {
        final UserResponse dealerResponse = createDealerResponse(participants.getDealer());
        final List<UserResponse> playerResponse = createPlayerResponse(participants.getPlayers());
        return new BlackJackGameResponse(dealerResponse, playerResponse);
    }

    private static UserResponse createDealerResponse(final Dealer dealer) {
        final Hand dealerHand = dealer.getHand();
        final HandResponse handResponse = HandResponse.of(dealer.calculateTotalValue(), dealerHand.getCards());
        return new UserResponse(dealer.getName(), handResponse);
    }

    private static List<UserResponse> createPlayerResponse(final List<Player> players) {
        return players.stream()
                .map(BlackJackGameResponse::createUserResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    private static UserResponse createUserResponse(final Player player) {
        return new UserResponse(player.getName(),
                HandResponse.of(player.calculateTotalValue(), player.getHand().getCards()));
    }

    public UserResponse getDealerResponse() {
        return dealerResponse;
    }

    public List<UserResponse> getPlayerResponses() {
        return unmodifiableList(playerResponses);
    }
}
