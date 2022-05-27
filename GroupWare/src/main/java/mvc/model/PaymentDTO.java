package mvc.model;

public class PaymentDTO {
	private int paynumber;
	private String number;
	private String imputed_date;
	private String payment_date;
	private int position_pay;
	private int margin_pay;
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
	public int getPosition_pay() {
		return position_pay;
	}
	public void setPosition_pay(int position_pay) {
		this.position_pay = position_pay;
	}
	public int getMargin_pay() {
		return margin_pay;
	}
	public void setMargin_pay(int margin_pay) {
		this.margin_pay = margin_pay;
	}
	
	
}
