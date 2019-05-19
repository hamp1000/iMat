package imat.events;

import java.util.ArrayList;
import java.util.List;

public class NavigationEventService {
    private static List<NavigationEventObserver> observers = new ArrayList<>();

    public static void attach(NavigationEventObserver observer) {
        observers.add(observer);
    }

    public static void remove(NavigationEventObserver observer) {
        observers.remove(observer);
    }

    public static void broadcast(NavigationEvent event) {
        for (NavigationEventObserver observer : observers) {
            observer.onRouteChange(event);
        }
    }
}
