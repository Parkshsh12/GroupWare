package mvc.model;

public class PaymentDTO {
	private int paynumber;
	private String number;
	private String imputed_date;
	private String payment_date;
	public int getPaynumber() {
		return paynumber;
	}
	public void setPaynumber(int paynumber) {
		this.paynumber = paynumber;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getImputed_date() {
		return imputed_date;
	}
	public void setImputed_date(String imputed_date) {
		this.imputed_date = imputed_date;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	
	
}
