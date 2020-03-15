package domain;

import domain.player.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsersInformation {
    public static final int DEALER_INDEX = 0;
    private List<UserInformation> usersInformation;

    public UsersInformation(Users users) {
        this.usersInformation = new ArrayList<>();

        this.usersInformation.add(new UserInformation(users.getDealer()));
        this.usersInformation.addAll(users.getPlayers().stream()
                .map(UserInformation::new)
                .collect(Collectors.toList()));
    }

    public List<UserInformation> getUsersInformation() {
        return Collections.unmodifiableList(this.usersInformation);
    }

    public UserInformation getDealerInformation(){
        return usersInformation.get(DEALER_INDEX);
    }

    public List<UserInformation> getPlayerInformation(){
        return usersInformation
                .stream()
                .filter(userInformation -> !userInformation.isSameName("딜러"))
                .collect(Collectors.toList());
    }
}
