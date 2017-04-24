package tms;

public class Schedule {
	private String id;
	private String type;
	private String startTime;
	private String origin; 
	private String destination;
	private String delay;
	private String delayReason;
	
	public Schedule(String id, String type, String startTime, String origin, String destination, String delay, String delayReason){
		this.id = id;
		this.type = type;
		this.startTime = startTime;
		this.origin = origin;
		this.destination = destination;
		this.delay = delay; 
		this.delayReason = delayReason;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	public String getDelayReason() {
		return delayReason;
	}

	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
	}

}
