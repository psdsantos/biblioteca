package controller;
import java.util.Date;
import entities.Book;

public class Lending extends Operation{
	Date lendingDate;
	
	public Lending() {
		super();
	}
	public Lending(Date lendingDate) {
		super();
		this.lendingDate = lendingDate;
	}
	public Date getLendingDate() {
		return lendingDate;
	}
	public void setLendingDate(Date lendingDate) {
		this.lendingDate = lendingDate;
	}
	
	
	
	public boolean verifyBookAvailability(Book book) {
		// VERIFICA DISPONIBILIDADE DO LIVRO
		return book.status;
	}
	public boolean verifyUserStatus() {
		// VERIFICA DISPONIBILIDADE DO USUÁRIO QUE REALIZA O LENDING
		return super.user.isStatus();
	}
	public void lendBookTo() {
		// REALIZAR LENDING:
		// RECEBER USER
		// RECEBER BOOK
		// CHECHAR DISPONIBILIDADE DO BOOK
		// DEIXAR BOOK INDISPONIVEL (status = 0)
		// ADICIONAR BOOK À LISTA DE BORROWED DO USER
		// SAVE TRANSACTION
	}
}
