package com.matera.library.functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.matera.library.model.Book;

public class ReadFile {

	private FileReader arquivo;
	private BufferedReader lerArquivo;
	private String caminhoArquivo = "arquivos/arquivo";

	public List<String> read() {

		List<String> listaLivro = new ArrayList<String>();

		try {
			this.arquivo = new FileReader(caminhoArquivo);
			this.lerArquivo = new BufferedReader(this.arquivo);

			String linha = this.lerArquivo.readLine();

			while (linha != null) {
				listaLivro.add(linha);
				linha = this.lerArquivo.readLine();
			}
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: ", e.getMessage());
		}
		return listaLivro;
	}

	public List<Book> formatData(List<String> listaLivros) {

		List<Book> listaFormatada = new ArrayList<Book>();

		for (int i = 0; i < listaLivros.size(); i++) {
			String format = listaLivros.get(i).replace(" ", "");
			String id = format.substring(0, 10);
			String descricao = listaLivros.get(i).substring(10, listaLivros.get(i).length() - 23).trim();

			String preco = format.substring(format.length() - 23, format.length() - 8);
			preco = preco.replaceFirst("^0+(?!$)", "");
			String cortePreco1 = preco.substring(0, preco.length() - 2);
			String cortePreco2 = preco.substring(preco.length() - 2, preco.length());
			BigDecimal precoFormatado = new BigDecimal(cortePreco1 + "." + cortePreco2);

			String data = format.substring(format.length() - 8, format.length());
			String dia = data.substring(0, data.length() - 6);
			String mes = data.substring(2, data.length() - 4);
			String ano = data.substring(4, data.length());
			String dataFinal = (dia + "/" + mes + "/" + ano);
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			Book livro = new Book();
			livro.setId(id);
			livro.setDescricao(descricao);
			livro.setPreco(precoFormatado);

			java.util.Date dt;
			try {
				dt = formato.parse(dataFinal);
				java.sql.Date d = new java.sql.Date(dt.getTime());
				livro.setData(d);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			listaFormatada.add(livro);
		}
		return listaFormatada;
	}

}