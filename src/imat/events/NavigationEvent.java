package imat.events;

public class NavigationEvent {
    public NavigationRoute route;
    public Object arg;

    public NavigationEvent(NavigationRoute route, Object arg) {
        this.route = route;
        this.arg = arg;
    }
}
