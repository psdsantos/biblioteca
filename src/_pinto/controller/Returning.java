package controller;
import java.util.Date;

public class Returning extends Operation{
	Date realReturningDate;
	
	public void generatePenalty() {
		// SE ATRASAR ENTREGA, GRAVAR NO PENALTIES.TXT	
	}
	public void returnBookFrom() {
		// REALIZAR RETURNING:
		// RECEBER BOOK
		// DEIXAR BOOK DISPONIVEL (status = 1)
		// REMOVER BOOK DA LISTA DE BORROWED DO USER
		// GERAR PENALIDADE
		// SAVE TRANSACTION
	}
}	
