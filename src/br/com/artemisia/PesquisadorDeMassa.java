package br.com.artemisia;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PesquisadorDeMassa {

	public static void main(String[] args) {
		
		BufferedReader inData;
		inData = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("Nome do arquivo de dados a serem pesquisados: ");
			String xls = inData.readLine();
			System.out.println("Nome do arquivo de banco de dados para busca: ");
			String txt = inData.readLine();
			LeitorDados ld = new LeitorDados();
			String [][] massas = ld.lerPlanilha(xls);
			String [] bd = ld.lerTexto(txt);
			BuscadorMassa bm = new BuscadorMassa();
			bm.buscaMassa(massas, bd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("fim");
	}

}
