package services;


import java.util.ArrayList;

import domain.entities.Destination;
import utils.DestinationFactory;

public class FinderService {

	private DestinationFactory destinationFactory;
	
	public FinderService() {
		this.destinationFactory = new DestinationFactory();
	}
	
	public ArrayList<Destination> findRoute(String startPoint, String endPoint) {
		ArrayList<Destination> startDestnations =
				this.destinationFactory
				.findDestinationsByStartingPoint(startPoint);
		ArrayList<Destination> endDestnations =
				this.destinationFactory
				.findDestinationsByEndPoint(endPoint);
		ArrayList<Destination> wantedRoute = new ArrayList<Destination>();
		double minPrice = Double.MAX_VALUE;
		for (Destination destination : startDestnations) {
			if(destination.getEndPoint().equalsIgnoreCase(endPoint)) {
				wantedRoute.add(0, destination);
				return wantedRoute;
			}
			if(destination.getPrice() < minPrice) {
				minPrice = destination.getPrice();
				wantedRoute.add(0,destination);
			}
		}
		return search(wantedRoute, endDestnations);
		
	}

	private ArrayList<Destination> search(ArrayList<Destination> wantedRoute,
			ArrayList<Destination> endDestnations) {
		double minPrice = Double.MAX_VALUE;
		for (Destination destination : endDestnations) {
			if(wantedRoute.get(0).getEndPoint()
					.equalsIgnoreCase(destination.getStartPoint())
						&& destination.getPrice() < minPrice) {
							minPrice = destination.getPrice();
							wantedRoute.add(1,destination);
						}
		}
		if(wantedRoute.size() < 2) {
			throw new NullPointerException("No such route!");
		}
		return wantedRoute;
	}
}
