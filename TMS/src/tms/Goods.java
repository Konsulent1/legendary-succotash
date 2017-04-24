package tms;

public class Goods {
	
	private String id, schedule, weight, destination, product;

	
	public Goods(String id, String schedule, String weight, String destination, String product){
		this.id = id;
		this.schedule = schedule;
		this.product = product; 
		this.weight = weight;
		this.destination = destination;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	public String getSchedule() {
		return schedule;
	}


	public void setShedule(String schedule) {
		this.schedule = schedule;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}

}
