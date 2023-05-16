package streamspractice;

public class Customer {
 
	Long customerId;
	String name;
	Integer tier;
	public Customer() {

	}
	public Customer(Long customerId, String name, Integer tier) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.tier = tier;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTier() {
		return tier;
	}
	public void setTier(Integer tier) {
		this.tier = tier;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", tier=" + tier + "]";
	}
	
	
	
}
