package controller;

import java.util.List;

public class UserResponse {

    private final String name;
    private final HandResponse handResponse;

    public UserResponse(final String name, final HandResponse handResponse) {
        this.name = name;
        this.handResponse = handResponse;
    }

    public String getName() {
        return name;
    }

    public HandResponse getHandResponse() {
        return handResponse;
    }

    public List<CardResponse> getCardResponse() {
        return handResponse.getCardResponse();
    }

    public int getTotalValue() {
        return handResponse.getTotalValue();
    }
}
