package br.com.artemisia;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import jxl.write.Number;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ExportarResultado {
	
	String nome;
	int cont = 1;
	WritableWorkbook planilha;
	WritableSheet aba;
	
	public ExportarResultado() {
		try {
			BufferedReader inData;
			inData = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Nome do arquivo de saída: ");
			nome = inData.readLine();
			
			planilha = Workbook.createWorkbook(new File(nome));
			aba = planilha.createSheet(nome, 0);
			
			String cabecalho[] = new String[7];
			cabecalho[0] = "COMPOSTO";
			cabecalho[1] = "MASSA BUSCADA";
			cabecalho[2] = "CHARGE";
			cabecalho[3] = "FEATURE_ID";
			cabecalho[4] = "RTINSECONDS";
			cabecalho[5] = "FRAGMENTOS";
			cabecalho[6] = "INTENSIDADE";
			
			for (int i=0 ; i<cabecalho.length; i++ ) {
				Label label = new Label(i,0,cabecalho[i]);
				aba.addCell(label);
			}
		
			planilha.write();
			planilha.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void exportaResultado(String composto, double massaBuscada, String charge, String feature_id, double rtinseconds, double [] frag, double [] intensidade){
		
		try {
			Workbook wb = Workbook.getWorkbook(new File(nome));
	        WritableWorkbook copia= Workbook.createWorkbook(new File(nome), wb);
	        WritableSheet ws = copia.getSheet(0);

			Label label = new Label(0, cont, composto);
			ws.addCell(label);
			
			Number number = new Number(1, cont, Double.valueOf(massaBuscada));
			ws.addCell(number);	
			
			label = new Label(2, cont, charge);
			ws.addCell(label);
			
			label = new Label(3, cont, feature_id);
			ws.addCell(label);
		
			number = new Number(4, cont, rtinseconds);
			ws.addCell(number);
			
			for (int i=0; i<frag.length; i++) {
				
				number = new Number(5, cont, frag[i]);
				ws.addCell(number);
				
				number = new Number(6, cont, intensidade[i]);
				ws.addCell(number);
			
				cont++;
			}
			
			cont++;
			
			copia.write();
			copia.close();
			wb.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
