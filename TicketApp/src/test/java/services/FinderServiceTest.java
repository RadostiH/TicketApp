package services;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.entities.Destination;
import domain.models.binding.DestinationBindingModel;
import junit.framework.Assert;
import utils.DestinationFactory;

@SuppressWarnings("all")
public class FinderServiceTest {
	
	private DestinationFactory destinationFactory;
	private FinderService finderService;

	@Before
	public void initalize() {
		this.destinationFactory = new DestinationFactory();
		this.finderService = new FinderService();
		DestinationBindingModel destination1 = new DestinationBindingModel(
				"Pernik", "Vidin", "09/26/2019 15:00:00",
				"09/26/2019 18:30:00", 30, 15.0);
		DestinationBindingModel destination2 = new DestinationBindingModel(
				"Vidin", "Ruse", "09/26/2019 19:00:00",
				"09/26/2019 22:15:00", 45, 12.5);
		this.destinationFactory.addDestination(destination1);
		this.destinationFactory.addDestination(destination2);
	}

	@Test
	public void testIfFindRouteReturnsComplexRoute() {
		ArrayList<Destination> route =
				this.finderService.findRoute("Pernik", "Ruse");
		Assert.assertEquals(15.0, route.get(0).getPrice());
		Assert.assertEquals(12.5, route.get(1).getPrice());
	}
	
	@Test 
	public void testIfFinderRouteReturnsSimpleRoute() {
		ArrayList<Destination> route =
				this.finderService.findRoute("Pernik", "Vidin");
		Assert.assertEquals(15.0, route.get(0).getPrice());
	}
	
	@Test(expected = NullPointerException.class)
	public void testIfFindRouteThrowsExeptionOnNotExistingRoute() {
		ArrayList<Destination> route =
				this.finderService.findRoute("Varna", "Burgas");
	}
	
	@After
	public void cleanDB() {
		this.destinationFactory.deleteDestination(
				this.destinationFactory.findDestination(
						"Pernik", "Vidin", "09/26/2019 15:00:00")
							.getId());
		this.destinationFactory.deleteDestination(
				this.destinationFactory.findDestination(
						"Vidin", "Ruse", "09/26/2019 19:00:00")
							.getId());
	}
}
