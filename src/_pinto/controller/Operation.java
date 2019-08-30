package controller;

import java.util.Date;

import entities.User;

public class Operation {
	protected User user;
	protected Date returningDate;
	
	public User getUsername() {
		return user;
	}
	public void setUsername(User user) {
		this.user = user;
	}
	public Date getReturningDate() {
		return returningDate;
	}
	public void setReturningDate(Date returningDate) {
		this.returningDate = returningDate;
	}
	
	
	public void saveTransaction() {
		// SALVAR INFOS DA OPERATION E DO USER NO TRANSACTIONS.TXT
	}
	
}
