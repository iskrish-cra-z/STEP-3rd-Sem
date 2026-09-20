/**
 * Problem 3: Bus Route Ranking Engine
 */
public class BusRoute {
    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 0); // Default sensible priority
    }

    public int compareTo(BusRoute other) {
        // Sort primarily by priority DESCENDING (higher number = higher priority dispatch)
        int priorityDiff = Integer.compare(other.priority, this.priority);
        if (priorityDiff != 0) {
            return priorityDiff;
        }
        // Tie-breaker: sort alphabetically by routeCode, case-insensitive
        return this.routeCode.compareToIgnoreCase(other.routeCode);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        // Implement an O(n^2) sort as per constraints (stable sorting logic)
        for (int i = 0; i < routes.length - 1; i++) {
            for (int j = 0; j < routes.length - i - 1; j++) {
                if (routes[j].compareTo(routes[j + 1]) > 0) {
                    BusRoute temp = routes[j];
                    routes[j] = routes[j + 1];
                    routes[j + 1] = temp;
                }
            }
        }
        return routes;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);
        
        System.out.print("[");
        for (int i = 0; i < ranked.length; i++) {
            System.out.print("\"" + ranked[i].routeCode + "\"");
            if (i < ranked.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
