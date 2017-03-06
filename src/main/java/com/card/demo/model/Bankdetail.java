package com.card.demo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import java.io.Serializable;


@Entity
@Table(name="BankDetail")
public class Bankdetail implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name="BankName", length=128, nullable=false)
	private String bankName;

	/*
1. Amex Card: ^3[47][0-9]{13}$
15. Visa Card: ^4[0-9]{12}(?:[0-9]{3})?$
16. Visa Master Card: ^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$	 
	*/
	@NotEmpty
	@Pattern(regexp = "[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{3,4}")
	@Column(name="CardNumber", length=19, nullable=false)
	private String cardNumber;

	@NotEmpty
	@Pattern(regexp = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-20[0-9]{2}")
	@Column(name="ExpiryDate", length=8, nullable=false)
	private String expiryDate;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "Bankdetail [id=" + id + ", bankName=" + bankName + ", cardNumber=" + cardNumber + ", expiryDate="
				+ expiryDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bankdetail other = (Bankdetail) obj;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
