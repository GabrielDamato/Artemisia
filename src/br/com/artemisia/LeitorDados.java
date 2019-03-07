package br.com.artemisia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class LeitorDados {
				
		public String[][] lerPlanilha(String nome){
			
			try {
				
				File arquivo=new File(nome);
				Workbook planilha=Workbook.getWorkbook(arquivo);
				Sheet aba=planilha.getSheet(0);
				String [][] dados = new String[aba.getRows()][aba.getColumns()];
				Cell[] cel;
				
				for (int i=0; i<dados.length;i++) {
					cel=aba.getRow(i);
					for(int j=0; j<dados[0].length;j++) {
						dados[i][j] = cel[j].getContents();
					}
				}
				return dados;
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		public String[] lerTexto(String nome) {
			
			try {
				String texto = new String(Files.readAllBytes(Paths.get(nome)));
				String [] textoFormat = texto.split("\r\n" + "\r\n");
				return textoFormat;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
		}
}
