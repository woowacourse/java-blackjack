package fuelinjection;

import fuelinjection.view.InputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        initializeDistance(inputView);
    }

    private static Distance initializeDistance(InputView inputView) {
        try {
            return new Distance(inputView.askDistance());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initializeDistance(inputView);
        }
    }
}
