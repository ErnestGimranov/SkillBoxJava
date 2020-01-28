import core.Line;
import core.Station;
import junit.framework.TestCase;


import java.util.ArrayList;
import java.util.List;


public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex = new StationIndex();
    RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
    List<Station> route;

    @Override
    protected void setUp() throws Exception {
        Line line1 = new Line(1, "Первая");
        stationIndex.addLine(line1);
        Line line2 = new Line(2, "Вторая");
        stationIndex.addLine(line2);
        Line line3 = new Line(3, "Третья");
        stationIndex.addLine(line3);
        Station station1 = new Station("station1", line1);
        Station station2 = new Station("station2", line1);
        Station station3 = new Station("station3", line1);
        Station station4 = new Station("station4", line2);
        Station station5 = new Station("station5", line2);
        Station station6 = new Station("station6", line2);
        Station station7 = new Station("station7", line3);
        Station station8 = new Station("station8", line3);
        Station station9 = new Station("station9", line3);
        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line2.addStation(station4);
        line2.addStation(station5);
        line2.addStation(station6);
        line3.addStation(station7);
        line3.addStation(station8);
        line3.addStation(station9);
        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        stationIndex.addStation(station6);
        stationIndex.addStation(station7);
        stationIndex.addStation(station8);
        stationIndex.addStation(station9);
        List<Station> connestion1 = new ArrayList<>();
        List<Station> connestion2 = new ArrayList<>();
        connestion1.add(station2);
        connestion1.add(station5);
        connestion2.add(station3);
        connestion2.add(station9);
        stationIndex.addConnection(connestion1);
        stationIndex.addConnection(connestion2);
        route = new ArrayList<>();
        route.add(station8);
        route.add(station9);
        route.add(station3);
        route.add(station2);

    }

    public void testGetShortestRoute_single_line(){
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("station7"), stationIndex.getStation("station9"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("station7"));
        expected.add(stationIndex.getStation("station8"));
        expected.add(stationIndex.getStation("station9"));

        assertEquals(expected, actual);
    }

    public void testGetShortestRoute_with_one_transfer(){
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("station8"), stationIndex.getStation("station6"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("station8"));
        expected.add(stationIndex.getStation("station9"));
        expected.add(stationIndex.getStation("station3"));
        expected.add(stationIndex.getStation("station2"));
        expected.add(stationIndex.getStation("station5"));
        expected.add(stationIndex.getStation("station6"));

        assertEquals(expected, actual);
    }

    public void testGetShortestRoute_with_two_transfers(){
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("station8"), stationIndex.getStation("station6"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("station8"));
        expected.add(stationIndex.getStation("station9"));
        expected.add(stationIndex.getStation("station3"));
        expected.add(stationIndex.getStation("station2"));
        expected.add(stationIndex.getStation("station5"));
        expected.add(stationIndex.getStation("station6"));

        assertEquals(expected, actual);
    }

    public void testGetShortestRoute_to_same_station(){
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("station1"), stationIndex.getStation("station1"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("station1"));
        assertEquals(expected, actual);
    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
/**
 *
 *                                 Line 1
 *                                  St1
 *                                   |
 *                                   |
 *      line 2             St4 -- St2/St5 -- St6
 *                                   |
 *                                   |
 *      line 3      St7 -- St8 -- St3/St9
 *
 */