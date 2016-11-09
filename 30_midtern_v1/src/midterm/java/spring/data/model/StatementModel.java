package midterm.java.spring.data.model;

import java.util.List;

public class StatementModel {
	private String username;
	private int balance;
	private List<TransactionModel>  statementList;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public List<TransactionModel> getStatementList() {
		return statementList;
	}
	public void setStatementList(List<TransactionModel>  statementList) {
		this.statementList = statementList;
	}
}
