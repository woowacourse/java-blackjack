package domain.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersInformations {

    private List<UserInformation> usersInformation;

    public UsersInformations(Players players) {
        this.usersInformation = new ArrayList<>();

        this.usersInformation.addAll(players.getPlayers().stream()
                .map(UserInformation::new)
                .collect(Collectors.toList())
        );
    }


    public List<UserInformation> getPlayerInformation() {
        return usersInformation
                .stream()
                .filter(userInformation -> !userInformation.isSameName("딜러"))
                .collect(Collectors.toList());
    }
}
