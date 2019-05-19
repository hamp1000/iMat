package imat.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NavigationEventService {
    private static List<NavigationEventObserver> observers = new ArrayList<>();
    private static Stack<NavigationEvent> eventStack = new Stack<>();

    public static void attach(NavigationEventObserver observer) {
        observers.add(observer);
    }

    public static void remove(NavigationEventObserver observer) {
        observers.remove(observer);
    }

    public static void push(NavigationEvent event) {
        eventStack.push(event);
        for (NavigationEventObserver observer : observers) {
            observer.onRouteChange(event);
        }
    }

    public static void pop() {
        if (eventStack.empty()) {
            return;
        }
        eventStack.pop();
        NavigationEvent event = eventStack.peek();
        for (NavigationEventObserver observer : observers) {
            observer.onRouteChange(event);
        }
    }
}
