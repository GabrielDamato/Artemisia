package br.com.artemisia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuscadorMassa {
	
	public void buscaMassa(String[][] massas, String[] banco) {
		
		String [] auxSplit;
		String composto;
		double erro;
		double massaBuscada;
		double pepmass;
		ExportarResultado Exp = new ExportarResultado();
		
		try {
			
			BufferedReader inData;
			inData = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Margem de erro: ");
			erro = Double.parseDouble(inData.readLine());
			System.out.println("Procurando...");
			
			for(int i=1; i<massas.length; i++) {
				for(int j=1; j<massas[0].length; j++) {
					
					massaBuscada=Double.parseDouble(massas[i][j].replaceAll(",","."));
					for(int k=0; k<banco.length; k++) {
						String [] linha = banco[k].split("\n");
						String [] massa = linha[2].split("=");
						pepmass = Double.parseDouble(massa[1]);
						if (massaBuscada+erro>pepmass && massaBuscada-erro<pepmass) {
							auxSplit = linha[1].split("=");
							String feature_id = auxSplit[1];
							auxSplit = linha[4].split("=");
							double rtinseconds = Double.parseDouble(auxSplit[1]);
							auxSplit = linha[5].split("=");
							String charge = auxSplit[1];
							double frag []= new double[linha.length-8];
							double intensidade[]= new double[linha.length-8];
							for (int l=7; l<linha.length-1; l++) {
								auxSplit = linha[l].split(" ");
								frag [l-7] = Double.parseDouble(auxSplit[0]);
								auxSplit = auxSplit[1].split("E");								
								intensidade [l-7] = Double.parseDouble(auxSplit[0])*Math.pow(10, Double.parseDouble(auxSplit[1]));
							}
							composto = massas[i][0] + " " + massas[0][j];
							Exp.exportaResultado(composto, massaBuscada, charge, feature_id, rtinseconds, frag, intensidade);
						}
					}
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
