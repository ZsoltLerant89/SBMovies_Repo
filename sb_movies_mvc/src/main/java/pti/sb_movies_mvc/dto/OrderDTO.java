package pti.sb_movies_mvc.dto;

public class OrderDTO {

	private int order;

	public OrderDTO(int order) {
		super();
		this.order = order;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
