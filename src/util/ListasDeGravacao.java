package util;

import java.util.ArrayList;
import java.util.List;

import modelos.ArquivoCblTO;

public class ListasDeGravacao {

	private static List<ArquivoCblTO> arquivosParaExportar = new ArrayList<ArquivoCblTO>();

	public static void setArquivosParaExportar(
			List<ArquivoCblTO> arquivosParaExportar) {
		ListasDeGravacao.arquivosParaExportar = arquivosParaExportar;
	}

	public static List<ArquivoCblTO> getArquivosParaExportar() {
		return arquivosParaExportar;
	}

}
